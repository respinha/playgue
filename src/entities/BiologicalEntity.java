package entities;

import common.Specification;
import region.laboratory.Laboratory;
import region.worldregion.Continent;

/**
 * Created by rui on 1/24/17.
 */
public abstract class BiologicalEntity extends LiveEntity {

    protected final Specification specification;
    protected final Laboratory laboratory;
    protected int productionTime;

    public BiologicalEntity(String name, Continent continent, Specification specification, Laboratory laboratory)  {
        super(name, continent);

        assert specification != null;
        assert laboratory != null;

        this.specification = specification;
        this.laboratory = laboratory;

        productionTime = 0;
    }

    public int getProductionTime() {
        return productionTime;
    }

}
