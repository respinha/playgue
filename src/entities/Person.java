package entities;

import common.Infection;
import region.worldregion.EarthZone;

/**
 * Created by espinha on 1/25/17.
 */
public class Person  {

    private final EarthZone area;
    private final String name;
    private int stamina;
    private Infection infection;

    public Person(String name, EarthZone area, int stamina) {
        this.name = name;
        this.area = area;

        this.stamina = stamina;
    }

    public int getStamina() {
        return stamina;
    }

    public void decreaseStamina() {
        if(infection != null)
            this.stamina -= infection.getSeverity();
    }

    public void increaseStamina(int factor) {
        this.stamina += factor;
    }
    
    public void infect(Infection infection) {
        this.infection = infection;
    }

    public boolean isInfected() {

        return infection != null;
    }

    public Infection getInfection() {
        return infection;
    }
}
