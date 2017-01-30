package entities;

import common.Globals;
import common.Infection;
import pt.ua.gboard.GBoard;
import region.MedicalInformationCenter;

import region.laboratory.MedicalLaboratory;
import region.worldregion.EarthRegion;

import java.util.*;

/**
 * Created by espinha on 1/25/17.
 */
public class NursingTeam extends MedicalTeam {

    private Map<String, Vaccine> vaccines;
    private Set<String> knownInfections;


    public NursingTeam(GBoard board, EarthRegion region, MedicalInformationCenter center, MedicalLaboratory laboratory) {
        super(board, region, center,laboratory);

        knownInfections = new LinkedHashSet<>();
        vaccines = new HashMap<>();
    }

    public void newInfection(Infection infection) {

        assert infection != null;

        knownInfections.add(infection.symptom());
    }

    public Map<String, Vaccine> vaccines() {
        return vaccines;
    }

    public Set<String> knownInfections() {
        return knownInfections;
    }

    @Override
    public void run() {

        System.out.println("Starting Medical Team lifecycle.");

        boolean required = true;
        while(required) {

            center.watchOver(this);

            laboratory.acquireVaccines(this);

            region.vaccinate(this);
            if(!required) laboratory.close();

            System.out.println("End of nursing cycle");
            Globals.metronome().sync();
        }

        /**
         * Lifecyle:
         * createVaccines()
         * while(conditionToRun) {
         *      continent.vaccinatetargetArea);
         *      laboratory.develop(vaccines)
         *
         *      conditionToRun = infectedPopulation > 0 && remaining life > 0
         *      Globals.tick();
         * }
         *
         *
         */
    }

    public void newVaccine(String symptom, Vaccine vaccine) {
        vaccines.put(symptom, vaccine);
    }
}