package common;

import worldregion.Continent;
import worldregion.MutableCountry;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rui on 1/24/17.
 */
public class Globals {

    public static int[] SYNTOM_CODES = initSyntoms();

    private static int[] initSyntoms() {
        int[] arr = new int[10];
        for(int i = 0; i < arr.length; i++)
            arr[i] = i+1;
        return arr;
    }

    public static MutableCountry randomCountry(Continent continent) {
        MutableCountry[] countries = continent.getMutableCountries();
        int totalCountries = countries.length;

        int countryIdx = ThreadLocalRandom.current().nextInt(totalCountries);

        return countries[countryIdx];
    }

    public static String[] randomBorders(MutableCountry mutableCountry) {

        List<String> borders = mutableCountry.getNeighbours();

        int nBorders = ThreadLocalRandom.current().nextInt(borders.size()) + 1;

        String[] randomBorders = new String[nBorders];
        for(int i = 0; i < randomBorders.length; i++) {
            randomBorders[i] = borders.get(ThreadLocalRandom.current().nextInt(borders.size()));
        }
        // return cca3 code
        return randomBorders;
    }

    public static void randomPause(int min, int max) {

        int interval = ThreadLocalRandom.current().nextInt(min, max);
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
