package region;

import common.Infection;
import common.Report;
import entities.Civilization;
import entities.Person;
import pt.ua.gboard.GBoard;

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

    public synchronized void inform(Civilization civilization) {
/*
        if(civilization.hasInfectedPeople())
            noWorries = false;

        for(Person p: civilization.people()) {

            infections.add(p.getInfection());
        }

        // print data to GBoard
        notifyAll();*/
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
