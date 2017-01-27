package entities;

import pt.ua.gboard.GBoard;
import region.worldregion.EarthRegion;

/**
 * Created by rui on 1/24/17.
 */
public abstract class LiveEntity implements Runnable {

    protected final GBoard board;

    public LiveEntity(GBoard board) {
        this.board = board;
    }
}
