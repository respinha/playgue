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
 * 11/21/16.
 *
 * Objects of this type are created by any Epidemic.
 * Number of objects can multiply at each iteration of the epidemic's lifecycle.
 * Main attributes are the current infection and the bacteria's lifespan.
 */
public class Bacteria {

    private Infection infection;
    private int lifespan = 0;

    /**
     *
     * @param infection
     */
    public Bacteria(Infection infection) {

        assert infection != null;

        this.infection  = infection;
        lifespan = new Random().nextInt(100 - 50) + 50;

        assert lifespan >= 50 && lifespan <= 150;
    }

    public Infection getInfection() {

        assert infection != null;

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
}
