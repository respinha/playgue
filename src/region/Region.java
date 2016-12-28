package region;

/**
 * Created by espinha on 11/21/16.
 */
public abstract class Region {

    protected double population;
    protected double area;
    protected RegionSpec regionSpec;

    public double getEpidemyPercentage() {
        return epidemyPercentage;
    }

    public void setEpidemyPercentage(double epidemyPercentage) {
        this.epidemyPercentage = epidemyPercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected double epidemyPercentage;
    protected State state;
    protected String name;

    public double getPopulation() {
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
