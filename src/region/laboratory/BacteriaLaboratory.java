package region.laboratory;

import common.Infection;
import entities.Bacteria;
import entities.BiologicalEntity;
import entities.Epidemic;
import pt.ua.gboard.GBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

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
        Vector<Bacteria> bacterias = epidemic.bacterias();


        if(bacterias == null) {
            bacterias = createBacterias(epidemic, true);
            epidemic.setBacterias(bacterias);
            return;
        }
        
        for (Bacteria bacteria: bacterias) {

            Infection infection = bacteria.getInfection();

            int severity = infection.getSeverity();

            int increase = severity > 6 ? severity/3 : severity+1;
            //System.out.println(increase);

            infection.updateSeverity(severity + ThreadLocalRandom.current().nextInt(increase));
        }

        /*try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Vector<Bacteria> newBacterias = createBacterias(epidemic,false);
        bacterias.addAll(newBacterias);

        epidemic.setBacterias(bacterias);
    }

    private Vector<Bacteria> createBacterias(Epidemic epidemic, boolean initial) {

        Vector<Bacteria> bacterias = new Vector<>();

        int bound = initial ? 150 : 50;
        int max = new Random().nextInt(bound - bound/2) + bound/2;
        for(int i = 0; i < max; i++) {

            Infection infection = new Infection();
            //System.out.println(infection.getSeverity());

            Bacteria b = new Bacteria(board, epidemic.region());
            b.setInfection(infection);

            bacterias.add(b);
        }

        return bacterias;
    }
}
