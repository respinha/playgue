package region.laboratory;

import common.Infection;
import common.Specification;
import entities.Bacteria;
import entities.BiologicalEntity;
import entities.Epidemy;
import entities.LiveEntity;
import region.worldregion.Continent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rui on 1/24/17.
 */
public class BacteriaLaboratory extends Laboratory {

    @Override
    public synchronized void develop(BiologicalEntity entity) {

        assert entity != null;

        Epidemy epidemy = (Epidemy) entity;

        List<Bacteria> bacterias = epidemy.bacterias();
        
        if(bacterias == null) {
            bacterias = createBacterias(epidemy);
            epidemy.setBacterias(bacterias);
            return;
        }
        
        for (Bacteria bacteria: bacterias) {

            Infection infection = bacteria.getInfection();

            assert infection != null;

            int severity = infection.getSeverity();
            int lifespan = bacteria.lifespan();
            infection.updateSeverity(severity + new Random().nextInt(lifespan));
        }
        
        List<Bacteria> newBacterias = createBacterias(epidemy);
        bacterias.addAll(newBacterias);

        epidemy.setBacterias(bacterias);
    }

    private List<Bacteria> createBacterias(Epidemy epidemy) {
        List<Bacteria> bacterias = new ArrayList<>();
        Infection infection = new Infection();

        int max = new Random().nextInt(1000 - 500) + 500;
        for(int i = 0; i < max; i++) {


            Bacteria b = new Bacteria(epidemy.getName() + " bacteria", null, null);
            b.setInfection(infection);

            bacterias.add(b);
        }

        return bacterias;
    }
}
