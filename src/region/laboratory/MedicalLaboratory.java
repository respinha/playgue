package region.laboratory;

import entities.Vaccine;
import pt.ua.gboard.GBoard;

import java.util.Map;

/**
 * Created by rui on 1/24/17.
 */
public class MedicalLaboratory extends Laboratory {

    private boolean newTasks;

    public MedicalLaboratory(GBoard board) {
        super(board);

        newTasks = false;
    }

    public synchronized Map<String, Vaccine> acquireVaccines(Map<String, Vaccine> vaccines) {


        for(Vaccine vaccine: vaccines.values()) {
            System.out.println(vaccine.infection().syntom());
            System.out.println(vaccine.getVersatility());

            vaccine.develop();
        }
        /*try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        return vaccines;
    }

    /*public synchronized boolean waitForNewTasks() {
        while(!newTasks) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return false;
    }*/
}
