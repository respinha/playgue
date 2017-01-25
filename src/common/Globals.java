package common;

import region.worldregion.Continent;
import region.worldregion.MutableCountry;

import java.util.List;
import java.util.Random;
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

        int countryIdx = new Random().nextInt(totalCountries);

        return countries[countryIdx];
    }

    public static void randomPause(int min, int max) {

        int interval = new Random().nextInt(max - min) + min;
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
