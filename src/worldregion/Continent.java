package worldregion;

import common.Globals;
import common.Infection;
import common.Report;
import entities.Cure;
import entities.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;

/**
 * Created by espinha on 11/21/16.
 */
public class Continent extends Region {

    private Map<String, MutableCountry> mutableCountries; // country code, country
    private Map<String, Country> countries;  // country, country's neighbour
    private int bacteriasDetected;

    public Continent(String countriesFile) throws IOException {

        assert countriesFile != null;
        assert name != null;

        countries = new HashMap<>();
        mutableCountries = new HashMap<>();

        bacteriasDetected = 0;

        Document document = null;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(countriesFile);
            Element root = document.getDocumentElement(); // countries

            this.name = root.getAttribute("worldregion");
            NodeList countriesList = root.getElementsByTagName("country");
            for(int i = 0; i < countriesList.getLength(); i++) {

                Element elem = (Element) countriesList.item(i);

                String subregion = elem.getAttribute("subregion");

                double temperature = Double.parseDouble(elem.getAttribute("temperature"));
                int welfareRating = Integer.parseInt(elem.getAttribute("welfare"));
                int medicalRating = Integer.parseInt(elem.getAttribute("medical"));

                String coordinates = elem.getAttribute("latlng");
                CountryLocation location = new CountryLocation(coordinates);

                String countryCode =  elem.getAttribute("cca3");
                RegionSpecification regionSpecification = new RegionSpecification(subregion, welfareRating, medicalRating, temperature);

                Country country = new Country(regionSpecification, elem.getAttribute("name"),
                                                    location, elem.getAttribute("capital"),
                                                    countryCode,
                                                    Double.parseDouble(elem.getAttribute("population")));

                String borders = elem.getAttribute("borders");
                List<String> bordersList = borders != null ? Arrays.asList(borders.split(";")) : null;

                country.setNeighbours(bordersList);

                countries.put(countryCode, country);
                mutableCountries.put(countryCode, country.getMutableCountry());

            }
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public MutableCountry[] getMutableCountries() {
        return (MutableCountry[]) mutableCountries.values().toArray();
    }

    @Override
    public synchronized double getPopulation() {

        double population = 0;
        for(String code: mutableCountries.keySet()) {
            population += mutableCountries.get(code).getPopulation();
        }

        return population;
    }

    public synchronized boolean spreadInfection(Infection infection, String code) {

        assert infection != null;
        assert code != null;
        assert mutableCountries.containsKey(code);

        MutableCountry country = mutableCountries.get(code);
        if(country.getPopulation() == 0) return false;

        country.infectPopulation(infection);

        notifyAll();

        // hardcoded for now
        return false;
    }

    public synchronized void spreadInfectionToBorders(Infection infection, String code) {

        assert infection != null;
        assert code != null;
        assert mutableCountries.containsKey(code);

        String[] borders = Globals.randomBorders(mutableCountries.get(code));

        for(String border: borders) {
            MutableCountry country = mutableCountries.get(border);
            country.infectPopulation(infection);

        }

    }

    public synchronized Report lookForNews(Reporter reporter) {

        while(bacteriasDetected == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // TODO: finish
        return new Report(null,null);
    }

    public synchronized void cureInfectedCountries(Cure cure) {

        assert cure != null;

        for(MutableCountry country: mutableCountries.values()) {
            if(country.isInfected()) {
                country.applyCure(cure);
            }
        }
    }
}
