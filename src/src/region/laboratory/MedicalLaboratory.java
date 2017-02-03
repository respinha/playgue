package region.laboratory;

import com.sun.org.apache.regexp.internal.RE;
import common.Globals;
import entities.LiveEntity;
import entities.NursingTeam;
import entities.ResearchTeam;
import entities.Vaccine;
import pt.ua.gboard.GBoard;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * Subtype of Laboratory shared by different Medical Teams.
 * NursingTeam and ResearchTeam communicate in a bidirecional producer-consumer system, implemented with two {@link TransferQueue}.
 * */
public class MedicalLaboratory extends Laboratory {

    //private Map<String, Vaccine> labVaccines;
    private TransferQueue<String> pendingInfections;
    private TransferQueue<Vaccine> pendingVaccines;

    private boolean missionCompleted;

    public MedicalLaboratory(GBoard board) {
        super(board);

        missionCompleted = false;

        pendingVaccines = new LinkedTransferQueue<>();
        pendingInfections = new LinkedTransferQueue<>();
    }

    //public synchronized Map<String, Vaccine> acquireVaccines(NursingTeam nursingTeam) {
    public void acquireVaccines(NursingTeam nursingTeam) {

        assert nursingTeam != null;
        // reporting known infections to the research team

        try {
            for(String symptom: nursingTeam.knownInfections()) {
                pendingInfections.transfer(symptom);
                nursingTeam.newVaccine(symptom, pendingVaccines.take());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void develop(LiveEntity liveEntity) {

        assert liveEntity != null;

        ResearchTeam researchTeam = (ResearchTeam) liveEntity;

        try {

            while (!missionCompleted) {
                String newSymptom = pendingInfections.take();
                Vaccine vaccine = new Vaccine(newSymptom);

                Globals.randomPause(300,1000);

                pendingVaccines.transfer(vaccine);
            }
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    public void close() {
        missionCompleted = true;

        try {
            pendingVaccines.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
