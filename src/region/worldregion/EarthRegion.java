package region.worldregion;

import entities.*;
import pt.ua.gboard.GBoard;

import java.util.*;

/**
 * Created by espinha on 11/21/16.
 */
public class EarthRegion extends WorldRegion{

    private List<EarthZone> areas;
    private Set<EarthZone> medicalKnownAreas;
    private boolean noWorries;
    private boolean civilizationAwake;

    public EarthRegion(GBoard board, List<Location> mapZones) {

        super(board);
        areas = new ArrayList<>();

        for(int i = 0; i < mapZones.size(); i++) {

            EarthZone area = new EarthZone(board, mapZones.get(i));
            areas.add(area);
        }

        medicalKnownAreas = new LinkedHashSet<>();

        noWorries = true;

        civilizationAwake = false;
    }

    public synchronized void spread(Epidemic epidemic, Set<Location> locations) {

        assert epidemic != null;
        assert locations != null;

        while (!civilizationAwake) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // DEBUG #########
        int sum = 0, totalSum = 0;
        for(EarthZone area: areas) {
            for (Person person : area.people()) {
                totalSum++;
                if (person.isInfected())
                    sum++;
            }
        }

        System.out.println("total infected: " + sum);
        System.out.println("total: " + totalSum);
        // ##############



        Vector<Bacteria> bacterias = epidemic.bacterias();
        //civilizationAwake = false;

        //Location location = locations.iterator().next();
        // Iterator<Location> it = locations.iterator();

        List<Location> list = new ArrayList<>();
        list.addAll(locations);

        for(EarthZone area: areas) {

            for(Location location: list){
                //Location location = locations.iterator().next();
                boolean found = area.getLocation().equals(location);
                if (found) {

                    //System.out.println(area.getLocation());
                    if (area.infected()) {
                        List<Location> borders = spreadBorders(area, bacterias);
                        epidemic.expand(borders);
                    }

                    area.spread(bacterias);
                }
            }
        }

        Iterator<Bacteria> it = bacterias.iterator();

        while(it.hasNext()) {

            Bacteria bacteria = it.next();
            bacteria.olden();
            if (bacteria.lifespan() == 0) {
                it.remove();
                System.out.println("death");
            }
        }

        noWorries = false;
        notify();
    }

    /**
     * Spreads epidemic to the borders of a zone (targetArea)
     * Everytime a border is found, it can be infected (random decision)
     * @param targetArea
     * @param bacterias
     * @return list of new zones infected
     */
    private List<Location> spreadBorders(EarthZone targetArea, Vector<Bacteria> bacterias) {

        List<Location> borders = new ArrayList<>();
        Location areaLocation = targetArea.getLocation();

        //System.out.println("target:" + areaLocation.x() + " " + areaLocation.y());

        // iterating through
        for(EarthZone area: areas) {

            // TODO: change from iterating all locations to mapping
            Location location = area.getLocation();
            if(location.isBorder(areaLocation)) {
                // random decision
                int rand = new Random().nextInt(2);
                if(rand > 0) {

                    // infecting the area
                    area.spread(bacterias);
                    borders.add(location);
                }
            }
        }

        return borders;
    }

    public synchronized boolean vaccinate(NursingTeam team) {


        boolean teamIsRequired = false;

        if(medicalKnownAreas.size() == 0) {

            List<EarthZone> infectedZones = new ArrayList<>();
            areas.forEach(
                    area -> {
                        if(area.infected())
                            infectedZones.add(area);
                    }
            );

            EarthZone target = infectedZones.get(new Random().nextInt(infectedZones.size()));
            medicalKnownAreas.add(target);
        }

        Map<String,Vaccine> vaccines = team.vaccines();

        Set<EarthZone> borders = new LinkedHashSet<>();

        for(EarthZone target: medicalKnownAreas) {

            if(target.infected()) {
                teamIsRequired = true;
                target.vaccinate(vaccines);
            }

            Location targetLocation = target.getLocation();

            for(EarthZone area: areas) {

                if(area.infected()) {

                    teamIsRequired = true;

                    Location location = area.getLocation();
                    if(location.isBorder(targetLocation)) {
                        int rand = new Random().nextInt(2);
                        if(rand > 0) {

                            borders.add(area);
                            area.vaccinate(vaccines);
                        }
                    }
                }

            }
        }

        medicalKnownAreas.addAll(borders);

        return teamIsRequired;
    }

    public synchronized void startDay(List<Inhabitants> inhabitants) {

        assert inhabitants != null;
        assert inhabitants.size() == areas.size();
        assert !civilizationAwake;

        for(int i = 0; i < inhabitants.size(); i++) {

            areas.get(i).setPeople(inhabitants.get(i));
        }

        civilizationAwake = true;
        notifyAll();

        System.out.println("started day");
    }

    public synchronized boolean dailyTasks(Civilization civilization) {

        System.out.println("cheguei");
        while (noWorries) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        boolean epidemic = false;
        for(int i = 0; i < areas.size();i++) {

            List<Person> people = areas.get(i).people();
            civilization.setPopulation(people, i);
            for(Person p: people) {
                if(p.isInfected())
                    epidemic = true;
            }
        }

        return epidemic;
    }

    public List<EarthZone> getAreas() {
        return areas;
    }

    public synchronized void setInhabitants(Civilization civilization) {
        assert civilization != null;

        for(int i = 0; i < civilization.people().size(); i++) {
            areas.get(i).setPeople(civilization.people().get(i));
        }
    }

    public synchronized void test() {
        System.out.println("I entered the test.");

        try {
            while (true)
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("I shouldn't be doing this");
    }
}
