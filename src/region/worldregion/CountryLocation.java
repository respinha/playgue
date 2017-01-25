package region.worldregion;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Created by espinha on 12/5/16.
 */
@Immutable
public class CountryLocation {

    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public CountryLocation(String location) {

        String[] data = location.split(",");

        latitude = Double.parseDouble(data[0]);
        longitude = Double.parseDouble(data[1]);
    }
}
