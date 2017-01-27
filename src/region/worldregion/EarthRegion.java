package region.worldregion;

import entities.*;
import pt.ua.gboard.GBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by espinha on 11/21/16.
 */
public class EarthRegion extends WorldRegion{

    private List<EarthZone> areas;
    private boolean noWorries;
    private boolean civilizationAwake;

    public EarthRegion(GBoard board, List<Location> mapZones) {

        super(board);
        areas = new ArrayList<>();

        for(int i = 0; i < mapZones.size(); i++) {

            EarthZone area = new EarthZone(board, mapZones.get(i));
            areas.add(area);
        }

        noWorries = true;

        civilizationAwake = false;
    }

    public synchronized void spread(List<Bacteria> bacterias, Location location) {

        assert bacterias != null;
        assert location != null;

        while (!civilizationAwake) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //civilizationAwake = false;

        for (EarthZone area : areas) {
            if (area.getLocation().equals(location)) {
                System.out.println(bacterias);
                area.spread(bacterias);
            }
        }

        noWorries = false;
        notify();


    }

    public synchronized void vaccinate(List<Vaccine> vaccines) {

    }

    public synchronized void startDay(List<Inhabitants> inhabitants) {

        assert inhabitants != null;
        assert inhabitants.size() == areas.size();
        assert !civilizationAwake;

        for(int i = 0; i < inhabitants.size(); i++) {

            List<Person> people = inhabitants.get(i).people();
            areas.get(i).setPeople(people);
        }

        civilizationAwake = true;
        notifyAll();

        System.out.println("started day");
    }

    public synchronized boolean dailyTasks(Civilization civilization) {

        System.out.println("cheguei");
        while (noWorries) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Chegueeeeeeeeei");
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean epidemic = false;
        for(int i = 0; i < areas.size();i++) {

            List<Person> people = areas.get(i).people();
            civilization.setPopulation(people, i);
            for(Person p: people) {
                if(p.isInfected())
                    epidemic = true;
            }
        }

        return epidemic;
    }

    public List<EarthZone> getAreas() {
        return areas;
    }

    public synchronized void setInhabitants(Civilization civilization) {
        assert civilization != null;

        for(Inhabitants inhabitants: civilization.people())
            for(EarthZone area: areas)
                area.setPeople(inhabitants.people());
    }
}
