package cz.czechitas.kockamyssyr.engine.swing;

public class Souradnice {

    private int souradniceX;
    private int souradniceY;

    public Souradnice(int souradniceX, int souradniceY) {
        this.souradniceX = souradniceX;
        this.souradniceY = souradniceY;
    }

    public int getSouradniceX() {
        return souradniceX;
    }

    public void setSouradniceX(int newValue) {
        souradniceX = newValue;
    }

    public int getSouradniceY() {
        return souradniceY;
    }

    public void setSouradniceY(int newValue) {
        souradniceY = newValue;
    }
}
