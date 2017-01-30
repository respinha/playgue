package entities;

import pt.ua.gboard.GBoard;
import region.MedicalInformationCenter;
import region.laboratory.MedicalLaboratory;
import region.worldregion.EarthRegion;

/**
 * Created by espinha on 1/28/17.
 */
public abstract class MedicalTeam extends Population {

    protected MedicalLaboratory laboratory;
    public MedicalTeam(GBoard board, EarthRegion region, MedicalInformationCenter center, MedicalLaboratory laboratory) {
        super(board, region, center);

        assert laboratory != null;

        this.laboratory = laboratory;
    }
}
