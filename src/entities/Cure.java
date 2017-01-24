package entities;


import common.Globals;
import common.Specification;
import laboratory.Laboratory;
import worldregion.Continent;

/**
 * Created by espinha on 11/21/16.
 */
public class Cure extends BiologicalEntity {

    private int cureAccuracy;   // from 1 to 10
    private int accuracy;

    public Cure(Continent continent, Specification specification, Laboratory laboratory) {
        super(continent, specification, laboratory);

        this.cureAccuracy = 1;
    }

    @Override
    public void run() {


        while (alive) {

            continent.cureInfectedCountries(this);

            Globals.randomPause(500,2000);

            productionTime++;
            laboratory.develop(this);
        }
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void updateAccuracy(int accuracy) {

        this.cureAccuracy = accuracy;
    }
}
