package worldregion;

import common.Globals;
import common.Infection;
import entities.Bacteria;
import entities.Cure;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by espinha on 12/12/16.
 */
public class MutableCountry extends Country {

    private int welfareRating;          // from 1 to 10 ???
    private int medicalRating;          // from 1 to 10 ???
    private double infectedPopulation;  // percentage

    private final double initialPopulation;
    private ConcurrentHashMap<String, Integer> infections;
    private ConcurrentSkipListSet<Cure> cures;

    public MutableCountry(RegionSpecification regionSpecification, String name, CountryLocation location, String capital, String cca3, double population) {
        super(regionSpecification, name, location, capital, cca3, population);

        this.setWelfareRating(regionSpecification.getWelfareRating());
        this.setMedicalRating(regionSpecification.getMedicalRating());
        
        this.infectedPopulation = 0;
        initialPopulation = population;
    }

    public int getMedicalRating() {
        return medicalRating;
    }

    public void setMedicalRating(int medicalRating) {
        this.medicalRating = medicalRating;
    }

    public double getInfectedPopulation() {
        return infectedPopulation;
    }

    public boolean isInfected() {
        return infectedPopulation > 0;
    }

    public void infectPopulation(Infection infection) {

        double healthyPopulation = initialPopulation - infectedPopulation;

        int contagion = infection.getContagion();

        double factor = (contagion * 0.3) + (welfareRating * 0.3) + (medicalRating * 0.4);
        double impact = (healthyPopulation/1000) * (1 / factor);
        this.infectedPopulation += impact;
    }

    public int getWelfareRating() {
        return welfareRating;
    }

    public void setWelfareRating(int welfareRating) {
        this.welfareRating = welfareRating;
    }

    public void changeWelfareRating(int factor) {

    }

    public void updateInfectedPopulation(double facto) {

    }

    public ConcurrentHashMap<String, Integer> getCurrentInfections() {
        return infections;
    }

    public void infect(Bacteria bacteria, int nBacterias) {

        if(this.infections.get(bacteria.toString()) == null) {
            this.infections.put(bacteria.toString(), 1);

        } else {

            int bacterias = infections.get(bacteria.toString());

            infections.put(bacteria.toString(), bacterias + nBacterias);
        }
    }

    public void applyCure(Cure cure, Bacteria bacteria) {
        
    }

    public ConcurrentSkipListSet<Cure> getDevelopedCures() {
        return cures;
    }
}
