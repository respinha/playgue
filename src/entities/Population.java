package entities;

import pt.ua.gboard.GBoard;
import region.MedicalInformationCenter;
import region.worldregion.EarthRegion;

import javax.swing.plaf.synth.Region;
import java.util.List;

/**
 * 1/26/17.
 *
 * Active entity subtype. May be inherited from any active entity that represents a group of people.
 */
public abstract class Population extends LiveEntity {

    protected MedicalInformationCenter center;

    /**
     *
     * @param board
     * @param region
     * @param center
     */
    public Population(GBoard board, EarthRegion region, MedicalInformationCenter center) {

        super(board, region);

        assert center != null;

        this.center = center;
    }
}
