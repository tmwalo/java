package com.gmail.vuyotm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Tower {

    private List<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable flyable) {
        if ((flyable != null) && (!observers.contains(flyable)))
            observers.add(flyable);
    }

    public String registerMessage(Flyable flyable) {

        Aircraft    aircraft;
        String      message;

        if (flyable == null)
            return ("");
        aircraft = (Aircraft)flyable;
        message = "";
        message += "Tower says: ";
        message += aircraft.getType();
        message += "#";
        message += aircraft.getName();
        message += "(" + aircraft.getId() + ")";
        message += " registered to weather tower.";
        return (message);

    }

    public void unregister(Flyable flyable) {
        if (flyable != null)
            observers.remove(flyable);
    }

    public String unregisterMessage(Flyable flyable) {

        Aircraft    aircraft;
        String      message;

        if (flyable == null)
            return ("");
        aircraft = (Aircraft)flyable;
        message = "";
        message += "Tower says: ";
        message += aircraft.getType();
        message += "#";
        message += aircraft.getName();
        message += "(" + aircraft.getId() + ")";
        message += " unregistered from weather tower.";
        return (message);

    }

    protected void conditionsChanged() {
        for (Flyable aircraft : observers) {
            aircraft.updateConditions();
        }
    }

    public List<Flyable> getObservers() {
        return observers;
    }

}
