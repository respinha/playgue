package region.worldregion;

import common.Globals;
import common.Infection;
import entities.Bacteria;
import entities.Person;
import entities.Inhabitants;
import entities.Vaccine;
import graphics.ValuedFilledGelem;
import pt.ua.gboard.GBoard;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Area that contains a set of inhabitants.
 * It is binded to a certain location.
 */
public class EarthZone extends Zone {

    private List<Person> people;
    private boolean infected;
    private int initialPopulation;

    public EarthZone(GBoard board, Location location) {
        super(board, location);

        infected = false;

    }

    public void spread(Vector<Bacteria> bacterias) {

        assert bacterias != null;

        if(people.size() > 0) { // if population still exists
            for (Bacteria bacteria : bacterias) {

                Infection infection = bacteria.getInfection();

                int rand = new Random().nextInt(people.size());

                Person person = people.get(rand);

                // only update infection if person has a less severe infection
                if (!person.isInfected() || person.getInfection().getSeverity() < infection.getSeverity())
                    person.infect(infection);
            }

            double percentage = 0;
            for(Person person: people) {
                if (person.isInfected()) {
                    percentage++;
                    infected = true;
                }
            }

            percentage = (percentage * 100) / people().size();

            if(infected) {
                int line = (int) getLocation().y();
                int col = (int) getLocation().x();

                board.erase(line, col);

                int gradient = (int) (255 - percentage);

                try {

                    Color tmp = new Color(gradient,0,0);
                    ValuedFilledGelem gelem = new ValuedFilledGelem(tmp, 100, getLocation().getDensity());
                    board.draw(gelem, line, col, 0);

                    ValuedFilledGelem elem = (ValuedFilledGelem) board.topGelem((int) location.y(), (int) location.x());
                    elem.mark();
                } catch (IllegalArgumentException e) {

                    System.exit(1);
                }

            }
        }
    }

    public void decreaseSickPeopleStamina() {

        Iterator<Person> iterator = people.iterator();
        while(iterator.hasNext()) {

            //Inhabitants inhabitants = civilization.get(i);
            //List<Person> people = inhabitants.people();
            Person person = iterator.next();
            person.decreaseStamina();

            if(person.dead()) {
                iterator.remove();
            }
        }
    }

    public boolean infected() {

        return infected && people.size() > 0;
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

        boolean updateInfected = false;
        for(Person person: people())
            if(person.isInfected())
                updateInfected = true;

        boolean prevInfected = infected;
        infected = updateInfected;

        if(!infected && prevInfected) {
            int x = (int) this.getLocation().x();

            int y = (int) this.getLocation().y();

            board.erase(y, x);

            Color color = Globals.chooseColor(this.getLocation().getDensity());

            ValuedFilledGelem gelem = new ValuedFilledGelem(color, 100, this.getLocation().getDensity());
            board.draw(gelem, y, x, 0);
        }
    }

    public List<Person> people() {
        return people;
    }

    public Location getLocation() {
        return location;
    }

    public void setPeople(Inhabitants inhabitants) {

        assert inhabitants != null;
        assert inhabitants.people() != null && inhabitants.people().size() > 0;

        people = new ArrayList<>(inhabitants.people().size());

        people.addAll(inhabitants.people());
        initialPopulation = people.size();
    }

    /**
     * Override of method equals(). EarthZones are equal if they share the same location in the board.
     * @param
     * @return
     */
    @Override
    public boolean equals(Object o) {

        assert o != null;

        EarthZone area = (EarthZone) o;

        return this.getLocation().equals(area.getLocation());
    }

    /**
     *
     * @return The area's initial population.
     */
    public int initialPopulation() {
        return initialPopulation;
    }
}