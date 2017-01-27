package region.laboratory;

import common.Report;
import entities.Vaccine;
import pt.ua.gboard.GBoard;

import java.util.List;

/**
 * Created by rui on 1/24/17.
 */
public class MedicalLaboratory extends Laboratory {

    private int knownBacterias;
    private int developedCures;

    public MedicalLaboratory(GBoard board) {
        super(board);
    }

    public synchronized void inform(Report report) {

        assert report != null;

        // todo: use report
        if(knownBacterias <= developedCures) knownBacterias++;
        notify();

    }

    public List<Vaccine> developVaccine(List<Vaccine> vaccines) {

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return vaccines;
    }
}
