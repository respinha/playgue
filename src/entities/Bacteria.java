package entities;

import region.RegionSpec;

import java.io.Serializable;

/**
 * Created by espinha on 11/21/16.
 */
public abstract class Bacteria implements Runnable {

    protected final Serializable type;
    protected RegionSpec regionSpec;
    protected double mortalityRate;
    protected int resistanceDegree;

    protected BacteriaSpec bacteriaSpec;
    public RegionSpec getRegionSpec() {
        return regionSpec;
    }

    public void setRegionSpec(RegionSpec regionSpec) {
        this.regionSpec = regionSpec;
    }

    public double getMortalityRate() {
        return mortalityRate;
    }

    public void setMortalityRate(double mortalityRate) {
        this.mortalityRate = mortalityRate;
    }

    public int getResistanceDegree() {
        return resistanceDegree;
    }

    public void setResistanceDegree(int resistanceDegree) {
        this.resistanceDegree = resistanceDegree;
    }

    public Serializable getType() {

        return type;
    }

    public Bacteria(BacteriaType type, int regionSpec) {
        this.type = type;
        this.regionSpec = new RegionSpec(regionSpec);
    }
}
