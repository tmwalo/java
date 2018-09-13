package com.gmail.vuyotm.custom_exceptions;

public class InvalidAircraftData extends RuntimeException {

    public InvalidAircraftData(String message) {
        super(message);
    }

}
