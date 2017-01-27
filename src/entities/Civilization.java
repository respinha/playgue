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

        this.center = center;

        civilization = new ArrayList<>();

        for(EarthZone area: region.getAreas()) {

            Inhabitants inhabitants = new Inhabitants(board, area);
            civilization.add(inhabitants);
        }

        region.setInhabitants(this);
    }

    @Override
    public void run() {

        boolean epidemic = false;
        boolean livingPeople = true;

        //System.out.println("before");
        region.startDay(people());
        //System.out.println("after");

        do {
            epidemic = region.dailyTasks(this);

            if(epidemic) {
                try {
                    center.inform(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            livingPeople = false;
            for(int i = 0; i < civilization.size(); i++) {

                Inhabitants inhabitants = civilization.get(i);
                List<Person> people = inhabitants.people();
                for(int j = 0; j < people.size(); j++) {
                    people.get(j).decreaseStamina();
                }

                if(!civilization.get(i).dead()) {
                    livingPeople = true;
                }
            }

            Globals.metronome().sync();

        } while (livingPeople && epidemic);

        /**
         * while(totalPopulation > 0) {
         *      totalPopulation = area.dailyTasks()
         *
         *      center.inform()
         *      Globals.tick()
         *}
         */
    }

    public List<Inhabitants> people() {
        return civilization;
    }

    public void clone(Civilization p) {
        center = p.informationCenter();
        Collections.copy(people(), p.people());

    }

    public MedicalInformationCenter informationCenter() {
        return center;
    }

    public void setPopulation(List<Person> people, int i) {

        Collections.copy(civilization.get(i).people(), people);
    }

    /*
    public void setPopulation(List<Person> inhabitants, int i) {
        Collections.copy(inhabitants, people);
    }

    public boolean hasInfectedPeople() {
        for(Person p: people())
            if(p.isInfected()) return true;

        return false;
    }*/
}
