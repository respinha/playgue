package region.laboratory;

import common.Report;
import entities.BiologicalEntity;
import entities.LiveEntity;
import entities.MedicalEntity;
import entities.Vaccine;
import region.worldregion.Continent;

import java.util.List;
import java.util.Random;

/**
 * Created by rui on 1/24/17.
 */
public class MedicalLaboratory extends Laboratory {

    private int knownBacterias;
    private int developedCures;

    @Override
    public synchronized void develop(BiologicalEntity entity) {

        assert entity != null;

        MedicalEntity medicalEntity = (MedicalEntity) entity;


    }

    public synchronized void inform(Report report) {

        assert report != null;

        // todo: use report
        if(knownBacterias <= developedCures) knownBacterias++;
        notify();

    }
}
