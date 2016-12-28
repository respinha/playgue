package region;

import java.util.LinkedList;

/**
 * Created by espinha on 11/21/16.
 */
public class Country extends Region {

    public static final String NORTH_EUROPE     = "NORTH EUROPE";
    public static final String WEST_EUROPE      = "WESTERN EUROPE";
    public static final String SOUTH_EUROPE     = "SOUTH EUROPE";
    public static final String EAST_EUROPE      = "EAST EUROPE";

    public static final String WEST_ASIA        = "WEST ASIA";
    public static final String SOUTH_ASIA       = "SOUTH ASIA";
    public static final String EAST_ASIA        = "EAST ASIA";
    public static final String NORTH_ASIA       = "NORTH ASIA";

    public static final String NORTH_AMERICA    = "NORTH AMERICA";
    public static final String SOUTH_AMERICA    = "SOUTH AMERICA";

    public static final String NORTH_AFRICA     = "NORTH AFRICA";
    public static final String SOUTH_AFRICA     = "SOUTH AFRICA";

    public static final String OCEANIA          = "OCEANIA";

    private CountryLocation location;
    private String capital;
    private int temperatureLevel;

    public Country(String name, String capital, CountryLocation location) {
        this.setName(name);

        this.setCapital(capital);
        this.setLocation(location);
    }



    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }


    public void setLocation(CountryLocation location) {
        this.location = location;
    }

    public int getTemperatureLevel() {
        return temperatureLevel;
    }

    public void setTemperatureLevel(int temperatureLevel) {
        this.temperatureLevel = temperatureLevel;
    }
}
