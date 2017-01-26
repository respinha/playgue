package entities;

import region.worldregion.EarthZone;

/**
 * Created by rui on 1/24/17.
 */
public abstract class LiveEntity {

    protected final EarthZone area;
    protected final String name;
    protected boolean alive;

    public LiveEntity(String name, EarthZone area) {
        this.name = name;
        this.area = area;
        alive = true;
    }

    public String getName() {
        return name;
    }
}
