package region.worldregion;

import common.Infection;
import entities.Bacteria;
import entities.Person;
import entities.Inhabitants;
import entities.Vaccine;
import pt.ua.gboard.GBoard;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by espinha on 1/25/17.
 */
public class EarthZone extends Zone {

    private List<Person> people;
    private boolean infected;
    private int density;

    public EarthZone(GBoard board, Location location) {
        super(board, location);

        //System.out.println(location.getPoint());
        infected = false;
    }

    public void spread(Vector<Bacteria> bacterias) {

        assert bacterias != null;

        for(Bacteria bacteria: bacterias) {

            Infection infection = bacteria.getInfection();

            int rand = new Random().nextInt(people.size());

            Person person = people.get(rand);

            // only update infection if person has a less severe infection
            if(!person.isInfected() || person.getInfection().getSeverity() < infection.getSeverity())
                person.infect(infection);

            // simulating contagion from an infected person to another person
            /*int max = (int) Math.pow(2, density);
            for(int i = 0; i < max; i++) {

                rand = new Random().nextInt(people.size());
                if(!people.get(rand).isInfected())
                    people.get(rand).infect(infection);
            }*/
        }

        infected = true;
    }

    public boolean infected() {
        return infected;
    }
    public void vaccinate(Map<String, Vaccine> vaccines) {

        assert vaccines != null;

        people().forEach(
                person -> {
                    if(person.isInfected()) {
                        person.vaccinatePerson(vaccines);
                    }
                }
        );
    }

    public List<Person> people() {
        return people;
    }

    public Location getLocation() {
        return location;
    }

    public void setPeople(Inhabitants inhabitants) {

        assert this.people == null;
        assert inhabitants != null;
        assert inhabitants.people() != null && inhabitants.people().size() > 0;

        people = new ArrayList<>(inhabitants.people().size());
        this.density = inhabitants.density();

        people.addAll(inhabitants.people());
    }

    @Override
    public boolean equals(Object o) {

        assert (EarthZone) o != null;

        EarthZone area = (EarthZone) o;

        return this.getLocation().equals(area.getLocation());
    }
}