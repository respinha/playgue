package graphics;

import pt.ua.gboard.FilledGelem;

import java.awt.*;

/**
 * Created by espinha on 1/26/17.
 */
public class ValuedFilledGelem extends FilledGelem {

    private int cellValue;
    public ValuedFilledGelem(Color color, double v, int cellValue) {
        super(color, v);

        this.cellValue = cellValue;
    }

    public int cellValue() {

        return cellValue;
    }
}
