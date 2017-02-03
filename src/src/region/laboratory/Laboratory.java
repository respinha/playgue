package region.laboratory;

import entities.LiveEntity;
import pt.ua.gboard.GBoard;


/**
 * Abstraction for any type of laboratory. Typically, it is expected to be a shared region.
 */

public abstract class Laboratory {

    protected final GBoard board;

    /*
    *
    * @param board
     */
    public Laboratory(GBoard board) {

        assert board != null;

        this.board = board;
    }

    /**
     *
     * @param
     */
    public abstract void develop(LiveEntity entity);
}
