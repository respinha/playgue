package entities;

import common.Globals;
import pt.ua.gboard.GBoard;
import region.MedicalInformationCenter;
import region.worldregion.EarthRegion;
import region.worldregion.EarthZone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by espinha on 1/25/17.
 */
public class Civilization extends Population {

    protected List<Inhabitants> civilization;
    protected MedicalInformationCenter center;

    public Civilization(GBoard board, EarthRegion region, MedicalInformationCenter center) {
        super(board, region, center);

        assert center != null;

        this.center = center;

        civilization = new ArrayList<>();

        for(EarthZone area: region.getAreas()) {

            Inhabitants inhabitants = new Inhabitants(area);
            civilization.add(inhabitants);
        }

        region.setInhabitants(this);


        assert civilization.size() > 0;
    }

    @Override
    public void run() {

        boolean epidemic;
        boolean livingPeople;

        region.startDay(people());

        do {
            epidemic = region.dailyTasks(this);

            if(epidemic) {

                System.out.println("WATCH OUT");
                try {
                    center.inform(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //livingPeople = false;

            System.out.println("Civilization: ending lifecycle");
            Globals.metronome().sync();

            if(!epidemic) {
                System.out.println("Epidemic OOOOVER");
                epidemic = true;
            }
        } while (epidemic);
    }

    public List<Inhabitants> people() {
        return civilization;
    }

    public MedicalInformationCenter informationCenter() {
        return center;
    }

    public void setPopulation(List<Person> people, int i) {

        //Collections.copy(people, civilization.get(i).people());

        assert i >= 0 && people != null;

        List<Person> list = civilization.get(i).people();
        list.clear();

        list.addAll(people);


    }

}
