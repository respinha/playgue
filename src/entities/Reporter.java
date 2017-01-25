package entities;

import common.Report;
import region.laboratory.MedicalLaboratory;
import region.worldregion.Continent;

import region.MedicalInformationCenter;
/**
 * Created by espinha on 1/23/17.
 */
public class Reporter extends LiveEntity{

    private final MedicalInformationCenter informationCenter;
    private MedicalLaboratory laboratory;

    public Reporter(String name, Continent continent, MedicalInformationCenter informationCenter, MedicalLaboratory laboratory) {
        super(name,continent);
        this.informationCenter = informationCenter;
        this.laboratory = laboratory;

    }

    @Override
    public void run() {

        while(alive) {
            Report report = continent.lookForNews(this); // blocking state

            informationCenter.update(this, report);
            laboratory.inform(report);

            alive = report.getData() != null;
        }
    }

}
