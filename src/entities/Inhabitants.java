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

/**
 * Created by espinha on 1/26/17.
 */
public class Inhabitants extends LiveEntity {


    private final int density;
    private boolean epidemic;
    private EarthZone area;
    private List<Person> people;

    public Inhabitants(GBoard board, EarthZone area) {
        super(board);
        
        epidemic = false;
        
        Location location = area.getLocation();
        this.density = location.getDensity();

        int max = (int) Math.pow(10,density+1);
        people = new ArrayList<>(max);

        for(int i = 0; i < max; i++) {

            int stamina = new Random().nextInt(100) +1;
            people.add(new Person("Person " + i, area, stamina));
        }
    }

    public int density() {
        return density;
    }

    @Override
    public void run() {
        epidemic = area.dailyTasks(this);
    }

    public boolean infected() {

        return epidemic;
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
