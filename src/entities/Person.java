package entities;

import common.Infection;
import region.worldregion.EarthZone;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by espinha on 1/25/17.
 */
public class Person  {

    private final EarthZone area;
    private final String name;
    private int stamina;
    private Infection infection;
    private Set<String> immunities;

    public Person(String name, EarthZone area, int stamina) {
        this.name = name;
        this.area = area;

        this.stamina = stamina;

        immunities = new LinkedHashSet<>();
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

        if(!immunities.contains(infection.syntom()))
            this.infection = infection;
    }

    public boolean isInfected() {

        return infection != null;
    }

    public Infection getInfection() {
        return infection;
    }

    public void vaccinatePerson(Map<String, Vaccine> vaccines) {

        Vaccine vaccine = vaccines.get(infection.syntom());

        if(vaccine != null) {
            // vaccine is completely effective
            immunities.add(infection.syntom());
            this.infection = null;

        } else {

            Vaccine[] vaccinesArr = (Vaccine[]) vaccines.values().toArray();
            vaccine = vaccinesArr[new Random().nextInt(vaccinesArr.length)];

            // TODO: implement
        }
    }

}
