package region.laboratory;

import common.Report;
import entities.Bacteria;
import entities.BiologicalEntity;
import entities.LiveEntity;
import region.worldregion.Continent;
import region.worldregion.MutableCountry;
import region.worldregion.Zone;

import java.util.List;

/**
 * Created by rui on 1/23/17.
 * Shared region
 * Receives updates from Bacterias and Cures
 * Provides information to status reporter
 */

public abstract class Laboratory {

    public abstract void develop(BiologicalEntity entity);
}
