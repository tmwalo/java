package com.gmail.vuyotm;

import com.gmail.vuyotm.custom_exceptions.CoordinateNotPositiveException;

public class Coordinates {

    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) {
        if ((longitude <= 0) || (latitude <= 0))
            throw new CoordinateNotPositiveException("Coordinate must be positive.");
        if (height < 0)
            height = 0;
        else if (height > 100)
            height = 100;
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void setLongitude(int longitude) {
        if (longitude <= 0)
            throw new CoordinateNotPositiveException("Coordinate must be positive.");
        this.longitude = longitude;
    }

    public void setLatitude(int latitude) {
        if (latitude <= 0)
            throw new CoordinateNotPositiveException("Coordinate must be positive.");
        this.latitude = latitude;
    }

    public void setHeight(int height) {
        if (height < 0)
            height = 0;
        else if (height > 100)
            height = 100;
        this.height = height;
    }

    @Override
    public String toString() {

        String  str;

        str = "";
        str += "longitude - " + getLongitude() + System.lineSeparator();
        str += "latitude - " + getLatitude() + System.lineSeparator();
        str += "height - " + getHeight();
        return (str);

    }

}
