package region.laboratory;

import common.Report;
import entities.Bacteria;
import entities.BiologicalEntity;
import entities.LiveEntity;
import pt.ua.gboard.GBoard;
import region.worldregion.Zone;

import java.util.List;

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
    //public abstract void develop(BiologicalEntity entity);
}
