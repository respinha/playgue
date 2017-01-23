package region;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Created by espinha on 11/21/16.
 */
@Immutable
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

    private final CountryLocation location;
    private final String capital;
    private final double temperatureLevel;
    private final RegionSpecification regionSpecification;

    protected final String CCA3_CODE;

    public String getCODE() {
        return CCA3_CODE;
    }

    public MutableCountry getMutableCountry() {

        return new MutableCountry(regionSpecification, name, location, capital, CCA3_CODE, population);
    }

    public Country(RegionSpecification regionSpecification, String name, CountryLocation location, String capital, String cca3, double population) {

        this.name = name;
        this.temperatureLevel = regionSpecification.getTemperature();

        this.capital = capital;
        this.location = location;

        this.regionSpecification = regionSpecification;

        CCA3_CODE = cca3;

        this.population = population;
    }

    public CountryLocation getLocation() {
        return location;
    }

    public RegionSpecification getRegionSpecification() {
        return regionSpecification;
    }

    public String getCapital() {
        return capital;
    }

    public double getTemperatureLevel() {
        return temperatureLevel;
    }
}
