package region.laboratory;

import entities.BiologicalEntity;
import pt.ua.gboard.GBoard;


/**
 * Created by rui on 1/23/17.
 * Shared earthRegion
 * Receives updates from Bacterias and Cures
 * Provides information to status reporter
 */

public abstract class Laboratory {

    protected final GBoard board;

    public Laboratory(GBoard board) {
        this.board = board;
    }

    public abstract void develop(BiologicalEntity entity);
}
