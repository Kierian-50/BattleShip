package test;

import battle.*;

import java.net.StandardSocketOptions;
import java.util.ArrayList;

/**
 * This class test the class game.
 */
public class TestGame {

    /**
     * The entry point of the program.
     * @param args The arguments.
     */
    public static void main(String[] args){
        //testGame();
        //testDisplayFromTeacher();
        //testDescription();
        testAnalyseShot();
    }

    /**
     * Test the constructor of the class Game.
     */
    public static void testGame(){

        System.out.println("Test the Game's class : \n");

        System.out.println("Test a normal case with the creation of a Game's object : \nView no errors :\n");

        Ship s1 = new Ship("Porte-avion",5);
        Ship s2 = new Ship("Porte-avion",5);

        ArrayList<Ship> aGoodFleet = new ArrayList<Ship>();

        aGoodFleet.add(s1);
        aGoodFleet.add(s2);

        Game game1 = new Game(aGoodFleet, "playerName1","playerName2",
                5,5, Mode.AA);

        System.out.println("We see that there is no error : in a normal case, the creation of the object works\n" +
                "Moreover, we see that the attributes have the right value : \n");

        System.out.println("auto = "+game1.getAuto().toString());
        System.out.println("current = "+game1.getCurrent().toString());
        System.out.println("captain = "+game1.getCaptain().toString());

        System.out.println("\nNow, we'll see what happen in error case : \n" +
                "The test will be with a wrong argument in the declaration of the object\n");

        Ship s4 = new Ship("Porte-avion",5);
        Ship s3 = new Ship("Porte-avion",5);

        ArrayList<Ship> aBadFleet = new ArrayList<Ship>();

        aGoodFleet.add(s4);
        aGoodFleet.add(s3);

        System.out.println("View error : \n");

        Game game2 = new Game(aBadFleet,"playerName1",null,-5,-5,Mode.HH);

        System.out.println("\nWe see that in a error case, the program blocks the errors.\n");

        System.out.println("End of the test.");

    }

    public static void testDisplayFromTeacher(){
        System.out.println("-------------\nThis method allows to test the display of the grid");

        Ship s1 = new Ship("fregate",3);
        Ship s2 = new Ship("fregate",3);
        Ship s3 = new Ship("patrouilleur",3);
        Ship s4 = new Ship("porte-avion",3);
        Ship s5 = new Ship("sous-marin",3);

        ArrayList<Ship> aGoodFleet = new ArrayList<Ship>();

        aGoodFleet.add(s1);
        aGoodFleet.add(s2);
        aGoodFleet.add(s3);
        aGoodFleet.add(s5);
        aGoodFleet.add(s4);

        Game game1 = new Game(aGoodFleet,"Player1","Player2",10,10,Mode.HH);
        game1.start();

        System.out.println("\nEnd of the test");
    }

    public static void testDescription(){
        Ship s1 = new Ship("fregate",3);
        Ship s2 = new Ship("fregate",3);
        Ship s3 = new Ship("patrouilleur",3);
        Ship s4 = new Ship("porte-avion",3);
        Ship s5 = new Ship("sous-marin",3);

        ArrayList<Ship> aGoodFleet = new ArrayList<Ship>();

        aGoodFleet.add(s1);
        aGoodFleet.add(s2);
        aGoodFleet.add(s3);
        aGoodFleet.add(s5);
        aGoodFleet.add(s4);

        Game game1 = new Game(aGoodFleet,"Player1","Player2",10,10,Mode.HH);
        System.out.println(game1.description());
    }


    public static void testAnalyseShot(){
        Ship s1 = new Ship("fregate",3);
        Ship s2 = new Ship("fregate",3);
        Ship s3 = new Ship("patrouilleur",3);
        Ship s4 = new Ship("porte-avion",3);
        Ship s5 = new Ship("sous-marin",3);

        ArrayList<Ship> aGoodFleet = new ArrayList<Ship>();

        aGoodFleet.add(s1);
        aGoodFleet.add(s2);
        aGoodFleet.add(s3);
        aGoodFleet.add(s5);
        aGoodFleet.add(s4);

        Game game1 = new Game(aGoodFleet,"Player1","Player2",10,10,Mode.HH);
        System.out.println("\nVisualiser : hit :");
        int[] tab = {0,0};
        System.out.println(game1.analyzeShot(tab));
        tab = new int[]{0, 2};
        System.out.println(game1.analyzeShot(tab));

        System.out.println("\nVisualiser : miss :");
        tab = new int[]{1,1};
        System.out.println(game1.analyzeShot(tab));
        tab = new int[]{0,0};
        System.out.println(game1.analyzeShot(tab));

        System.out.println("\nVisualiser : sunk :");
        tab = new int[]{0,1};
        System.out.println(game1.analyzeShot(tab));

        System.out.println("\nChange current :");
        /*game1.changeCurrent();

        System.out.println("\nVisualiser : hit :");
        tab = new int[]{0,0};
        System.out.println(game1.analyzeShot(tab));
        tab = new int[]{0, 2};
        System.out.println(game1.analyzeShot(tab));

        System.out.println("\nVisualiser : miss :");
        tab = new int[]{1,1};
        System.out.println(game1.analyzeShot(tab));
        tab = new int[]{0,0};
        System.out.println(game1.analyzeShot(tab));

        System.out.println("\nVisualiser : sunk :");
        tab = new int[]{0,1};
        System.out.println(game1.analyzeShot(tab));*/
    }
}
