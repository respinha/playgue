package entities;

import common.Globals;
import common.Infection;
import region.laboratory.BacteriaLaboratory;
import region.worldregion.EarthZone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by espinha on 1/25/17.
 */
public class Epidemy extends BiologicalEntity implements Runnable {

    private List<Bacteria> bacterias;
    public Epidemy(String name, EarthZone area, BacteriaLaboratory laboratory) {
        super(name, area,laboratory);
    }

    @Override
    public void run() {

        boolean running = true;
        while(running) {
            laboratory.develop(this);
            area.spread(bacterias);

            for(Bacteria bacteria: bacterias) {

                bacteria.olden();   // decrease lifespan

                if (bacteria.lifespan() == 0)
                    bacterias.remove(bacteria);
            }

            running = bacterias.size() > 0;
            Globals.metronome().sync();
        }
        /**
         * Lifecyle:
         * createBacterias()
         * while(conditionToRun) {
         *      area.infect(targetArea);
         *      laboratory.reproduceBacterias(bacterias)
         *
         *      conditionToRun = infectedPopulation > 0 && remaining life > 0
         *      Globals.tick();
         * }
         *
         */

        /*

        while(alive && lifespan > 0) {
            MutableCountry country = Globals.randomCountry(continent);  // TODO: change to random area
            Globals.randomPause(500,2000);
            alive = continent.spreadInfection(this, country.getCODE());

            int development = laboratory.develop(this);
            infection.updateSeverity(development);

            for(Bacteria bacteria: bacterias) {

                Infection infection = bacteria.newInfection();
                //System.out.println(Thread.currentThread().getId() + " contagion: " + infection.getContagion());






                int contagion = infection.getContagion();

                int increase = new Random(productionTime).nextInt(contagion);
                infection.increaseContagion(increase);

                if(increase + 1 > contagion/2) { // greater than half
                    continent.spreadInfectionToBorders(this, country.getCODE());
                }

                //System.out.println("ran all borders");
                productionTime++;
            }
        }*/
    }

    public List<Bacteria> bacterias() {
        return bacterias;
    }

    public void setBacterias(List<Bacteria> bacterias) {
        Collections.copy(bacterias, this.bacterias);
    }
}
