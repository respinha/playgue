package region.worldregion;

import common.Infection;
import entities.Bacteria;
import entities.Person;
import entities.Population;
import entities.Vaccine;
import pt.ua.gboard.GBoard;
import region.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by espinha on 11/21/16.
 */
public class Region {

    private List<EarthZone> areas;
    private boolean noWorries;

    public Region(GBoard board, Location[] mapZones) {

        areas = new ArrayList<>();

        for(int i = 0; i < mapZones.length; i++) {

            EarthZone area = new EarthZone(board, mapZones[i]);
            areas.add(area);
        }

        noWorries = true;
    }

    public synchronized void spread(List<Bacteria> bacterias, int targetZone) {

        assert bacterias != null;

        EarthZone area = areas.get(targetZone);
        area.spread(bacterias);

        // population informing medical entity
        noWorries = false;
        notifyAll();
    }

    public synchronized void vaccinate(List<Vaccine> vaccines) {

    }

    public synchronized boolean dailyTasks(Population population) {

        while (noWorries) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < areas.size();i++) {

            population.setPopulation(areas.get(i).people(), i);
        }

        for(EarthZone area: areas) {
            for (Person p : area.people()) {

                if (p.isInfected())
                    return true;
            }
        }


        return false;
    }

}
