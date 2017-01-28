package entities;

import common.Globals;
import common.Infection;
import pt.ua.gboard.GBoard;
import region.MedicalInformationCenter;

import region.laboratory.MedicalLaboratory;
import region.worldregion.EarthRegion;
import region.worldregion.EarthZone;

import java.util.*;

/**
 * Created by espinha on 1/25/17.
 */
public class NursingTeam extends MedicalTeam {

    private Map<String, Vaccine> vaccines = null;

    public NursingTeam(GBoard board, EarthRegion region, MedicalInformationCenter center, MedicalLaboratory laboratory) {
        super(board, region, center,laboratory);

        vaccines = new HashMap<>();
    }

    public void newInfection(Infection infection) {

        assert infection != null;

        if(vaccines.get(infection.syntom()) == null) {
            Vaccine vaccine = new Vaccine(infection);
            vaccines.put(infection.syntom(),vaccine);
        }
    }

    public Map<String, Vaccine> vaccines() {
        return vaccines;
    }

    @Override
    public void run() {

        System.out.println("Starting Medical Team lifecycle.");

        boolean required = true;
        while(required) {

            //region.test();

            center.watchOver(this);

            vaccines = laboratory.acquireVaccines(vaccines);

            required = region.vaccinate(this);

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

}