package com.gmail.vuyotm;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {

        String  weather;
        int     currentLatitude;
        int     currentHeight;

        if (weatherTower == null)
            throw new NullPointerException("weatherTower field can't be null.");
        weather = weatherTower.getWeather(coordinates);
        setCurrentWeather(weather);
        currentLatitude = coordinates.getLatitude();
        currentHeight = coordinates.getHeight();
        if (weather.equals("SUN")) {
            coordinates.setLatitude(currentLatitude + 10);
            coordinates.setHeight(currentHeight + 2);
        }
        else if (weather.equals("RAIN")) {
            coordinates.setLatitude(currentLatitude + 5);
        }
        else if (weather.equals("FOG")) {
            coordinates.setLatitude(currentLatitude + 1);
        }
        else if (weather.equals("SNOW")) {
            coordinates.setHeight(currentHeight - 7);
        }

    }

    @Override
    public String weatherChangeMsg() {

        String  weather;
        String  message;

        message = "";
        message += getType();
        message += "#";
        message += getName();
        message += "(" + getId() + "): ";

        weather = getCurrentWeather();
        if (weather == null)
            throw new NullPointerException("weather local variable can't be null.");
        if (weather.equals("SUN")) {
            message += "Melting in the blazing sun.";
        }
        else if (weather.equals("RAIN")) {
            message += "The rain is raining.";
        }
        else if (weather.equals("FOG")) {
            message += "Speeding through the fog.";
        }
        else if (weather.equals("SNOW")) {
            message += "Snow way the cold will slow me down!";
        }
        else
            message += "Unknown weather condition.";
        return (message);

    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        updateConditions();
    }

}