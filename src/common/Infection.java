package common;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rui on 1/24/17.
 */
public class Infection {

    private final int[] syntoms;
    private final int contagion;
    private int severity;


    public Infection(int[] syntoms) {
        this.syntoms = syntoms;
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
}
