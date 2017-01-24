package worldregion;

/**
 * Created by espinha on 11/21/16.
 */
public abstract class Region {

    protected double population;
    protected double area;
    protected RegionSpecification regionSpecification;

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

    public RegionSpecification getRegionSpecification() {
        return regionSpecification;
    }

    public void setRegionSpecification(RegionSpecification regionSpecification) {
        this.regionSpecification = regionSpecification;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
