package region;

/**
 * Created by espinha on 11/21/16.
 */
public abstract class Region {
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public RegionSpec getRegionSpec() {
        return regionSpec;
    }

    public void setRegionSpec(RegionSpec regionSpec) {
        this.regionSpec = regionSpec;
    }

    public double getEpidemyRatio() {
        return epidemyRatio;
    }

    public void setEpidemyRatio(double epidemyRatio) {
        this.epidemyRatio = epidemyRatio;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    protected int population;
    protected double area;
    protected RegionSpec regionSpec;

    protected double epidemyRatio;
    protected State state;
    protected String name;

    Region(String name) {
        this.name = name;
    }

}
