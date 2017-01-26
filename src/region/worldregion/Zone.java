package region.worldregion;

import entities.Person;
import pt.ua.gboard.GBoard;
import region.Location;

import java.awt.*;
import java.util.*;

/**
 * Created by espinha on 1/25/17.
 */
public abstract class Zone {

    protected final GBoard board;
    protected final Point position;

    public Zone(GBoard board, Location location) {

        assert board != null;
        assert location != null;

        this.board = board;
        this.position = location.getPoint();
    }
}
