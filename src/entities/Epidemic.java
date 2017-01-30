package entities;

import common.Globals;
import common.Infection;
import pt.ua.gboard.GBoard;
import region.laboratory.BacteriaLaboratory;
import region.worldregion.EarthRegion;
import region.worldregion.EarthZone;
import region.worldregion.Location;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by espinha on 1/25/17.
 */
public class Epidemic extends BiologicalEntity implements Runnable {

    private final BacteriaLaboratory laboratory;
    private Vector<Bacteria> bacterias;
    private Set<Location> locations;

    public Epidemic(GBoard board, EarthRegion region, BacteriaLaboratory laboratory, Location location) {
        super(board, region);

        this.laboratory = laboratory;

        this.locations = new LinkedHashSet<>();
        locations.add(location);

        System.out.println("Started thread: " + Thread.currentThread().getId());
    }


    @Override
    public void run() {

        boolean running = true;


        while(running) {

            laboratory.develop(this);

            //int size = bacterias.size();

            region.spread(this, locations);

            running = bacterias.size() > 0;

            Globals.metronome().sync();
        }
    }

    public Vector<Bacteria> bacterias() {
        return bacterias;
    }

    public void setBacterias(Vector<Bacteria> bacterias) {

        assert bacterias != null;

        if(this.bacterias == null)
            this.bacterias = bacterias;
        else
            Collections.copy(bacterias, this.bacterias);
    }

    public void expand(List<Location> borders) {
        assert borders != null;

        locations.addAll(borders);
    }
}
