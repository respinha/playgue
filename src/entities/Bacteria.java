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
 *
 * Objects of this type are created by any @link{Epidemic}

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

    public void setInfection(Infection infection) {

        assert infection != null;

        this.infection = infection;
    }

    public int lifespan() {

        assert lifespan >= 0;
        return lifespan;
    }

    public void olden() {
        if(lifespan > 0)
            lifespan--;

        assert lifespan >= 0;
    }

    @Override
    public void run() {

    }
}
