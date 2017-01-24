package entities;

import common.Specification;
import laboratory.Laboratory;
import worldregion.Continent;

/**
 * Created by rui on 1/24/17.
 */
public abstract class BiologicalEntity extends LiveEntity {

    protected final Specification specification;
    protected final Laboratory laboratory;

    public BiologicalEntity(Continent continent, Specification specification, Laboratory laboratory)  {
        super(continent);
        this.specification = specification;
        this.laboratory = laboratory;
    }

    protected void terminate() {
        alive = false;
    }

}
