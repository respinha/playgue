package Tests;

import pt.ua.gboard.*;

import java.awt.*;
import java.io.*;

/**
 * Created by espinha on 1/25/17.
 */
public class MapCreator {

    public static final int seaSymbol = 0;
    public static final int[] earthSymbols = {1,2,3,4,5};
    public static final GBoard gboard = new GBoard("Map", 20, 39, 1); //lines, columns, layers

    public static void main(String[] args) throws IOException {

        gboard.pushInputHandler(new TestInputHandler());
        drawIslandMap(gboard);
    }

    public static void drawIslandMap(GBoard board) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("single_island.txt"));
        String line;

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

                FilledGelem gelem = new FilledGelem(color, 100);
                board.draw(gelem, l, i, 0);
            }

            l++;
        }
    }
}
