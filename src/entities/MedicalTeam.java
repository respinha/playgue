package entities;

import common.Globals;
import common.Report;
import pt.ua.gboard.GBoard;
import region.MedicalInformationCenter;
import region.laboratory.BacteriaLaboratory;
import region.laboratory.MedicalLaboratory;
import region.worldregion.EarthRegion;
import region.worldregion.EarthZone;
import region.worldregion.Zone;

import java.util.Collections;
import java.util.List;

/**
 * Created by espinha on 1/25/17.
 */
public class MedicalTeam extends Population {

    private List<Vaccine> vaccines = null;
    private Report latestReport;
    private MedicalLaboratory laboratory;

    public MedicalTeam(GBoard board, EarthRegion region, MedicalInformationCenter center, MedicalLaboratory laboratory) {
        super(board, region, center);
    }


    public void setVaccines(List<Vaccine> vaccines) {
        Collections.copy(vaccines, this.vaccines);
    }

    public List<Vaccine> vaccines() {
        return vaccines;
    }

    @Override
    public void run() {

        boolean running = true;

        while(running) {

            latestReport = center.watchOver();

            // TODO: if vaccines() == null then create()
            vaccines = laboratory.developVaccine(vaccines);

            region.vaccinate(vaccines);

            running = latestReport != null;
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
