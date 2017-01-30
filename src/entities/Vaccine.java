package entities;


import common.Infection;

/**
 * Created by espinha on 11/21/16.
 */
public class Vaccine {

    //private final Infection infection;
    private String symptom;
    private double versatility;

    public Vaccine(String symptom) {

        assert symptom != null;

        this.symptom = symptom;

        versatility = Math.random() * 0.75;
    }

    public boolean equals(Object o) {

        assert ((Vaccine) o).symptom() != null;

        return this.symptom.equalsIgnoreCase(symptom);
    }

    private String symptom() {
        return symptom;
    }


    public double getVersatility() {
        return versatility;
    }

    public void develop() {

        double upgrade = Math.random() * 0.2;
        if(versatility + upgrade < 1.0)
            versatility += upgrade;
    }
}
