package laboratory;

import entities.BiologicalEntity;
import entities.Cure;
import worldregion.Continent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rui on 1/24/17.
 */
public class MedicalLaboratory extends Laboratory {

    public MedicalLaboratory(Continent continent) {
        super(continent);
    }

    @Override
    public void develop(BiologicalEntity entity) {

        assert entity != null;
        assert entity.getProductionTime() > 0;

        Cure cure = (Cure) entity;

        assert cure.getAccuracy() >= 1;

        int cureTime = cure.getProductionTime();

        int accuracy = cure.getAccuracy() + ThreadLocalRandom.current().nextInt(cureTime);

        cure.updateAccuracy(accuracy);
    }


    public synchronized Cure[] getCurrentCures() {
        return null;
    }
}
