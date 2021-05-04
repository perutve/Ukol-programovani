package cz.czechitas.kockamyssyr;

import java.util.*;
import java.util.stream.*;
import cz.czechitas.kockamyssyr.api.*;
import cz.czechitas.kockamyssyr.engine.swing.*;

public class HlavniProgram {

    private Cat tom;
    private Mouse jerry;

    private List<Tree> souradniceStromy;
    private List<VolnePole> souradniceVolnePole;

    private final Random generatorNahodnychCisel = new Random();

    public void main(String[] args) {

        souradniceStromy = vygenerujBludiste();
        souradniceVolnePole = vygenerujVolnePole();
        start();

        //view -> parametr info (ctrl+P)
        // ; code -> completion -> smart Type(CTRL+shift+space)(případně Basic)

    }

//    public void brain(Cat kocka){
//        while (jerry.isAlive()){
//            int rozdilSouradnicX = ziskejRozdilXSouradnic(kocka, jerry);
//            int rozdilSouradnicY = ziskejRozdilYSouradnic(kocka, jerry);
//            posunSeOX(kocka, rozdilSouradnicX);
//            posunSeOY(kocka, rozdilSouradnicY);
//        }
//    }

    private void start(){

        Cat bubu = createCat(100, 100);
        Cat lary = createCat(100, 800);
        Cat coco = createCat(1500, 100);
        Cat fifi = createCat(1500, 800);

        vygenerujJidlo();

        jerry = new Mouse(900, 400);

        tom = new Cat(souradniceVolnePole.get(vygenerujIndex()).getSouradniceX(),
                souradniceVolnePole.get(vygenerujIndex()).getSouradniceY()); // tom.setBrain(new KeyboardBrain());

        jerry.setBrain(new KeyboardBrain(KeyCode.UP, KeyCode.LEFT, KeyCode.DOWN, KeyCode.RIGHT));

        tom.setBrain(this::controlPlayer);

        catRandomMovement(bubu);
        catRandomMovement(lary);
        catRandomMovement(coco);
        catRandomMovement(fifi);
    }

    private void catRandomMovement(Cat kocka){
        RandomMovement nahodnyPohybKocky = new RandomMovement(jerry, kocka);
        nahodnyPohybKocky.setRandomBrain();
    }

    private Cat createCat(int souradniceX, int souradniceY){
        Cat kocka = new Cat(souradniceX, souradniceY);
        for (VolnePole volnePole : souradniceVolnePole){
            if (souradniceX == volnePole.getSouradniceX() && souradniceY == volnePole.getSouradniceY()){
                volnePole.setkVymazani(true);
            }
        }
        return kocka;
    }

    private List<VolnePole> vygenerujVolnePole(){

        souradniceVolnePole = new ArrayList<>();

        for (int x = 100; x <= 1500; x = x + 50){
            for (int y = 100; y <= 800; y = y + 50){
                souradniceVolnePole.add(new VolnePole(x, y));
            }
        }

        for (VolnePole volnePole : souradniceVolnePole ){
            for (Tree vytvorenyStrom : souradniceStromy){
                if (volnePole.getSouradniceX() == vytvorenyStrom.getX() &&
                        volnePole.getSouradniceY() == vytvorenyStrom.getY()){
                    volnePole.setkVymazani(true);
                }
            }
        }
        return souradniceVolnePole;
    }

    private List<VolnePole> filterVolnePole(){
        souradniceVolnePole = souradniceVolnePole.stream().filter((objektZPole) ->
                !objektZPole.iskVymazani()).collect(Collectors.toList());
        return souradniceVolnePole;
    }

    private int vygenerujIndex(){
        return generatorNahodnychCisel.nextInt(souradniceVolnePole.size() - 1);
    }

    private void vygenerujJidlo(){

        souradniceVolnePole = filterVolnePole();
        VolnePole jidlo;

        for (int i = 0; i <= 20; i++){

            jidlo = souradniceVolnePole.get(vygenerujIndex());
            new Cheese(jidlo.getSouradniceX(), jidlo.getSouradniceY());

            souradniceVolnePole.remove(jidlo);
            jidlo = souradniceVolnePole.get(vygenerujIndex());

            new Meat(jidlo.getSouradniceX(), jidlo.getSouradniceY());
            souradniceVolnePole.remove(jidlo);

        }
    }

    private List<Tree> vygenerujBludiste(){

        souradniceStromy = new ArrayList<>();

        for (int x = 50; x <= 1550; x = x + 50){
            souradniceStromy.add(new Tree(x, 50)); //Obrázek o velikosti 50 px se bije s obrázkem o velikosti 50 px
            souradniceStromy.add(new Tree(x, 850));
        }

        for (int y = 100; y <= 800; y = y + 50){
            souradniceStromy.add(new Tree(50, y));
            souradniceStromy.add(new Tree(1550, y));
        }

        for (int x = 250; x <= 650; x = x + 50){
            souradniceStromy.add(new Tree(x, 150));
            souradniceStromy.add(new Tree(x, 750));
            souradniceStromy.add(new Tree(x + 700, 150));
            souradniceStromy.add(new Tree(x + 700, 750));
        }

        for (int y = 250; y <= 650; y = y + 50){
            souradniceStromy.add(new Tree(150, y));
            souradniceStromy.add(new Tree(1450, y));
        }

        for (int y = 350; y <= 550; y = y + 50){
            souradniceStromy.add(new Tree(650, y));
            souradniceStromy.add(new Tree(950, y));
        }

        for (int y = 100; y <= 150; y = y + 50){
            souradniceStromy.add(new Tree(800, y));
            souradniceStromy.add(new Tree(800, y + 650));
        }

        for (int y = 300; y <= 350; y = y + 50){
            souradniceStromy.add(new Tree(800, y));
            souradniceStromy.add(new Tree(800, y + 250));
        }

        for (int y = 200; y <= 250; y = y + 50){
            souradniceStromy.add(new Tree(400, y));
            souradniceStromy.add(new Tree(1200, y));
            souradniceStromy.add(new Tree(400, y + 450));
            souradniceStromy.add(new Tree(1200, y + 450));
        }

        for (int x = 300; x <= 500; x = x + 50){
            souradniceStromy.add(new Tree(x, 350));
            souradniceStromy.add(new Tree(x + 800, 350));
            souradniceStromy.add(new Tree(x, 550));
            souradniceStromy.add(new Tree(x + 800, 550));
        }

        for (int x = 750; x <= 850; x = x + 50){
            souradniceStromy.add(new Tree(x, 450));
        }


        for (int x = 750; x <= 850; x = x + 50){
            souradniceStromy.add(new Tree(x, 250));
            souradniceStromy.add(new Tree(x, 650));
        }

        souradniceStromy.add(new Tree(400, 450));
        souradniceStromy.add(new Tree(1200, 450));

        return souradniceStromy;
    }

    private void controlPlayer(Player p){
        while (jerry.isAlive()){
            int rozdilSouradnicX = ziskejRozdilXSouradnic(tom, jerry);
            int rozdilSouradnicY = ziskejRozdilYSouradnic(tom, jerry);
            posunSeOX(tom, rozdilSouradnicX);
            posunSeOY(tom, rozdilSouradnicY);
        }
    }

    private int ziskejRozdilXSouradnic(Cat kocka, Mouse mys){
        return mys.getX() - kocka.getX();
    }

    private int ziskejRozdilYSouradnic(Cat kocka, Mouse mys) {
        return mys.getY() - kocka.getY();
    }

    private void posunSeOX(Cat kocka, int rozdilX){
        if (rozdilX < 0){
            kocka.turnLeft();
            kocka.turnLeft();
            kocka.moveForward(Math.abs(rozdilX));
            kocka.turnRight();
            kocka.turnRight();
        }
        else {
            kocka.moveForward(rozdilX);
        }

    }

    private void posunSeOY(Cat kocka, int rozdilY){
        if (rozdilY < 0){
            kocka.turnLeft();
            kocka.moveForward(Math.abs(rozdilY));
            kocka.turnRight();
        }
        else {
            kocka.turnRight();
            kocka.moveForward(rozdilY);
            kocka.turnLeft();
        }
    }


}
