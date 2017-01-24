package common;

import entities.Reporter;
import pt.ua.gboard.GBoard;
import worldregion.Region;

/**
 * Created by espinha on 11/21/16.
 */

// Shared
public class MedicalInformationCenter {

    // organization

    private static GBoard board;
    public MedicalInformationCenter(GBoard board) {
        this.board = board;
    }

    public synchronized void update(Reporter reporter, Report report) {

    }
}
