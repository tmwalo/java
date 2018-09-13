package com.gmail.vuyotm;

import com.gmail.vuyotm.custom_exceptions.CoordinateNotPositiveException;
import com.gmail.vuyotm.custom_exceptions.InvalidAircraftData;
import com.gmail.vuyotm.custom_exceptions.InvalidSimulationIterations;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Simulator {

    public static void main(String[] args) {

        boolean             foundSimulationIterations;
        boolean             foundAircraftData;
        int                 simulationIterations;
        String              line;
        int                 lineNum;
        String[]            tokens;
        ArrayList<Flyable>  aircraftCollection;
        WeatherTower        weatherTower;

        if (args.length != 1) {
            System.out.println("Error" + System.lineSeparator() + "Usage: Simulator scenario_file");
            System.exit(0);
        }

        foundSimulationIterations = false;
        foundAircraftData = false;
        simulationIterations = 0;
        lineNum = 0;
        aircraftCollection = new ArrayList<Flyable>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            while ((line = bufferedReader.readLine()) != null) {
                ++lineNum;
                if (line.equals(""))
                    continue ;
                line = line.trim();
                tokens = line.split("\\s+");
                if (!foundSimulationIterations) {
                    simulationIterations = parseSimulationIterations(tokens, lineNum);
                    foundSimulationIterations = true;
                }
                else
                    foundAircraftData = buildAircraft(tokens, aircraftCollection, lineNum);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch (InvalidSimulationIterations e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch (InvalidAircraftData e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " (line number: " + lineNum + ")");
            System.exit(0);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch (CoordinateNotPositiveException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        if (!foundSimulationIterations) {
            System.out.println("Error - Number of simulation iterations not found.");
            System.exit(0);
        }
        if (!foundAircraftData) {
            System.out.println("Error - Aircraft data not found.");
            System.exit(0);
        }

        weatherTower = new WeatherTower();
        registerTowerWithFlyable(aircraftCollection, weatherTower);
        registerFlyableWithTower(aircraftCollection, weatherTower);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("simulation.txt"))) {

            List<Flyable> observersCopy;

            writeRegistrationMessages(bufferedWriter, weatherTower);
            for (int index = simulationIterations; index > 0; --index) {

                observersCopy = new ArrayList<Flyable>(weatherTower.getObservers());

                weatherTower.changeWeather();
                for (Flyable aircraft : observersCopy) {

                    if (((Aircraft) aircraft).getCoordinates().getHeight() != 0) {
                        bufferedWriter.write(((Aircraft) aircraft).weatherChangeMsg());
                        bufferedWriter.newLine();
                    }
                    else
                        landAndUnregisterAircraft(bufferedWriter, (Aircraft) aircraft, weatherTower);

                }

            }

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

    public static int parseSimulationIterations(String[] tokens, int lineNum) {

        int simulationIterations;

        if (tokens.length != 1)
            throw new InvalidSimulationIterations("Invalid number of iterations input line. (line number: " + lineNum + ")");
        simulationIterations = Integer.parseInt(tokens[0]);
        if (simulationIterations <= 0)
            throw new InvalidSimulationIterations("Number of iterations must be positive. (line number: " + lineNum + ")");
        return (simulationIterations);

    }

    public static boolean buildAircraft(String[] tokens, ArrayList<Flyable> aircraftCollection, int lineNum) {

        String  type;
        String  name;
        int     longitude;
        int     latitude;
        int     height;

        if (tokens.length != 5)
            throw new InvalidAircraftData("Invalid aircraft data. (line number: " + lineNum + ")");
        type = tokens[0];
        name = tokens[1];
        longitude = Integer.parseInt(tokens[2]);
        latitude = Integer.parseInt(tokens[3]);
        height = Integer.parseInt(tokens[4]);
        aircraftCollection.add(AircraftFactory.newAircraft(type, name, longitude, latitude, height));
        return (true);

    }

    public static void registerTowerWithFlyable(ArrayList<Flyable> aircraftCollection, WeatherTower weatherTower) {
        for (Flyable flyable : aircraftCollection) {
            flyable.registerTower(weatherTower);
        }
    }

    public static void registerFlyableWithTower(ArrayList<Flyable> aircraftCollection, WeatherTower weatherTower) {
        for (Flyable flyable : aircraftCollection) {
            weatherTower.register(flyable);
        }
    }

    public static void writeRegistrationMessages(BufferedWriter bufferedWriter, WeatherTower weatherTower) throws IOException {
        for (Flyable flyable : weatherTower.getObservers()) {
            bufferedWriter.write(weatherTower.registerMessage(flyable));
            bufferedWriter.newLine();
        }
    }

    public static void landAndUnregisterAircraft(BufferedWriter bufferedWriter, Aircraft aircraft, WeatherTower weatherTower) throws IOException {
        bufferedWriter.write(aircraft.weatherChangeMsg());
        bufferedWriter.newLine();
        weatherTower.unregister((Flyable) aircraft);
        bufferedWriter.write(aircraft.landingMsg());
        bufferedWriter.newLine();
        bufferedWriter.write(weatherTower.unregisterMessage((Flyable) aircraft));
        bufferedWriter.newLine();
    }

}
