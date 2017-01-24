package laboratory;

import entities.Bacteria;
import entities.BiologicalEntity;
import worldregion.Continent;
import worldregion.MutableCountry;

/**
 * Created by rui on 1/23/17.
 * Shared region
 * Receives updates from Bacterias and Cures
 * Provides information to status reporter
 */

public abstract class Laboratory {

    protected Continent continent;

    public Laboratory(Continent continent) {
        this.continent = continent;

    }

    public abstract void develop(BiologicalEntity entity);
    protected synchronized MutableCountry[] getInfectedCountries(Bacteria bacteria) {
        return null;
    }
}
