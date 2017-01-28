package entities;


import common.Infection;

/**
 * Created by espinha on 11/21/16.
 */
public class Vaccine {

    private final Infection infection;
    private double versatility;

    public Vaccine(Infection infection) {

        assert infection != null;

        this.infection = infection;

        versatility = Math.random() * 0.75;
    }

    public boolean equals(Object o) {

        assert ((Vaccine) o).infection() != null;

        System.out.println(infection.syntom());
        System.out.println(((Vaccine) o).infection().syntom());
        return this.infection.equals(((Vaccine) o).infection());
    }

    public Infection infection() {
        return infection;
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
