package com.gmail.vuyotm;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {

        WeatherProvider weatherProvider;

        if (coordinates == null)
            throw new NullPointerException("Coordinates can't be null.");
        weatherProvider = WeatherProvider.getProvider();
        return  (weatherProvider.getCurrentWeather(coordinates));

    }

    void changeWeather() {
        conditionsChanged();
    }

}
