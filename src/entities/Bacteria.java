package entities;

//import worldregion.RegionSpecification;

import common.Globals;
import common.Infection;
import common.Specification;
import laboratory.Laboratory;
import worldregion.Continent;
import worldregion.MutableCountry;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by espinha on 11/21/16.
 */
public class Bacteria extends BiologicalEntity {

    protected int resistanceDegree;
    private Infection infection;

    public Bacteria(Continent continent, Specification specification, Laboratory laboratory) {
        super(continent, specification, laboratory);
    }

    public Infection getInfection() {
        return infection;
    }

    public void setInfection(Infection infection) {
        this.infection = infection;
    }

    public int getResistanceDegree() {
        return resistanceDegree;
    }

    public void updateResistanceDegree(int factor) {
        this.resistanceDegree += factor;
    }


    @Override
    public void run() {

        infection = newInfection();
        while(alive) {

            MutableCountry country = Globals.randomCountry(continent);

            alive = continent.spreadInfection(infection, country.getCODE());

            Globals.randomPause(500,2000);

            int contagion = infection.getContagion();
            if(ThreadLocalRandom.current().nextInt(contagion) + 1 > contagion/2) { // greater than half
                continent.spreadInfectionToBorders(newInfection(), country.getCODE());
            }

            productionTime++;

            laboratory.develop(this);
        }
    }

    private Infection newInfection() {
        int[] syntomCodes = Arrays.copyOfRange(Globals.SYNTOM_CODES, 0, 3);

        return new Infection(syntomCodes);
    }
}
