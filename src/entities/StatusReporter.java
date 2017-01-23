package entities;

import region.Continent;
import region.Country;
import region.MutableCountry;
import region.Region;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by espinha on 1/23/17.
 */
public class StatusReporter implements Runnable {

    private ConcurrentSkipListSet<MutableCountry> countries;
    public StatusReporter(Continent continent) {
        countries = continent.getMutableCountries();
    }

    @Override
    public void run() {
        
    }
}
