package entities;

import common.Globals;
import common.Report;
import region.MedicalInformationCenter;
import region.laboratory.BacteriaLaboratory;
import region.laboratory.MedicalLaboratory;
import region.worldregion.EarthZone;
import region.worldregion.Zone;

import java.util.Collections;
import java.util.List;

/**
 * Created by espinha on 1/25/17.
 */
public class MedicalEntity extends BiologicalEntity implements Runnable {

    private List<Vaccine> vaccines = null;
    private Report latestReport;
    private MedicalInformationCenter center;

    public MedicalEntity(String name, EarthZone area, MedicalLaboratory laboratory, MedicalInformationCenter center) {
        super(name, area, laboratory);
        this.center = center;
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
            laboratory.develop(this);

            area.vaccinate(vaccines);

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
