package laboratory;

import entities.BiologicalEntity;
import entities.Cure;
import worldregion.Continent;

/**
 * Created by rui on 1/24/17.
 */
public class MedicalLaboratory extends Laboratory {

    private double cureAccuracy;        // percentage

    public MedicalLaboratory(Continent continent) {
        super(continent);
    }

    @Override
    public void develop(BiologicalEntity entity) {
        Cure cure = (Cure) entity;
    }


    public synchronized Cure[] getCurrentCures() {
        return null;
    }

    public double getCureAccuracy(Cure cure) {
        return cureAccuracy;
    }

    public void developCure(double accuracy) {
        this.cureAccuracy += accuracy;
    }
}
