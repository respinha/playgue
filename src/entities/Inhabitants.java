package entities;

import pt.ua.gboard.GBoard;
import region.MedicalInformationCenter;
import region.worldregion.EarthRegion;
import region.worldregion.EarthZone;
import region.worldregion.Location;
import region.worldregion.WorldRegion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by espinha on 1/26/17.
 */
public class Inhabitants  {


    private final int density;
    private boolean epidemic;
    private List<Person> people;

    public Inhabitants(EarthZone area) {

        epidemic = false;
        
        Location location = area.getLocation();
        this.density = location.getDensity();

        int max = (int) Math.pow(10,density+1);
        people = new ArrayList<>(max);

        for(int i = 0; i < max; i++) {

            int stamina = new Random().nextInt(100 - 78) + 78;
            people.add(new Person("Person " + i, area, stamina));
        }
    }

    public int density() {
        return density;
    }

    public boolean infected() {

        for(Person person: people)
            if(person.isInfected())
                return true;

        return false;
    }

    public List<Person> people() {
        return people;
    }

    public boolean dead() {
        for(int i = 0; i < people.size(); i++) {
            if(people.get(i).getStamina() > 0)
                return false;
        }

        return true;
    }
}
