package graphics;

import entities.Epidemic;
import pt.ua.concurrent.CThread;
import pt.ua.gboard.GBoard;
import pt.ua.gboard.GBoardInputHandler;
import pt.ua.gboard.Gelem;
import region.laboratory.BacteriaLaboratory;
import region.laboratory.MedicalLaboratory;
import region.worldregion.EarthRegion;
import region.worldregion.Location;

import java.awt.*;
import java.util.*;

/**
 * Handler for mouse presses in map.
 * Each mouse press creates a new Thread with an epidemic, which has a random infection.
 */
public class MapInputHandler extends GBoardInputHandler {

    private EarthRegion region;
    private BacteriaLaboratory bacteriaLaboratory;
    private java.util.List<CThread> threads;

    public MapInputHandler(EarthRegion region, BacteriaLaboratory bacteriaLaboratory) {
        //super(mousePressedMask | keyPressedMask);
        super(mousePressedMask);

        this.region = region;
        this.bacteriaLaboratory = bacteriaLaboratory;

        threads = new ArrayList<>();
    }

    @Override
    public void run(GBoard gBoard, int line, int column, int layer, int type, int code, Gelem gelem) {

        ValuedFilledGelem v = (ValuedFilledGelem) gelem;

        if(v.cellValue() > 0) {

            System.out.println("Cell picked: "  + v.cellValue());
            Location location = new Location(new Point(column, line), v.cellValue());

            if(threads.size() < 5) {
                CThread thread = new CThread(new Epidemic(gBoard, region, bacteriaLaboratory, location)); // TODO: pool of epidemies
                threads.add(thread);

                thread.start();
            }
        } else {
            System.out.println("Did you ever tried to walk on the sea? Didn't think so.");
        }
    }
}
