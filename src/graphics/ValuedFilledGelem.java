package graphics;

import pt.ua.gboard.FilledGelem;

import java.awt.*;

/**
 * Subtype of {@link FilledGelem} which has an EarthZone's population density value.
 */
public class ValuedFilledGelem extends FilledGelem {

    private int cellValue;
    private int state = 0;
    
    public ValuedFilledGelem(Color color, double v, int cellValue) {
        super(color, v);

        assert color != null;
        assert cellValue >= 0;

        this.cellValue = cellValue;
    }

    public int cellValue() {

        return cellValue;
    }


    public void release() {
        state = 0;
    }
    
    public int state() {
        return state;
    }

    public void mark() {
        state = 1;
    }
}
