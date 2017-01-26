package region;

import common.Infection;
import common.Report;
import entities.Person;
import entities.Population;
import pt.ua.gboard.GBoard;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by espinha on 11/21/16.
 */

// Shared
public class MedicalInformationCenter {

    // organization

    private static GBoard board;
    private boolean noWorries;

    private Set<Infection> infections;

    public MedicalInformationCenter(GBoard board) {
        this.board = board;

        noWorries = true;
    }

    public synchronized void inform(Population population) {

        if(population.hasInfectedPeople())
            noWorries = false;

        for(Person p: population.people()) {

            infections.add(p.getInfection());
        }

        // print data to GBoard
        notifyAll();
    }

    public synchronized Report watchOver() {

        while (noWorries) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return new Report(infections);
    }
}
