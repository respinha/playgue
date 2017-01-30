package common;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rui on 1/24/17.
 *
 * Infection object. It is an attribute of {@link entities.Bacteria}, which has variable symptom and  severity.
 * Other fields may be added with appropriate methods if we wish to introduce more elements to the
 * infection/contagion mechanism.
 *
 * Class invariants: symptom is never null and severity is positive.
 */
public class Infection {

    private final String symptom;
    private double severity;

    private BitSet dna;
    /**
     * Constructor.
     *
     * Post-conditions: symptom is valid and severity is a value between 1 and 5
     */
    public Infection() {

        this.symptom = Globals.randomSymptom();

        severity = 1 + (5 - 1) * new Random().nextDouble();

        dna = new BitSet();

        assert symptom != null && !symptom.isEmpty() && severity <= 5 && severity >= 1;

    }
    public double getSeverity() {
        return severity;
    }

    public void updateSeverity(double severity) {
        this.severity = severity;
    }

    @Override
    public boolean equals(Object o) {

        Infection i2 = (Infection) o;

        assert i2 != null;

        return symptom.equalsIgnoreCase(i2.symptom());
    }

    public String symptom() {

        assert symptom != null;

        return symptom;
    }

    public void decreaseSeverity(double factor) {

        assert factor >= 0 && factor < 1;

        double decrease = 1 - factor;
        this.severity /= decrease;

        assert severity > 0;
    }
}
