package common;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rui on 1/24/17.
 */
public class Infection {

    private final String syntom;
    private final String[] syntoms = {"headache", "vomits", "nosebleed","heart failure"};

    private int severity;


    public Infection() {

        this.syntom = syntoms[new Random().nextInt(syntoms.length)];
        //this.syntoms = syntoms;
        severity = new Random().nextInt(10) +1;

        assert syntom != null && !syntom.isEmpty() && severity <= 10 && severity >= 1;

    }
    public int getSeverity() {
        return severity;
    }

    public void updateSeverity(int severity) {
        this.severity = severity;
    }

    @Override
    public boolean equals(Object o) {

        Infection i2 = (Infection) o;
        return syntom.equalsIgnoreCase(i2.syntom());
    }

    public String syntom() {

        return syntom;
    }
}
