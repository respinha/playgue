package entities;

import pt.ua.gboard.GBoard;
import region.MedicalInformationCenter;
import region.worldregion.EarthRegion;

import javax.swing.plaf.synth.Region;
import java.util.List;

/**
 * Created by espinha on 1/26/17.
 */
public abstract class Population extends BiologicalEntity {

    protected GBoard board;
    protected MedicalInformationCenter center;
    public Population(GBoard board, EarthRegion region, MedicalInformationCenter center) {

        super(board, region);

        assert center != null;

        this.board = board;
        this.center = center;
    }
}
