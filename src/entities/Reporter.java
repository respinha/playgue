package entities;

import common.Report;
import worldregion.Continent;
import worldregion.MutableCountry;

import java.util.concurrent.ConcurrentMap;
import common.MedicalInformationCenter;
/**
 * Created by espinha on 1/23/17.
 */
public class Reporter extends LiveEntity{

    private final MedicalInformationCenter informationCenter;

    public Reporter(Continent continent, MedicalInformationCenter informationCenter) {
        super(continent);
        this.informationCenter = informationCenter;

    }

    @Override
    public void run() {

        while(alive) {
            Report report = continent.lookForNews(this); // blocking state
            informationCenter.update(this, report);

            alive = report.getMessage() != null;
        }
    }
}
