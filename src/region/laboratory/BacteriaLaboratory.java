package region.laboratory;

import common.Infection;
import common.Specification;
import entities.Bacteria;
import entities.BiologicalEntity;
import region.worldregion.Continent;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rui on 1/24/17.
 */
public class BacteriaLaboratory extends Laboratory {

    public BacteriaLaboratory(Continent continent) {
        super(continent);
    }

    @Override
    public synchronized int develop(BiologicalEntity entity) {

        assert entity != null;
        assert entity.getProductionTime() > 0;

        while (time % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Bacteria bacteria = (Bacteria) entity;

        assert bacteria.getInfection() != null;


        Infection infection = bacteria.getInfection();
        int infectionTime = bacteria.getProductionTime();

        return infection.getSeverity() + new Random().nextInt(infectionTime);
    }


    public synchronized void createBacteria(Specification specification) {
        // todo: review purpose

    }
}
