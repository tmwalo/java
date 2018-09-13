package com.gmail.vuyotm;

public abstract class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {

        Coordinates coordinates;

        coordinates = new Coordinates(longitude, latitude, height);
        if (type.equals("Helicopter")) {
            Helicopter helicopter;
            helicopter = new Helicopter(name, coordinates);
            helicopter.setType(type);
            return (helicopter);
        }
        else if (type.equals("JetPlane")) {
            JetPlane jetPlane;
            jetPlane = new JetPlane(name, coordinates);
            jetPlane.setType(type);
            return (jetPlane);
        }
        else if (type.equals("Baloon")) {
            Baloon  baloon;
            baloon = new Baloon(name, coordinates);
            baloon.setType(type);
            return (baloon);
        }
        else
            throw new IllegalArgumentException(type + " is an unknown aircraft type.");

    }

}
