package com.gmail.vuyotm;

public class WeatherProvider {

    private static WeatherProvider  weatherProvider;
    private static String[]         weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {

        if (weatherProvider == null)
            weatherProvider = new WeatherProvider();
        return (weatherProvider);

    }

    public String getCurrentWeather(Coordinates coordinates) {

        int     sumCoords;
        int     weatherIndex;
        String  randomWeather;

        if (coordinates == null)
            throw new IllegalArgumentException("Coordinates can't be null.");
        sumCoords = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
        weatherIndex = sumCoords % weather.length;
        randomWeather = weather[weatherIndex];
        return (randomWeather);

    }

    public static String[] getWeatherTypes() {
        return weather;
    }

}
