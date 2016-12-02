package region;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by espinha on 11/21/16.
 */
public class Continent extends Region {

    private ArrayList<Country> countries;
    private int developmentDegree;

    public Continent(String name, String countriesFile) throws IOException {
        super(name);
        countries = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(countriesFile));

        String line;
        while((line = reader.readLine()) != null) {

            String[] words = line.split(";");
            String country = words[0];
            developmentDegree = Integer.parseInt(words[1]);

            countries.add(new Country(country));
        }
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    public int getDevelopmentDegree() {
        return developmentDegree;
    }

    public void setDevelopmentDegree(int developmentDegree) {
        this.developmentDegree = developmentDegree;
    }
}
