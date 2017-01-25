package common;

import entities.Bacteria;
import entities.Reporter;
import region.worldregion.MutableCountry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by espinha on 1/24/17.
 */
public class Report {

    private Reporter reporter;
    private Map<MutableCountry, Double> countriesByInfectedPopulation;
    private Set<Bacteria> bacterias;

    public Reporter getReporter() {
        return reporter;
    }

    public Report(Reporter reporter) {
        this.reporter = reporter;
        countriesByInfectedPopulation = new HashMap<>();
    }

    public void appendData(MutableCountry country, double percentage) {
        countriesByInfectedPopulation.putIfAbsent(country,percentage);
    }
    public Map<MutableCountry,Double> getData() {
        return countriesByInfectedPopulation;
    }

    public void setBacterias(Set<Bacteria> bacterias) {
        this.bacterias = bacterias;
    }

    public Set<Bacteria> bacterias() {
        return bacterias;
    }
}
