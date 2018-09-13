package com.gmail.vuyotm;

import java.util.ArrayList;

public abstract class Aircraft {

    protected long              id;
    protected String            type;
    protected String            name;
    protected Coordinates       coordinates;
    protected String            currentWeather;
    private static String[]     aircraftTypes = {"Helicopter", "JetPlane", "Baloon"};
    private static long         idCounter = 0;

    protected Aircraft(String name, Coordinates coordinates) {
        if (name == null)
            throw new NullPointerException("Aircraft name can't be null.");
        if (name == "")
            throw new IllegalArgumentException("Aircraft name can't be empty.");
        if (coordinates == null)
            throw new NullPointerException("Coordinates can't be null.");
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    public abstract String weatherChangeMsg();

    public String landingMsg() {

        String      message;

        message = "";
        message += getType();
        message += "#";
        message += getName();
        message += "(" + getId() + ")";
        message += " landing.";
        return (message);

    }

    private long nextId() {
        ++idCounter;
        return (idCounter);
    }

    public void setType(String type) {

        boolean found;

        found = false;
        if (type == null)
            throw new NullPointerException("Aircraft type can't be null.");
        for (String currentAircraftType : getAircraftTypes()) {
            if (type.equals(currentAircraftType)) {
                found = true;
                break ;
            }
        }
        if (!found)
            throw new IllegalArgumentException("Unknown aircraft type provided.");
        this.type = type;

    }

    public void setCurrentWeather(String currentWeather) {

        boolean found;

        found = false;
        if (currentWeather == null)
            throw new NullPointerException("Current weather can't be null.");
        for (String weatherType : WeatherProvider.getWeatherTypes()) {
            if (currentWeather.equals(weatherType)) {
                found = true;
                break ;
            }
        }
        if (!found)
            throw  new IllegalArgumentException("Unknown weather value provided.");
        this.currentWeather = currentWeather;

    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public static String[] getAircraftTypes() {
        return aircraftTypes;
    }

    @Override
    public String toString() {

        String  str;

        str = "";
        str += "id: " + getId() + System.lineSeparator();
        str += "type: " + getType() + System.lineSeparator();
        str += "name: " + getName() + System.lineSeparator();
        str += "coordinates:" + System.lineSeparator() + getCoordinates().toString() + System.lineSeparator();
        str += "current weather: " + getCurrentWeather();
        return (str);

    }

}
