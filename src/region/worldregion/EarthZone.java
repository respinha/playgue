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

    public EarthZone(GBoard board, Location location) {
        super(board, location);

        System.out.println(location.getPoint());
    }

    public void spread(List<Bacteria> bacterias) {

        assert bacterias != null;

        for(Bacteria bacteria: bacterias) {

            Infection infection = bacteria.getInfection();

            int rand = new Random().nextInt(people.size());

            Person person = people.get(rand);
            person.infect(infection);

            // TODO: add contagion
        }
    }

    public void vaccinate(List<Vaccine> vaccines) {

        assert vaccines != null;

        //for()
    }

    public List<Person> people() {
        return people;
    }

    public Location getLocation() {
        return location;
    }

    public boolean dailyTasks(Inhabitants inhabitants) {
        return false;
    }


    public void setPeople(List<Person> people) {

        assert this.people == null;
        assert people != null;

        this.people = people;
    }
}
