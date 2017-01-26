package entities;

import common.Specification;
import region.laboratory.Laboratory;
import region.worldregion.EarthZone;

/**
 * Created by rui on 1/24/17.
 */
public abstract class BiologicalEntity extends LiveEntity {

    protected final Laboratory laboratory;

    public BiologicalEntity(String name, EarthZone area, Laboratory laboratory)  {
        super(name, area);

        assert laboratory != null;

        this.laboratory = laboratory;

    }
}
