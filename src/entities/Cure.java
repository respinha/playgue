package entities;

import region.RegionSpec;

/**
 * Created by espinha on 11/21/16.
 */
public abstract class Cure implements Runnable {

    protected BacteriaSpec bacteriaSpec;
    protected RegionSpec regionSpec;
    protected int resistance;

    public Cure(BacteriaSpec bacteriaSpec, RegionSpec regionSpec, int resistance) {
        this.bacteriaSpec = bacteriaSpec;
        this.regionSpec = regionSpec;
        this.resistance = resistance;
    }
}
