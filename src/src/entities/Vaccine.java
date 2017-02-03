package entities;


import common.Infection;

/**
 * A Vaccine which may be used to heal someone and provide immunity to an infection's symptom.
 */
public class Vaccine {

    private String symptom;
    private double versatility;

    /**
     *
     * @param symptom
     */
    public Vaccine(String symptom) {

        assert symptom != null;

        this.symptom = symptom;

        versatility = Math.random() * 0.75;
        assert versatility >= 0;
    }

    /**
     * Override of method equals. Comparison relies on known symptom.
     * @param o
     * @return
     */
    public boolean equals(Object o) {

        assert ((Vaccine) o).symptom() != null;

        return this.symptom.equalsIgnoreCase(symptom);
    }

    /**
     *
     * @return Vaccine's target symptom.
     */
    private String symptom() {

        assert symptom != null;

        return symptom;
    }


    /**
     *
     * @return Vaccine's accuracy in case it cannot fully heal a certain symptom.
     */
    public double getVersatility() {
        return versatility;
    }

    /**
     * Currently not being used.
     */
    public void develop() {

        double upgrade = Math.random() * 0.2;
        if(versatility + upgrade < 1.0)
            versatility += upgrade;
    }
}
