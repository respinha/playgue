package region.laboratory;

import common.Globals;
import common.Infection;
import entities.Bacteria;
import entities.Epidemic;
import entities.LiveEntity;
import pt.ua.gboard.GBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Subtype of laboratory shared by epidemics.
 * Currently only one public method is available (develop()).
 */
public class BacteriaLaboratory extends Laboratory {

    /**
     *
     * @param board
     */
    public BacteriaLaboratory(GBoard board) {
        super(board);
    }


    @Override
    public synchronized void develop(LiveEntity entity) {

        assert entity != null;


        Epidemic epidemic = (Epidemic) entity;
        Vector<Bacteria> bacterias = epidemic.bacterias();

        if(bacterias == null) {
            bacterias = createBacterias();
            epidemic.setBacterias(bacterias);
            return;
        }
        
        /*for (Bacteria bacteria: bacterias) {

            Infection infection = bacteria.getInfection();

            double severity = infection.getSeverity();

            double increase = severity > 6 ? severity/3 : severity+1;
            infection.updateSeverity(severity + ThreadLocalRandom.current().nextDouble() * increase);
        }*/

        epidemic.setBacterias(bacterias);

        assert epidemic.bacterias().size() > 0;
    }

    /**
     * Create a set of new bacterias with a random symptom.
     * @return new vector of Bacterias.
     */
    private Vector<Bacteria> createBacterias() {

        Vector<Bacteria> bacterias = new Vector<>();

        int bound = 150;
        int max = new Random().nextInt(bound - bound/2) + bound/2;

        Infection infection = new Infection();

        for(int i = 0; i < max; i++) {

            // Globals.randomPause(500,1500);

            Bacteria b = new Bacteria(infection);
            bacterias.add(b);
        }

        assert bacterias.size() > 0;

        return bacterias;
    }
}
