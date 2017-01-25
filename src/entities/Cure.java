package entities;


import common.Globals;
import common.Specification;
import region.laboratory.Laboratory;
import region.worldregion.Continent;

/**
 * Created by espinha on 11/21/16.
 */
public class Cure extends BiologicalEntity {

    private int cureAccuracy;   // from 1 to 10
    private int accuracy;

    public Cure(String name, Continent continent, Specification specification, Laboratory laboratory) {
        super(name, continent, specification, laboratory);

        this.cureAccuracy = 1;
    }

    @Override
    public void run() {


        while (alive) {

            continent.cureInfectedCountries(this);

            Globals.randomPause(500,2000);

            productionTime++;

            this.accuracy = laboratory.develop(this);
        }
    }

    public int getAccuracy() {
        return accuracy;
    }
}
