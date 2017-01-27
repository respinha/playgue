package region.laboratory;

import common.Infection;
import entities.Bacteria;
import entities.BiologicalEntity;
import entities.Epidemic;
import pt.ua.gboard.GBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rui on 1/24/17.
 */
public class BacteriaLaboratory extends Laboratory {
    public BacteriaLaboratory(GBoard board) {
        super(board);
    }

    public synchronized void develop(BiologicalEntity entity) {

        assert entity != null;

        /*try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        Epidemic epidemic = (Epidemic) entity;

        List<Bacteria> bacterias = epidemic.bacterias();
        
        if(bacterias == null) {
            bacterias = createBacterias(epidemic);
            epidemic.setBacterias(bacterias);
            return;
        }
        
        for (Bacteria bacteria: bacterias) {

            Infection infection = bacteria.getInfection();

            assert infection != null;

            int severity = infection.getSeverity();
            int lifespan = bacteria.lifespan();
            infection.updateSeverity(severity + new Random().nextInt(lifespan));
        }
        
        List<Bacteria> newBacterias = createBacterias(epidemic);
        bacterias.addAll(newBacterias);

        epidemic.setBacterias(bacterias);
    }

    private List<Bacteria> createBacterias(Epidemic epidemic) {
        List<Bacteria> bacterias = new ArrayList<>();
        Infection infection = new Infection("headaches");

        int max = new Random().nextInt(1000 - 500) + 500;
        for(int i = 0; i < max; i++) {


            Bacteria b = new Bacteria(board, epidemic.region());
            b.setInfection(infection);

            bacterias.add(b);
        }

        return bacterias;
    }
}
