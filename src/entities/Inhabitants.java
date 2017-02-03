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
 * 1/26/17.
 *
 * Inhabitants that live in a certain EarthZone,
 */
public class Inhabitants  {


    private final int density;
    private List<Person> people;

    /**
     *
     * @param area
     */
    public Inhabitants(EarthZone area) {

        assert area != null;
        
        Location location = area.getLocation();
        this.density = location.getDensity();

        int max = (int) Math.pow(10,density+1);
        people = new ArrayList<>(max);

        for(int i = 0; i < max; i++) {

            int stamina = new Random().nextInt(100 - 78) + 78;
            people.add(new Person(stamina));
        }
    }

    /**
     *
     * @return density
     */
    public int density() {
        return density;
    }

    /**
     *
     * @return If there is any inhabitant infected.
     */
    public boolean infected() {

        for(Person person: people)
            if(person.isInfected())
                return true;

        return false;
    }

    /**
     *
     * @return List of current inhabitants.
     */
    public List<Person> people() {
        return people;
    }
}
