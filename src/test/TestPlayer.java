package test;

import battle.*;

import java.util.ArrayList;

/**
 * This class test every class and method around the player.
 * @author Kierian Tirlemont
 */
public class TestPlayer {
    /**
     * The entry point of the program
     * @param args The arguments
     */
    public static void main(String[] args){
        //Test in the previous tp
        //There are useless here
        //testAutoPlayer();
        //testHumanPlayer();
        //testPlayerMethod();
        //testHumanPlayerReadConfiguration();
        //testHumanPlayerNewShot();
        //testHumanPlayerShipPlacement();
        //testPlayerIsSunk();
        testAutoPlayerShipPlacement();
    }

    /**
     * This class test the autoPlayer's class
     */
    public static void testAutoPlayer(){

        System.out.println("Test of the class autoPlayer : \n");

        System.out.println("Test a normal case : View no error :\n ");

        Ship s1 = new Ship("Porte-avion",5);
        Ship s2 = new Ship("Porte-avion",5);
        Ship s3 = new Ship("Porte-avion",5);
        Ship s4 = new Ship("Porte-avion",5);

        ArrayList<Ship> aGoodFleet = new ArrayList<Ship>();

        aGoodFleet.add(s1);
        aGoodFleet.add(s2);
        aGoodFleet.add(s3);
        aGoodFleet.add(s4);

        AutoPlayer autoPlayer1 = new AutoPlayer(aGoodFleet,"autoPlayer1", 50,50);

        System.out.println("End of normal case\nTest an error case : View errors : \n");

        Ship s5 = new Ship(null,5);
        Ship s6 = new Ship("Porte-avion",5);
        Ship s7 = new Ship("Porte-avion",-5);
        Ship s8 = new Ship("Porte-avion",5);

        ArrayList<Ship> aWrongFleet = new ArrayList<Ship>();

        aGoodFleet.add(s1);
        aGoodFleet.add(s2);
        aGoodFleet.add(s3);
        aGoodFleet.add(s4);

        AutoPlayer autoPlayer2 = new AutoPlayer(aWrongFleet,null,5,5);

        System.out.println("\nEnd of the test.");
    }

    /**
     * This class test the humanPlayer's class.
     */
    public static void testHumanPlayer(){
        System.out.println("-----------------\nTest of the humanPlayer : \n");

        Ship s1 = new Ship("Porte-avion",5);
        Ship s2 = new Ship("Porte-avion",5);
        Ship s3 = new Ship("Porte-avion",5);
        Ship s4 = new Ship("Porte-avion",5);

        ArrayList<Ship> aGoodFleet = new ArrayList<Ship>();

        aGoodFleet.add(s1);
        aGoodFleet.add(s2);
        aGoodFleet.add(s3);
        aGoodFleet.add(s4);

        System.out.println("Test a normal case : view any errors ");

        HumanPlayer humanPlayer1 = new HumanPlayer(aGoodFleet,"humanPlayer1",5,5);

        System.out.println("End of the normal case\n");

        System.out.println("Test an error case : view error\n");

        Ship s5 = new Ship(null,5);
        Ship s6 = new Ship("Porte-avion",5);
        Ship s7 = new Ship("Porte-avion",-5);
        Ship s8 = new Ship("Porte-avion",5);

        ArrayList<Ship> aWrongFleet = new ArrayList<Ship>();

        aWrongFleet.add(s5);
        aWrongFleet.add(s6);
        aWrongFleet.add(s7);
        aWrongFleet.add(s8);

        HumanPlayer humanPlayer = new HumanPlayer(aWrongFleet,null,5,5);

        System.out.println("\nEnd of the test.");
    }

    /**
     * This class test the method of the player's class.
     */
    public static void testPlayerMethod(){
        System.out.println("------------\nTest the method of the player's class :");

        Ship s1 = new Ship("Porte-avion",5);
        Ship s2 = new Ship("Porte-avion",5);
        Ship s3 = new Ship("Porte-avion",5);
        Ship s4 = new Ship("Porte-avion",5);

        ArrayList<Ship> aGoodFleet = new ArrayList<Ship>();

        aGoodFleet.add(s1);
        aGoodFleet.add(s2);
        aGoodFleet.add(s3);
        aGoodFleet.add(s4);

        System.out.println("Test a normal case on the initializeMyGrid's method : " +
                "\nview any errors and the elements of the square's array : \n");

        int width = 5;
        int height = 6;

        Player player1 = new HumanPlayer(aGoodFleet,"humanPlayer1",width,height);

        Square tabMyGrid [][] = player1.getMyGrid();

        for(int i = 0 ; i<width;i++){
            for(int j = 0 ; j<height;j++){
                System.out.println(tabMyGrid[i][j].toString());
            }
        }

        System.out.println("\nWe see that the y max is 5 because the height is 6\n" +
                "We also see that the x max is 4 because the width is 5.");

        System.out.println("Test a normal case on the initializeOpponentGrid's method : " +
                "\nview any errors and the elements of the square's array : \n");

        Square tabOpponentGrid [][] = player1.getOpponentGrid();

        for(int i = 0 ; i<width;i++){
            for(int j = 0 ; j<height;j++){
                System.out.println(tabOpponentGrid[i][j].toString());
            }
        }
        System.out.println("\nWe see that the y max is 5 because the height is 6\n" +
                "We also see that the x max is 4 because the width is 5.");

        System.out.println("\nEnd of the test");
    }

    /**
     * This class test the method newShot.
     */
    public static void testHumanPlayerNewShot(){
        System.out.println("-----------------\nTest of the nextShot from humanPlayer : \n" +
                "\n\"ShipPlacementPlayer1.txt\"");

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

        System.out.println("Test a normal case : view any errors \n");

        int[] tab = new int[2];

        HumanPlayer humanPlayer1 = new HumanPlayer(aGoodFleet,"humanPlayer1",10,10);
        tab = humanPlayer1.newShot();
        System.out.println("Display the value enter : \n");

        System.out.println(tab[0]);
        System.out.println(tab[1]);
        System.out.println("\nEnd of the test");
    }

    /**
     * This method tests the method shipPlacement.
     */
    public static void testHumanPlayerShipPlacement(){
        System.out.println("-----------------\nTest of the humanPlayer shipConfiguration : \n\n" +
                "Test a normal case with the file \"ShipPlacementPlayer1.txt\n");

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

        System.out.println("Test a normal case : view any errors \n");

        HumanPlayer humanPlayer1 = new HumanPlayer(aGoodFleet,"humanPlayer1",10,10);

        System.out.println("\nEnd of the test");
    }

    /**
     * This method tests the methods isSunk et allSunk
     */
    public static void testPlayerIsSunk(){
        System.out.println("-----------------\nTest of the humanPlayer shipConfiguration : \n");

        Ship s1 = new Ship("fregate",3);
        Ship s2 = new Ship("fregate",3);
        Ship s3 = new Ship("patrouilleur",3);
        Ship s4 = new Ship("porte-avion",3);
        Ship s5 = new Ship("sous-marin",3);

        ArrayList<Ship> aGoodFleet = new ArrayList<Ship>();

        aGoodFleet.add(s1);
        aGoodFleet.add(s2);


        System.out.println("Test a normal case : view any errors : the file is \"TestIsSunkAllSunk.txt\"\n" +
                "\nPrint true : \n");

        Player player = new HumanPlayer(aGoodFleet,"humanPlayer1",10,10);

        player.isSunk(0,0);
        player.isSunk(0,1);
        player.isSunk(0,2);

        player.isSunk(4,9);
        player.isSunk(5,9);
        player.isSunk(6,9);

        System.out.println(player.allSunk());

        System.out.println("\nEnd of the test");
    }

    private static void testAutoPlayerShipPlacement(){
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
        aGoodFleet.add(s4);
        aGoodFleet.add(s4);
        aGoodFleet.add(s4);
        aGoodFleet.add(s4);
        aGoodFleet.add(s4);
        aGoodFleet.add(s4);




        System.out.println("Test a normal case : view any errors \n");

        AutoPlayer bot = new AutoPlayer(aGoodFleet,"humanPlayer1",10,10);

        bot.displayMyGrid();

    }

}
