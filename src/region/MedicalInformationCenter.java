package region;

import common.Infection;
import entities.*;
import pt.ua.gboard.GBoard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by espinha on 11/21/16.
 */

// Shared
public class MedicalInformationCenter {

    // organization

    private static GBoard board;
    private boolean statusUpdated;

    private Set<Infection> infections;

    private BufferedWriter writer;
    public MedicalInformationCenter(GBoard board) throws IOException {
        this.board = board;

        statusUpdated = false;

        infections = new LinkedHashSet<>();
        
        writer = new BufferedWriter(new FileWriter("log.txt"));
    }

    public synchronized void inform(Civilization civilization) throws IOException {

        civilization.people().forEach(
                inhabitants -> inhabitants.people().forEach(
                        person -> {
                            if(person.isInfected())
                                infections.add(person.getInfection());
                        }
                )
        );

        statusUpdated = true;
        notify();
    }

    public synchronized void watchOver(NursingTeam team) {

        /*try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        
        while (!statusUpdated) {
            
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Woke up a doctor");

        for(Infection infection: infections) {

            team.newInfection(infection);
        }

        statusUpdated = false;

    }
}
