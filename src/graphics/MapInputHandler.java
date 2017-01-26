package graphics;

import entities.Epidemy;
import pt.ua.gboard.GBoard;
import pt.ua.gboard.GBoardInputHandler;
import pt.ua.gboard.Gelem;
import region.laboratory.BacteriaLaboratory;
import region.laboratory.MedicalLaboratory;
import region.worldregion.EarthZone;

/**
 * Created by espinha on 1/25/17.
 */
public class MapInputHandler extends GBoardInputHandler {

    private EarthZone area;
    private MedicalLaboratory medicalLaboratory;
    private BacteriaLaboratory bacteriaLaboratory;

    public MapInputHandler(EarthZone area, BacteriaLaboratory bacteriaLaboratory, MedicalLaboratory medicalLaboratory) {
        super(mousePressedMask | keyPressedMask);

        this.area = area;
        this.bacteriaLaboratory = bacteriaLaboratory;
        this.medicalLaboratory = medicalLaboratory;
    }

    @Override
    public void run(GBoard gBoard, int i, int i1, int i2, int i3, int i4, Gelem gelem) {
        new Thread(new Epidemy("Epidemy", area, bacteriaLaboratory)); // TODO: pool of epidemies
    }
}
