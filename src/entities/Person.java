package entities;

import common.Globals;
import common.Infection;
import region.worldregion.EarthZone;

import java.util.*;

/**
 * Created by espinha on 1/25/17.
 */
public class Person  {

    private final EarthZone area;
    private final String name;
    private double stamina;
    private Infection infection;
    private Set<String> immunities;
    private List<Double> staminaRecord;

    public Person(String name, EarthZone area, double stamina) {
        this.name = name;
        this.area = area;

        assert stamina >= 78;

        this.stamina = stamina;
        immunities = new LinkedHashSet<>();

        if(new Random().nextInt(2) > 0) {

            immunities.add(Globals.randomSymptom());
        }

        staminaRecord = new ArrayList<>();
    }

    public double getStamina() {


        assert stamina >= 0;

        return stamina;

    }

    public void decreaseStamina() {

        if(infection != null && stamina > 0) {
            staminaRecord.add(stamina);
            this.stamina -= infection.getSeverity();
        }

        if(stamina < 0)
            stamina = 0;
    }
    
    public void infect(Infection infection) {

        assert infection != null;

        if(!immunities.contains(infection.symptom())) {
            this.infection = infection;
            //System.out.println(infection.getSeverity());
        }

    }

    public boolean isInfected() {

        return infection != null;
    }

    public Infection getInfection() {
        return infection;
    }

    public void vaccinatePerson(Map<String, Vaccine> vaccines) {

        assert vaccines != null;

        if(infection != null) {

            if(vaccines.get(infection.symptom()) != null)
                infection = null;

            for(String s: vaccines.keySet())
                immunities.add(s);
        }

        if(infection != null) {
            Vaccine[] vaccinesArr = (Vaccine[]) vaccines.values().toArray();
            Vaccine vaccine = vaccinesArr[new Random().nextInt(vaccinesArr.length)];

            infection.decreaseSeverity(vaccine.getVersatility());
        }
    }

    public boolean dead() {
        assert stamina >= 0;

        return stamina == 0;
    }

}
