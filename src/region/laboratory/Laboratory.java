package region.laboratory;

import entities.Bacteria;
import entities.BiologicalEntity;
import region.worldregion.Continent;
import region.worldregion.MutableCountry;

/**
 * Created by rui on 1/23/17.
 * Shared region
 * Receives updates from Bacterias and Cures
 * Provides information to status reporter
 */

public abstract class Laboratory {

    protected Continent continent;
    protected
    int time;

    public Laboratory(Continent continent) {
        this.continent = continent;
        time = 0;
    }

    public abstract int develop(BiologicalEntity entity);

    public synchronized void newDay() {
        time++;
        if(time % 2 == 0)
            notifyAll();
    }
}
