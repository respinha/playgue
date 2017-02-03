package entities;

import pt.ua.gboard.GBoard;
import region.MedicalInformationCenter;
import region.laboratory.MedicalLaboratory;
import region.worldregion.EarthRegion;

/**
 * 1/28/17.
 *
 * Subtyped abstraction of population which interacts with the MedicalLaboratory.
 */
public abstract class MedicalTeam extends Population {

    protected MedicalLaboratory laboratory;
    public MedicalTeam(GBoard board, EarthRegion region, MedicalInformationCenter center, MedicalLaboratory laboratory) {
        super(board, region, center);

        assert laboratory != null;

        this.laboratory = laboratory;
    }
}
