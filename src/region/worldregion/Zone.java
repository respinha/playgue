package region.worldregion;

import pt.ua.gboard.GBoard;

import java.awt.*;

/**
 * Created by espinha on 1/25/17.
 */
public abstract class Zone extends WorldRegion {

    protected final Point position;
    protected final Location location;

    public Zone(GBoard board, Location location) {

        super(board);

        assert location != null;

        this.location = location;
        this.position = location.getPoint();
    }
}
