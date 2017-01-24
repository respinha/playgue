package entities;


import common.Specification;
import laboratory.Laboratory;
import worldregion.Continent;

/**
 * Created by espinha on 11/21/16.
 */
public class Cure extends BiologicalEntity {
    public Cure(Continent continent, Specification specification, Laboratory laboratory) {
        super(continent, specification, laboratory);
    }

    @Override
    public void run() {

    }
}
