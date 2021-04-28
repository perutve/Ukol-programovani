package cz.czechitas.kockamyssyr.engine.swing;

import java.util.*;
import cz.czechitas.kockamyssyr.api.*;

public class RandomMovement {

    private Cat kocka;
    private Mouse mys;

    public RandomMovement(Mouse mys, Cat kocka) {
        this.kocka = kocka;
        this.mys = mys;
    }

    public void setRandomBrain(){
          kocka.setBrain(this::controlPlayer);
    }

    private void controlPlayer(Player p){
        while (mys.isAlive()){
              randomPohyb(kocka);
        }
    }

    private void randomPohyb(Cat kocka) {

        Random generatorNahodnychCisel = new Random();
        int vyberPohybu;

        if (kocka.isPossibleToMoveForward()){
            kocka.moveForward();
        } else {
            vyberPohybu = generatorNahodnychCisel.nextInt(2);
            if (vyberPohybu == 0){
                kocka.turnRight();
            } else {
                kocka.turnLeft();
            }

        }

    }
}
