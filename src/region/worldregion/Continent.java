package region.worldregion;

import common.Globals;
import common.Infection;
import common.Report;
import entities.Bacteria;
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
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by espinha on 11/21/16.
 */
public class Continent extends Region {

    private Map<String, MutableCountry> mutableCountries; // country code, country
    private Map<String, Country> countries;  // country, country's neighbour

    private boolean newBacteria;
    private int time;

    public Continent(String countriesFile) throws IOException {

        assert countriesFile != null;
        assert name != null;

        countries = new HashMap<>();
        mutableCountries = new HashMap<>();

        newBacteria = false;

        Document document = null;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(countriesFile);
            Element root = document.getDocumentElement(); // countries

            this.name = root.getAttribute("region/worldregion");
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
                                                    Integer.parseInt(elem.getAttribute("population")));

                String borders = elem.getAttribute("borders");

                List<String> bordersList = null;
                if(borders != null && !borders.trim().isEmpty()) {
                    bordersList = Arrays.asList(borders.split(","));

                    //if(bordersList.get(0).trim().isEmpty())  System.out.println("B: " + country.getName());
                    country.setNeighbours(bordersList);
                }

                countries.put(countryCode, country);
                mutableCountries.put(countryCode, country.getMutableCountry());

            }
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        time = 0;
    }

    public MutableCountry[] getMutableCountries() {

        MutableCountry[] countriesArr = new MutableCountry[mutableCountries.size()];

        return mutableCountries.values().toArray(countriesArr);
    }

    /*@Override
    public synchronized int getPopulation() {

        int population = 0;
        for (String code : mutableCountries.keySet()) {
            population += mutableCountries.get(code).getPopulation();
        }

        return population;
    }*/

    public synchronized boolean spreadInfection(Bacteria bacteria, String code) {

        assert bacteria != null;
        assert code != null;
        assert mutableCountries.containsKey(code);

        while (time % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        MutableCountry country = mutableCountries.get(code);
        if(country.getPopulation() == 0) return true;

        country.infect(bacteria);

        if(new Random(time).nextInt(2) == 0) {
            newBacteria = true;
            notify();
        }

        // hardcoded for now
        return true;
    }

    public synchronized void spreadInfectionToBorders(Bacteria bacteria, String code) {

        assert bacteria != null;
        assert code != null;

        assert mutableCountries.containsKey(code);

        while (time % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String[] borders = randomBorders(mutableCountries.get(code));

        if(borders != null){
            for (String border : borders) {
                System.out.println("Code: " + code + "; Infect border: " + border + "; TID: " + Thread.currentThread().getId());
                MutableCountry country = mutableCountries.get(border);
                country.infect(bacteria);
            }
        }
    }

    private String[] randomBorders(MutableCountry mutableCountry) {

        assert mutableCountry != null;

        List<String> borders = mutableCountry.getNeighbours();

        if(borders == null) return null;

        int nBorders = new Random(time).nextInt(borders.size()) + 1;

        String[] randomBorders = new String[nBorders];
        for(int i = 0; i < randomBorders.length; i++) {
            while (mutableCountries.get(randomBorders[i]) == null)
                randomBorders[i] = borders.get(new Random(time).nextInt(borders.size()));
        }
        // return cca3 code
        return randomBorders;
    }


    public synchronized Report lookForNews(Reporter reporter) {

        assert reporter != null;

        /*while (time % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        while(!newBacteria || time % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        newBacteria = false;

        Report report = new Report(reporter);
        for(MutableCountry country: mutableCountries.values()) {

            if(country.isInfected()) {
                double percentage = (country.getInfectedPopulation() * 100) / country.getPopulation();
                report.appendData(country, percentage);

                Set<Bacteria> bacterias = country.getCurrentBacterias();

                report.setBacterias(bacterias);
            }
        }

        return report;
    }

    public synchronized void cureInfectedCountries(Cure cure) {

        assert cure != null;

        while (time % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(MutableCountry country: mutableCountries.values()) {
            if(country.isInfected()) {
                country.applyCure(cure);
            }
        }
    }

    public synchronized void newDay() {
        time++;

        for(MutableCountry country: mutableCountries.values()) {
            if(country.isInfected())
                country.infectPopulation();
        }

        if(time % 2 == 0)
            notifyAll();
    }
}
