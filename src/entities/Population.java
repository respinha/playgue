package entities;

import common.Globals;
import region.MedicalInformationCenter;
import region.worldregion.EarthZone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by espinha on 1/25/17.
 */
public class Population extends LiveEntity implements Runnable {

    protected List<Person> people;
    private MedicalInformationCenter center;
    private List<Person> population;

    public Population(String name, EarthZone area, MedicalInformationCenter center, int density) {
        super(name, area);

        this.center = center;

        int max = (int) Math.pow(10,density);
        people = new ArrayList<>(max);

        for(int i = 0; i < max; i++) {

            int resistance = new Random().nextInt(100) +1;
            people.add(new Person("Person " + i, area, resistance));
        }
    }

    @Override
    public void run() {

        boolean epidemy = false;
        do {
            epidemy = area.dailyTasks(this);

            if(epidemy) center.inform(this);


            for(int i = 0; i < people().size(); i++)
                people().get(i).decreaseStamina();

            Globals.metronome().sync();

        } while (people.size() > 0 && epidemy);

        /**
         * while(totalPopulation > 0) {
         *      totalPopulation = area.dailyTasks()
         *
         *      center.inform()
         *      Globals.tick()
         *}
         */
    }

    public List<Person> people() {
        return people;
    }

    public void clone(Population p) {
        center = p.informationCenter();
        Collections.copy(people(), p.people());

    }

    public MedicalInformationCenter informationCenter() {
        return center;
    }

    public void setPopulation(List<Person> population, int i) {
        Collections.copy(population, people);
    }

    public boolean hasInfectedPeople() {
        for(Person p: people())
            if(p.isInfected()) return true;

        return false;
    }
}
