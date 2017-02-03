package entities;

import pt.ua.gboard.GBoard;
import region.MedicalInformationCenter;
import region.laboratory.MedicalLaboratory;
import region.worldregion.EarthRegion;

/**
 * 1/28/17.
 */
public class ResearchTeam extends MedicalTeam{


    public ResearchTeam(GBoard board, EarthRegion region, MedicalInformationCenter center, MedicalLaboratory laboratory) {
        super(board, region, center,laboratory);
    }

    /*@Override
    public void run() {


    }*/

    @Override
    public void arun() {
        laboratory.develop(this);
    }
}
