package common;

import region.MedicalInformationCenter;
import region.laboratory.Laboratory;
import region.worldregion.Continent;

import java.awt.*;
import java.util.TimerTask;

/**
 * Created by rui on 1/25/17.
 */
public class WorldTimer extends TimerTask{

    private final Continent continent;
    /*private final MedicalInformationCenter center;
    private final Laboratory[] laboratories = new Laboratory[2];*/

    public WorldTimer(Continent continent, MedicalInformationCenter center, Laboratory[] laboratories) {

        this.continent =  continent;
        /*this.center = center;

        for(int i = 0; i < 2; i++)
            this.laboratories[i] = laboratories[i];*/
    }

    @Override
    public void run() {
        continent.newDay();
        /*center.newDay();
        for(int i = 0; i < 2; i++)
            laboratories[i].newDay();*/
    }
}
