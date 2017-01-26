package common;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rui on 1/24/17.
 */
public class Infection {

    //private final int[] syntoms;
    private int contagion;
    private int severity;


    public Infection() {
        //this.syntoms = syntoms;
        this.contagion = new Random().nextInt(10) + 1;
        severity = new Random().nextInt(10) +1;
    }

    public int getContagion() {
        return contagion;
    }

    public int getSeverity() {
        return severity;
    }

    public void updateSeverity(int severity) {
        this.severity = severity;
    }

    public void increaseContagion(int increase) {
        contagion += increase;
    }

    @Override
    public boolean equals(Object o) {

        Infection i2 = (Infection) o;
        return getSeverity() == i2.getSeverity() && getContagion() == i2.getContagion();
    }
}
