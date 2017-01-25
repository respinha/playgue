package region.laboratory;

import common.Report;
import entities.BiologicalEntity;
import entities.Cure;
import region.worldregion.Continent;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rui on 1/24/17.
 */
public class MedicalLaboratory extends Laboratory {

    private int knownBacterias;
    private int developedCures;

    public MedicalLaboratory(Continent continent) {
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

        Cure cure = (Cure) entity;

        assert cure.getAccuracy() >= 1;

        int cureTime = cure.getProductionTime();

        int accuracy = cure.getAccuracy() + new Random().nextInt(cureTime);

        return accuracy;
    }


    public synchronized Cure[] getCurrentCures() {
        return null;
    }

    public synchronized void inform(Report report) {

        assert report != null;

        while (time % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // todo: use report
        if(knownBacterias <= developedCures) knownBacterias++;
        notify();

    }

    public synchronized void studying(Cure cure) {

        while(knownBacterias <= developedCures || time % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        developedCures++;
    }
}
