package entities;


import common.Globals;
import common.Specification;
import region.laboratory.Laboratory;
import region.worldregion.EarthZone;

/**
 * Created by espinha on 11/21/16.
 */
public class Vaccine extends BiologicalEntity {

    private int accuracy;

    public Vaccine(String name, EarthZone area, Specification specification, Laboratory laboratory) {
        super(name, area, specification, laboratory);

        this.accuracy = 1;
    }

    /*@Override
    public void run() {


        while (alive) {

            area.cureInfectedCountries(this);

            Globals.randomPause(500,2000);

            productionTime++;

            this.accuracy = laboratory.develop(this);
        }
    }*/

    public int getAccuracy() {
        return accuracy;
    }
}
