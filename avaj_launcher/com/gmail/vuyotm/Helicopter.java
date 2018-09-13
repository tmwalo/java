package com.gmail.vuyotm;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {

        String  weather;
        int     currentLongitude;
        int     currentHeight;

        if (weatherTower == null)
            throw new NullPointerException("weatherTower field can't be null.");
        weather = weatherTower.getWeather(coordinates);
        setCurrentWeather(weather);
        currentLongitude = coordinates.getLongitude();
        currentHeight = coordinates.getHeight();
        if (weather.equals("SUN")) {
            coordinates.setLongitude(currentLongitude + 10);
            coordinates.setHeight(currentHeight + 2);
        }
        else if (weather.equals("RAIN")) {
            coordinates.setLongitude(currentLongitude + 5);
        }
        else if (weather.equals("FOG")) {
            coordinates.setLongitude(currentLongitude + 1);
        }
        else if (weather.equals("SNOW")) {
            coordinates.setHeight(currentHeight - 12);
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
            message += "Perfect warm weather for the chopper.";
        }
        else if (weather.equals("RAIN")) {
            message += "The rain brings tears.";
        }
        else if (weather.equals("FOG")) {
            message += "Could I use the rotor to clear this fog.";
        }
        else if (weather.equals("SNOW")) {
            message += "The snow makes us slow.";
        }
        else {
            message += "Unknown weather condition.";
        }
        return (message);

    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        updateConditions();
    }

}
