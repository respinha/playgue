package entities;


import common.Infection;
import pt.ua.gboard.GBoard;
import region.laboratory.Laboratory;
import region.worldregion.EarthRegion;
import region.worldregion.EarthZone;

/**
 * Created by espinha on 11/21/16.
 */
public class Vaccine extends BiologicalEntity {

    private final Infection infection;
    private int accuracy;

    public Vaccine(GBoard board, EarthRegion region, Infection infection) {
        super(board, region);

        assert infection != null;

        this.infection = infection;
    }

    public void heal(Person person) {

        if(!infection.equals(person.getInfection())) {

            // todo: implement
        }
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

    @Override
    public void run() {

    }
}
