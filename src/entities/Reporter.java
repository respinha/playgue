package entities;

import worldregion.Continent;
import worldregion.MutableCountry;

import java.util.concurrent.ConcurrentMap;

/**
 * Created by espinha on 1/23/17.
 */
public class Reporter extends LiveEntity{

    public Reporter(Continent continent) {
        super(continent);

    }

    @Override
    public void run() {

        boolean running = true;
        while(running) {
            continent.lookForNews(this);
        }
    }
}
