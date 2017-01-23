package entities;

//import region.RegionSpecification;

import common.Specification;
import region.Region;
import region.RegionSpecification;

/**
 * Created by espinha on 11/21/16.
 */
public class Bacteria implements Runnable {

    //protected RegionSpecification regionSpecification;
    protected double mortalityRate;
    protected int resistanceDegree;

    protected Specification bacteriaSpec;

    public Bacteria(Region region, Specification spec) {
        this.bacteriaSpec = spec;

    }

    public double getMortalityRate() {
        return mortalityRate;
    }

    public void updateMortalityRate(double factor) {
        mortalityRate += factor;
    }

    public int getResistanceDegree() {
        return resistanceDegree;
    }

    public void updateResistanceDegree(int factor) {
        this.resistanceDegree += factor;
    }


    @Override
    public void run() {

    }
}
