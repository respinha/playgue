package region;

import entities.Bacteria;
import entities.Cure;
import sun.awt.image.ImageWatched;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by espinha on 12/12/16.
 */
class MutableCountry extends Country {

    private int welfareRating;          // from 1 to 10 ???
    private int medicalRating;          // from 1 to 10 ???
    private double infectedPopulation;  // percentage
    private double cureAccuracy;        // percentage

    private HashMap<String, Integer> infections;
    private LinkedList<Cure> cures;

    public MutableCountry(String name, String capital, CountryLocation location, int welfareRating, int medicalRating) {
        super(name, capital, location);
        this.setWelfareRating(welfareRating);
        this.setMedicalRating(medicalRating);

        this.cureAccuracy = 0;
        this.infectedPopulation = 0;
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

    public void infectPopulation(double increase) {
        this.infectedPopulation += increase;
    }

    public double getCureAccuracy() {
        return cureAccuracy;
    }

    public void developCure(double accuracy) {
        this.cureAccuracy += accuracy;
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

    public HashMap<String, Integer> getCurrentInfections() {
        return infections;
    }

    public void infect(Bacteria bacteria, int nBacterias) {

        String type = bacteria.getType().toString();
        if(this.infections.get(type) == null) {
            this.infections.put(bacteria.getType().toString(), 1);

        } else {

            int bacterias = infections.get(type);

            infections.put(type, bacterias + nBacterias);
        }

    }

    public void cure(Cure cure, Bacteria bacteria) {

    }

    public LinkedList<Cure> getCurrentCures() {
        return cures;
    }
}
