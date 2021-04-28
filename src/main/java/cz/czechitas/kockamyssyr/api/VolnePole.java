package cz.czechitas.kockamyssyr.api;

public class VolnePole {

    private int souradniceX;
    private int SouradniceY;
    private boolean kVymazani;

    public VolnePole(int souradniceX, int souradniceY) {
        this.souradniceX = souradniceX;
        SouradniceY = souradniceY;
    }

    public int getSouradniceX() {
        return souradniceX;
    }

    public void setSouradniceX(int newValue) {
        souradniceX = newValue;
    }

    public int getSouradniceY() {
        return SouradniceY;
    }


    public void setSouradniceY(int newValue) {
        SouradniceY = newValue;
    }

    public boolean iskVymazani() {
        return kVymazani;
    }

    public void setkVymazani(boolean newValue) {
        kVymazani = newValue;
    }
}
