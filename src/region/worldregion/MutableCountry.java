package region.worldregion;

import common.Infection;
import entities.Bacteria;
import entities.Cure;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by espinha on 12/12/16.
 */
public class MutableCountry extends Country {

    private int welfareRating;          // from 1 to 10 ???
    private int medicalRating;          // from 1 to 10 ???
    private int infectedPopulation;

    private final double initialPopulation;
    private Set<Bacteria> bacterias;
    private boolean infected;

    public MutableCountry(RegionSpecification regionSpecification, String name, CountryLocation location, String capital, String cca3, int population) {
        super(regionSpecification, name, location, capital, cca3, population);

        this.setWelfareRating(regionSpecification.getWelfareRating());
        this.setMedicalRating(regionSpecification.getMedicalRating());
        
        this.infectedPopulation = 0;
        initialPopulation = population;

        bacterias = new LinkedHashSet<>();

        infected = false;
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
        return infected;
    }

    public void infect(Bacteria bacteria) {

        assert bacteria != null;

        bacterias.add(bacteria);

        if(!isInfected())
            infected = true;
    }

    public void infectPopulation() {

        for(Bacteria bacteria: bacterias) {

            Infection infection = bacteria.getInfection();
            double healthyPopulation = initialPopulation - infectedPopulation;

            int contagion = infection.getContagion();

            double factor = (contagion * 0.3) + (welfareRating * 0.3) + (medicalRating * 0.4);

            double impact = 200 + ((healthyPopulation / 1000) * (1 / factor));

            if (infectedPopulation < initialPopulation)
                this.infectedPopulation += (int) impact;
        }

    }

    public int getWelfareRating() {
        return welfareRating;
    }

    public void setWelfareRating(int welfareRating) {
        this.welfareRating = welfareRating;
    }

    public void changeWelfareRating(int factor) {

    }

    public Set<Bacteria> getCurrentBacterias() {
        return bacterias;
    }


    public void applyCure(Cure cure) {

        int accuracy = cure.getAccuracy();

        double factor = (accuracy * 0.3) + (welfareRating * 0.3) + (medicalRating * 0.4);
        double impact = 200 + ((infectedPopulation/1000) * (1 / factor));

        if(infectedPopulation > 0)
            this.infectedPopulation -= impact;

    }
}
