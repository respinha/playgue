package graphics;

import entities.Epidemic;
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
 * Created by espinha on 1/25/17.
 */
public class MapInputHandler extends GBoardInputHandler {

    private EarthRegion region;
    private MedicalLaboratory medicalLaboratory;
    private BacteriaLaboratory bacteriaLaboratory;
    private java.util.List<Thread> threads;
    public MapInputHandler(EarthRegion region, BacteriaLaboratory bacteriaLaboratory, MedicalLaboratory medicalLaboratory) {
        //super(mousePressedMask | keyPressedMask);
        super(mousePressedMask);

        this.region = region;
        this.bacteriaLaboratory = bacteriaLaboratory;
        this.medicalLaboratory = medicalLaboratory;

        threads = new ArrayList<>();
    }

    @Override
    public void run(GBoard gBoard, int line, int column, int layer, int type, int code, Gelem gelem) {
        System.out.println("TestInputHandler: line "+line+", column="+column+", layer="+layer+", type="+type+", code="+code+", gelem="+gelem);

        System.out.println(column + " " + line);
        ValuedFilledGelem v = (ValuedFilledGelem) gelem;


        if(v.cellValue() > 0) {

            System.out.println("Cell picked: "  + v.cellValue());
            Location location = new Location(new Point(column, line), v.cellValue());

            Thread thread = new Thread(new Epidemic(gBoard, region, bacteriaLaboratory, location)); // TODO: pool of epidemies
            threads.add(thread);
            thread.start();
        } else {
            System.out.println("Did you ever tried to walk on the sea? Didn't think so.");
        }
    }
}
