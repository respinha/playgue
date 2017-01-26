package region.worldregion;

import common.Infection;
import common.Report;
import entities.Bacteria;
import entities.Person;
import entities.Population;
import entities.Vaccine;
import pt.ua.gboard.GBoard;
import region.Location;

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
    }

    public void spread(List<Bacteria> bacterias) {

        assert bacterias != null;

        for(Bacteria bacteria: bacterias) {

            Infection infection = bacteria.getInfection();
            Person person = people.get(new Random().nextInt(people.size()));
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
}
