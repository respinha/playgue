package entities;

//import earthRegion.worldregion.RegionSpecification;

import common.Globals;
import common.Infection;
import pt.ua.gboard.GBoard;
import region.laboratory.Laboratory;
import region.worldregion.EarthRegion;
import region.worldregion.EarthZone;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by espinha on 11/21/16.
 */
public class Bacteria extends BiologicalEntity {

    private Infection infection;
    private int lifespan = 0;

    public Bacteria(GBoard board, EarthRegion region) {
        super(board, region);

        lifespan = new Random().nextInt(100 - 50) + 50;
    }

    public Infection getInfection() {
        return infection;
    }

    /*@Override
    public void run() {

        infection = newInfection();

        int i = 0;  // DEBUG

        while(i < 5) {

            System.out.println(Thread.currentThread().getId() + " contagion: " + infection.getContagion());
            MutableCountry country = Globals.randomCountry(continent);

            alive = continent.spreadInfection(this, country.getCODE());

            Globals.randomPause(500,2000);

            int contagion = infection.getContagion();

            int increase = new Random(productionTime).nextInt(contagion);
            infection.increaseContagion(increase);

            if(increase + 1 > contagion/2) { // greater than half
                continent.spreadInfectionToBorders(this, country.getCODE());
            }

            //System.out.println("ran all borders");
            productionTime++;

            int development = laboratory.develop(this);
            infection.updateSeverity(development);

            i++;
        }
    }*/

    public void setInfection(Infection infection) {
        this.infection = infection;
    }

    public int lifespan() {
        return lifespan;
    }

    public void olden() {
        lifespan--;
    }

    @Override
    public void run() {

    }
}
