package entities;

import pt.ua.concurrent.CRunnable;
import pt.ua.gboard.GBoard;
import region.worldregion.EarthRegion;

/**
 * Created by rui on 1/24/17.
 *
 * Abstraction for active entities.
 *
 */
public abstract class LiveEntity extends CRunnable {

    protected final GBoard board;
    protected final EarthRegion region;

    /**
     *
     * @param board
     * @param region
     */
    public LiveEntity(GBoard board, EarthRegion region) {

        assert board != null;

        assert region != null;

        this.board = board;
        this.region = region;

    }
}
