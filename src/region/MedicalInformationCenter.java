package region;

import common.Infection;
import common.Report;
import entities.Civilization;
import entities.Inhabitants;
import entities.Person;
import pt.ua.gboard.GBoard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private BufferedWriter writer;
    public MedicalInformationCenter(GBoard board) throws IOException {
        this.board = board;

        noWorries = true;

        writer = new BufferedWriter(new FileWriter("log.txt"));
    }

    public synchronized void inform(Civilization civilization) throws IOException {

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //if(noWorries) {
        noWorries = false;
        notify();
        //}

        for(Inhabitants inhabitants: civilization.people()) {
            for(Person person: inhabitants.people()) {
                infections.add(person.getInfection());
            }
        }
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

        noWorries = true;

        return new Report(infections);
    }
}
