package region.worldregion;

import entities.Bacteria;
import pt.ua.concurrent.CObject;
import pt.ua.gboard.GBoard;

import java.util.List;

/**
 * Regions abstraction.
 * */
public abstract class WorldRegion extends CObject {

    protected GBoard board;

    /**
     * Constructor.
     * @param board
     */
    public WorldRegion(GBoard board) {
        this.board = board;
    }
}
