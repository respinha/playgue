package region;

import common.Infection;
import entities.*;
import pt.ua.concurrent.CObject;
import pt.ua.gboard.GBoard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Shared region in which population notifies the existing NursingTeams of all epidemic updates in EarthRegion.
 */

public class MedicalInformationCenter extends CObject {

    private static GBoard board;
    private boolean statusUpdated;

    private Set<Infection> infections;

    /**
     * Constructor.
     * @param board
     * @throws IOException
     */
    public MedicalInformationCenter(GBoard board) throws IOException {

        assert board != null;

        this.board = board;

        statusUpdated = false;

        infections = new LinkedHashSet<>();

        assert !statusUpdated;
    }

    /**
     * Method invoked by Civilization to update list of known infections and awake all NursingTeams.
     * @param civilization
     * @throws IOException
     */
    public synchronized void inform(Civilization civilization) {

        assert civilization != null;

        //System.out.println("informing");

        civilization.people().forEach(
                inhabitants -> {
                    if(inhabitants.infected()) {
                        for(Person person: inhabitants.people()) {
                            if(person.isInfected())
                                infections.add(person.getInfection());
                        }
                    }
                }
        );

        statusUpdated = true;
        signal();
    }

    /**
     * Method invoked by all NursingTeams in which they await for updates from Civilization.
     * @param team
     */
    public synchronized void watchOver(NursingTeam team) {

        //System.out.println(team != null);
        while (!statusUpdated) {
            await();
        }

        //System.out.println("Woke up a doctor");

        for(Infection infection: infections) {

            team.newInfection(infection);
        }

        statusUpdated = false;
    }
}
