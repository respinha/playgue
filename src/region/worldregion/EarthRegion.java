package region.worldregion;

import common.Globals;
import common.Infection;
import entities.*;
import graphics.ValuedFilledGelem;
import pt.ua.gboard.GBoard;
import pt.ua.gboard.Gelem;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Shared region.
 * It is composed by a set of EarthZones and accessed by a Civilization
 */
public class EarthRegion extends WorldRegion{

    private List<EarthZone> areas;
    private Set<EarthZone> medicalKnownAreas;
    private boolean noWorries;
    private boolean civilizationAwake;

    /**
     *
     * @param board
     * @param mapZones
     */
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

    /**
     * Method invoked by an Epidemic to infect people in a certain location.
     * @param epidemic
     * @param locations
     */
    public synchronized void spread(Epidemic epidemic, Set<Location> locations) {

        assert epidemic != null;
        assert locations != null;

        while (!civilizationAwake) {
            /*try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            await();
        }

        // DEBUG #########
        int sum = 0, totalSum = 0, death = 0;

        Iterator<EarthZone> areaIterator = areas.iterator();

        while(areaIterator.hasNext()) {

            EarthZone area = areaIterator.next();
            if(area.people().size() == 0) {

                int line = (int) area.getLocation().x();
                int col = (int) area.getLocation().y();

                /*board.erase(line,col);
                areaIterator.remove();*/
            }
            totalSum += area.people().size();
            for (Person person : area.people()) {


                if (person.isInfected())
                    sum++;
            }
        }

        System.out.println("total infected: " + sum);
        System.out.println("total: " + totalSum);

        Vector<Bacteria> bacterias = epidemic.bacterias();

        List<Location> list = new ArrayList<>();
        list.addAll(locations);

        for(EarthZone area: areas) {
            for(Location location: list){

                boolean found = area.getLocation().equals(location);
                if (found) {

                    if (area.infected() && area.people().size() > (area.initialPopulation() / 3)) {
                        List<Location> borders = spreadBorders(area, bacterias);
                        epidemic.expand(borders);
                    }

                    area.spread(bacterias);
                    if(area.infected()) {

                        bacterias.addAll(multiply(epidemic));
                    }
                }
            }
        }

        Iterator<Bacteria> bacIterator = bacterias.iterator();

        while(bacIterator.hasNext()) {

            Bacteria bacteria = bacIterator.next();
            bacteria.olden();

            if (bacteria.lifespan() == 0) {

                bacIterator.remove();
            }
        }

        for(EarthZone area: areas) {

            area.decreaseSickPeopleStamina();

            Location location = area.getLocation();

            ValuedFilledGelem elem = (ValuedFilledGelem) board.topGelem((int) location.y(), (int) location.x());
            if(!area.infected() && elem != null) {

                Color color = null;

                if(area.people().size() == 0) {
                    board.erase(elem, (int) location.y(), (int) location.x());
                    color = Color.GRAY;

                    ValuedFilledGelem newGelem = new ValuedFilledGelem(color, 100, elem.cellValue());

                    board.draw(newGelem, (int) location.y(), (int) location.x(), 0);
                }
                else {
                    if (area.people().size() > 0 && elem.state() == 1) {
                        board.erase(elem, (int) location.y(), (int) location.x());
                        color = Globals.chooseColor(elem.cellValue());

                        ValuedFilledGelem newGelem = new ValuedFilledGelem(color, 100, elem.cellValue());

                        board.draw(newGelem, (int) location.y(), (int) location.x(), 0);
                    }
                }


            }

        }

        noWorries = false;
        //notify();
        signal();
    }

    /**
     * Method invoked when bacterias successfully spread to other areas.
     * @param epidemic
     * @return new bacterias.
     */
    private Vector<Bacteria> multiply(Epidemic epidemic) {

        assert epidemic != null;
        assert epidemic.bacterias().size() > 0;

        Vector<Bacteria> bacterias = new Vector<>();

        int bound = 10;
        int max = new Random().nextInt(bound - bound/2) + bound/2;

        Infection infection = epidemic.bacterias().get(0).getInfection();
        for(int i = 0; i < max; i++) {

            Bacteria b = new Bacteria(infection);
            //b.setInfection(infection);
            bacterias.add(b);
        }

        return bacterias;
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

    /**
     * Method invoked by NursingTeam after it acquires a set of vaccines.
     * @param team
     * @return
     */
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

    /**
     * Method invoked only once, in the first iteration
     * of a Civiliztion's lifecycle.
     * @param inhabitants
     */
    public synchronized void startDay(List<Inhabitants> inhabitants) {

        assert inhabitants != null;
        assert inhabitants.size() == areas.size();
        assert !civilizationAwake;

        for(int i = 0; i < inhabitants.size(); i++) {

            areas.get(i).setPeople(inhabitants.get(i));
        }

        civilizationAwake = true;
        broadcast();

    }

    /**
     * Method invoked by Civilization when it starts an iteration of its lifecycle.
     * Entity awaits until more epidemics are detected.
     * @param civilization
     * @return
     */
    public synchronized boolean dailyTasks(Civilization civilization) {

        while (noWorries) {
            await();
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

    /**
     *
     * * @return Region's areas.
     */
    public List<EarthZone> getAreas() {
        return areas;
    }

    /**
     *
     * Method invoked by the Civilization of this region when it is instantiated.
     * @param civilization
     */
    public synchronized void setInhabitants(Civilization civilization) {
        assert civilization != null;

        for(int i = 0; i < civilization.people().size(); i++) {
            areas.get(i).setPeople(civilization.people().get(i));
        }

        assert civilization.people().size() > 0;
    }
}
