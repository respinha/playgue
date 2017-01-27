package graphics;

import Tests.TestInputHandler;
import entities.Bacteria;
import entities.Civilization;
import pt.ua.gboard.FilledGelem;
import pt.ua.gboard.GBoard;
import pt.ua.gboard.Gelem;
import region.MedicalInformationCenter;
import region.laboratory.BacteriaLaboratory;
import region.worldregion.EarthRegion;
import region.worldregion.Location;
import region.worldregion.Zone;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipFile;

/**
 * Created by espinha on 12/5/16.
 */
public class GMap {


    public static void main(String[] args) {

        final int seaSymbol = 0;
        final int[] earthSymbols = {1,2,3,4,5};
        final GBoard gboard = new GBoard("Map", 20, 39, 1); //lines, columns, layers


        try {
            ArrayList<Location> locations = drawIslandMap(gboard);

            EarthRegion region = new EarthRegion(gboard, locations);
            MedicalInformationCenter center = new MedicalInformationCenter(gboard);

            Civilization civilization = new Civilization(gboard, region, center);

            new Thread(civilization).start();
            BacteriaLaboratory bacteriaLaboratory = new BacteriaLaboratory(gboard);

            gboard.pushInputHandler(new MapInputHandler(region, bacteriaLaboratory, null));
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

                Color color;
                switch (number){

                    case 0: color = Color.BLUE;
                        break;
                    case 1: color = Color.orange;
                        break;
                    case 2: color = Color.magenta;
                        break;
                    case 3:
                    default: color = Color.RED;
                        break;
                }

                if(number > 0)
                    locations.add(new Location(new Point(i, l), number));

                ValuedFilledGelem gelem = new ValuedFilledGelem(color, 100, number);

                Gelem tmp = (Gelem) gelem;

                ValuedFilledGelem tmp2 = (ValuedFilledGelem) tmp;
                if(tmp2.cellValue() > 0)
                    System.out.println("AI Q PICA");

                board.draw(gelem, l, i, 0);

            }

            l++;
        }

        return locations;
    }
}
