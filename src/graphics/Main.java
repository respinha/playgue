package graphics;

import common.Globals;
import entities.Civilization;
import entities.NursingTeam;
import entities.ResearchTeam;
import pt.ua.gboard.GBoard;
import region.MedicalInformationCenter;
import region.laboratory.BacteriaLaboratory;
import region.laboratory.MedicalLaboratory;
import region.worldregion.EarthRegion;
import region.worldregion.Location;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by espinha on 12/5/16.
 */
public class Main {

    public static void main(String[] args) {

        final GBoard gboard = new GBoard("Map", 20, 39, 1); //lines, columns, layers


        try {
            ArrayList<Location> locations = drawIslandMap(gboard);

            EarthRegion region = new EarthRegion(gboard, locations);
            MedicalInformationCenter center = new MedicalInformationCenter(gboard);

            Civilization civilization = new Civilization(gboard, region, center);


            BacteriaLaboratory bacteriaLaboratory = new BacteriaLaboratory(gboard);
            MedicalLaboratory medicalLaboratory = new MedicalLaboratory(gboard);

            NursingTeam nursingTeam = new NursingTeam(gboard, region, center, medicalLaboratory);
            ResearchTeam researchTeam = new ResearchTeam(gboard,region,center,medicalLaboratory);

            new Thread(nursingTeam).start();
            new Thread(civilization).start();
            new Thread(researchTeam).start();

            // info pannel
            /*JPanel content = new JPanel(new BorderLayout());
            content.setBorder(BorderFactory.createBevelBorder(0));

            gboard.frame().add(content);
            gboard.frame().pack();*/

            gboard.pushInputHandler(new MapInputHandler(region, bacteriaLaboratory));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Location> drawIslandMap(GBoard board) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("single_island.txt"));
        String line;

        ArrayList<Location> locations = new ArrayList<>();

        int l = 0;
        while((line = reader.readLine()) != null){

            for(int i = 0; i < line.length(); i++) {

                char c = line.charAt(i);
                int number = Character.getNumericValue(c);

                Color color = Globals.chooseColor(number);

                if(number > 0)
                    locations.add(new Location(new Point(i, l), number));

                ValuedFilledGelem gelem = new ValuedFilledGelem(color, 100, number);
                board.draw(gelem, l, i, 0);
            }

            l++;
        }

        return locations;
    }
}
