package entities;

import pt.ua.gboard.GBoard;
import region.worldregion.EarthRegion;

/**
 * Created by espinha on 1/26/17.
 */
public abstract class BiologicalEntity extends LiveEntity {

    protected EarthRegion region;

    public BiologicalEntity(GBoard board, EarthRegion region) {
        super(board);

        assert region != null;

        this.region = region;

    }

    public EarthRegion region() {
        return region;
    }
}
