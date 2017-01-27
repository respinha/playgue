package region.worldregion;

import entities.Bacteria;
import pt.ua.gboard.GBoard;

import java.util.List;

/**
 * Created by espinha on 1/26/17.
 */
public abstract class WorldRegion {

    protected GBoard board;
    public WorldRegion(GBoard board) {
        this.board = board;
    }
}
