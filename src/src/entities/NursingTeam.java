package entities;

import common.Globals;
import common.Infection;
import pt.ua.concurrent.CThread;
import pt.ua.gboard.GBoard;
import region.MedicalInformationCenter;

import region.laboratory.MedicalLaboratory;
import region.worldregion.EarthRegion;

import java.util.*;

/**
 * 1/25/17.
 *
 * Inherits from MedicalTeam.
 * Lifecycle:
 *      watch an EarthRegion until some problem is detected
 *      request Vaccine for each new symptom that is detected
 *      vaccinate people in a certain area with the acquired vaccines.
 */
public class NursingTeam extends MedicalTeam {

    private Map<String, Vaccine> vaccines;
    private Set<String> knownInfections;

    /**
     * Constructor.
     * @param board
     * @param region
     * @param center
     * @param laboratory
     */
    public NursingTeam(GBoard board, EarthRegion region, MedicalInformationCenter center, MedicalLaboratory laboratory) {
        super(board, region, center,laboratory);

        knownInfections = new LinkedHashSet<>();
        vaccines = new HashMap<>();
    }

    /**
     * Add an infection's symtpom to the list of known symptoms, if it doesn't exist
     * @param infection
     */
    public void newInfection(Infection infection) {

        assert infection != null;

        int prevSize = knownInfections.size();

        //System.out.println(infection);
        knownInfections.add(infection.symptom());

        assert knownInfections.size() >= prevSize;
    }

    /**
     *
     * @return Vaccines mapped by symtpoms
     */
    public Map<String, Vaccine> vaccines() {

        assert vaccines != null;
        return vaccines;
    }

    /**
     *
     * @return Known infections.
     */
    public Set<String> knownInfections() {
        return knownInfections;
    }

    /**
     * Lifecycle
     */
    @Override
    public void arun() {


        //System.out.println("Starting Medical Team lifecycle.");

        int time = 0;
        while(true) {

            center.watchOver(this);

            laboratory.acquireVaccines(this);

            region.vaccinate(this);

            if(time++ == 10) {

                if(Globals.evenRandom()) {

                    new CThread(new NursingTeam(board,region, center, laboratory)).start();
                }
            }
            //if(!required) laboratory.close();

            //System.out.println("End of nursing cycle");
            Globals.metronome().sync();
        }
    }

    /**
     * Add a new vaccine, developed by the research team.
     * Vaccines are always attached to a known symptom.
     * @param symptom
     * @param vaccine
     */
    public void newVaccine(String symptom, Vaccine vaccine) {
        vaccines.put(symptom, vaccine);
    }
}