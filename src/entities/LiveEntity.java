package entities;

import worldregion.Continent;

/**
 * Created by rui on 1/24/17.
 */
public abstract class LiveEntity implements Runnable{

    protected final Continent continent;
    protected boolean alive;

    public LiveEntity(Continent continent) {
        this.continent = continent;
        alive = true;
    }
}
