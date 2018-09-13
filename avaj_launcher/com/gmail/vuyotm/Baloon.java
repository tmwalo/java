package com.gmail.vuyotm;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
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
            coordinates.setLongitude(currentLongitude + 2);
            coordinates.setHeight(currentHeight + 4);
        }
        else if (weather.equals("RAIN")) {
            coordinates.setHeight(currentHeight - 5);
        }
        else if (weather.equals("FOG")) {
            coordinates.setHeight(currentHeight - 3);
        }
        else if (weather.equals("SNOW")) {
            coordinates.setHeight(currentHeight - 15);
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
            message += "I stay shining in the sun.";
        }
        else if (weather.equals("RAIN")) {
            message += "The rain ruins the vibe.";
        }
        else if (weather.equals("FOG")) {
            message += "Drifting in the fog.";
        }
        else if (weather.equals("SNOW")) {
            message += "Snow in my eyes.";
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
