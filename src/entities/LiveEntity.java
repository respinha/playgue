package entities;

import region.worldregion.Continent;

/**
 * Created by rui on 1/24/17.
 */
public abstract class LiveEntity implements Runnable{

    protected final Continent continent;
    protected final String name;
    protected boolean alive;

    public LiveEntity(String name, Continent continent) {
        this.name = name;
        this.continent = continent;
        alive = true;
    }

    public String getName() {
        return name;
    }
}
