package entities;

//import region.worldregion.RegionSpecification;

import common.Globals;
import common.Infection;
import common.Specification;
import region.laboratory.Laboratory;
import region.worldregion.Continent;
import region.worldregion.MutableCountry;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by espinha on 11/21/16.
 */
public class Bacteria extends BiologicalEntity {

    protected int resistanceDegree;
    private Infection infection;

    public Bacteria(String name, Continent continent, Specification specification, Laboratory laboratory) {
        super(name, continent, specification, laboratory);
    }

    public Infection getInfection() {
        return infection;
    }

    @Override
    public void run() {

        infection = newInfection();

        int i = 0;  // DEBUG

        while(i < 2) {

            MutableCountry country = Globals.randomCountry(continent);

            alive = continent.spreadInfection(this, country.getCODE());

            Globals.randomPause(500,2000);

            int contagion = infection.getContagion();
            if(new Random().nextInt(contagion) + 1 > contagion/2) { // greater than half
                continent.spreadInfectionToBorders(this, country.getCODE());
            }

            productionTime++;

            int development = laboratory.develop(this);
            infection.updateSeverity(development);

            i++;
        }
    }

    private Infection newInfection() {
        int[] syntomCodes = Arrays.copyOfRange(Globals.SYNTOM_CODES, 0, 3);

        return new Infection(syntomCodes);
    }

    @Override
    public boolean equals(Object b2) {

        return this.getName() == ((Bacteria) b2).getName();
    }
}
