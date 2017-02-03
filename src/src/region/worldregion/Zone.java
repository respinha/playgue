package region.worldregion;

import pt.ua.gboard.GBoard;

import java.awt.*;

/**
 * Zone abstraction, as a Region's zone can be a specification of WorldRegion.
 * */
public abstract class Zone extends WorldRegion {

    protected final Point position;
    protected final Location location;

    /**
     *
     * @param board
     * @param location
     */
    public Zone(GBoard board, Location location) {

        super(board);

        assert location != null;

        this.location = location;
        this.position = location.getPoint();
    }
}
