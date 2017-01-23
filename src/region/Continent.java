package region;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by espinha on 11/21/16.
 */
public class Continent extends Region {

    private ConcurrentSkipListSet<MutableCountry> mutableCountries;
    private Map<Country, List<String>> countries;  // country, country's neighbour

    private int developmentDegree;


    public Continent(String countriesFile) throws IOException {

        assert countriesFile != null;
        assert name != null;
        // todo: validate XML

        countries = new HashMap<>();
        mutableCountries = new ConcurrentSkipListSet<>();


        Document document = null;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(countriesFile);
            Element root = document.getDocumentElement(); // countries

            this.name = root.getAttribute("region");
            NodeList countriesList = root.getElementsByTagName("country");
            for(int i = 0; i < countriesList.getLength(); i++) {

                Element elem = (Element) countriesList.item(i);

                String subregion = elem.getAttribute("subregion");

                double temperature = Double.parseDouble(elem.getAttribute("temperature"));
                int welfareRating = Integer.parseInt(elem.getAttribute("welfare"));
                int medicalRating = Integer.parseInt(elem.getAttribute("medical"));

                String coordinates = elem.getAttribute("latlng");
                CountryLocation location = new CountryLocation(coordinates);

                RegionSpecification regionSpecification = new RegionSpecification(subregion, welfareRating, medicalRating, temperature);
                Country country = new Country(regionSpecification, elem.getAttribute("name"),
                                                    location, elem.getAttribute("capital"),
                                                    elem.getAttribute("cca3"),
                                                    Double.parseDouble(elem.getAttribute("population")));

                String borders = elem.getAttribute("borders");
                List<String> bordersList = borders != null ? Arrays.asList(borders.split(";")) : null;

                countries.put(country, bordersList);
                mutableCountries.add(country.getMutableCountry());

            }
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

/*
            NodeList regionsList = root.getElementsByTagName("Region");


            for(int i = 0; i < regionsList.getLength(); i++) {

                String regionName = root.getElementsByTagName("Name").item(0).getTextContent();

                int welfareRating = Integer.parseInt(getXMLStringValue(regionsList, i, "Welfare"));
                int medicalRating = Integer.parseInt(getXMLStringValue(regionsList, i, "Medical"));
                double temperature = Double.parseDouble(getXMLStringValue(regionsList, i, "Temperature"));

                RegionSpecification regionSpecification = new RegionSpecification(regionName, welfareRating, medicalRating, temperature);
                NodeList countriesList = root.getElementsByTagName("Country");

                for(int j = 0;  j < countriesList.getLength(); j++) {

                    String countryName  = getXMLStringValue(countriesList, j, "Name");
                    String capital = getXMLStringValue(countriesList, j, "Capital");

                    CountryLocation location = new CountryLocation(getXMLStringValue(countriesList, j, "Location"));

                    /*Country country = new Country(regionSpecification, countryName, location, capital);
                    setNeighbours(countriesList, i, country);

                    MutableCountry mutableCountry = new MutableCountry(regionSpecification, countryName, location, capital);

                    mutableCountries.add(mutableCountry);
                }*/

    }

    private void setNeighbours(NodeList countriesList, int i, Country country) {

        NodeList neighbors = ((Element) countriesList.item(i)).getElementsByTagName("Neighbours");

        List<String> neighboursList;

        if(countries.containsKey(country)) {
            neighboursList = countries.get(country);

        } else {
            neighboursList = new LinkedList<>();
        }


        for(int n = 0; n < neighbors.getLength(); n++) {

            neighboursList.add(neighbors.item(i).getTextContent());
        }

        countries.put(country, neighboursList);
    }

    private String getXMLStringValue(NodeList list, int i, String tag) {
        return ((Element) list.item(i)).getElementsByTagName(tag).item(0).getTextContent();
    }
    /*public ConcurrentSkipListSet<Country> getCountries() {
        return countries;
    }

    public void setCountries(ConcurrentSkipListSet<Country> countries) {
        this.countries = countries;
    }*/

    public int getDevelopmentDegree() {
        return developmentDegree;
    }

    public void setDevelopmentDegree(int developmentDegree) {
        this.developmentDegree = developmentDegree;
    }

    public ConcurrentSkipListSet<MutableCountry> getMutableCountries() {
        return mutableCountries;
    }
}
