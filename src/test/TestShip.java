package test;

import battle.Direction;
import battle.Ship;

/**
 * This class tests the Ship's class.
 */
public class TestShip {

    /**
     * The entry point of the program
     * @param args The arguments
     */
    public static void main(String[] args){
        //testConstructorShip();
        //testMethodShip();
        testContains();
    }

    /**
     * This method tests the constructor of the Ship's class.
     */
    public static void testConstructorShip(){

        System.out.println("Test of the Ship's class\n\nNow, we'll see the test od the constructor :\n");

        System.out.println("We begin with a normal case :\nView no error :\n");

        Ship ship1 = new Ship("Le Flamant",2);

        System.out.println("We see that in normal case, there is no error.\n");

        System.out.println("Now, test an error case with bad arguments :\nView errors :");

        Ship ship2 = new Ship(null,-5);

        System.out.println("\nWe see that the program works in an error case.\n");

        System.out.println("End of the test of the Ship's class on the constructor\n");

    }

    /**
     * This method test two methods (addHit and isSunk) of the ship's class.
     */
    public static void testMethodShip(){

        System.out.println("\n--------------\nTest of the Ship's class.\nNow, we'll test the methods.\n");

        System.out.println("We'll begin with a normal case with the method addHit and the isSunk\n");

        Ship ship1 = new Ship("Le Triomphant",2);

        System.out.println(ship1.toString());

        System.out.println("We see the ship's object.\nNow, we'll use the method addHit two times\n");

        ship1.addHit();
        ship1.addHit();

        System.out.println(ship1.toString());

        System.out.println("The ship is sunk, so we'll see if the method isSunk works :\n");

        System.out.println(ship1.isSunk());

        if(ship1.isSunk()){
            System.out.println("Ok");
        }else{
            System.out.println("Pas ok !");
        }

        System.out.println("We see that the method return true, so the methods work.\n");

        System.out.println("End of the test.");
    }

    /**
     * This method tests the method contains of ship.
     */
    public static void testContains(){

        System.out.println("\n--------------\nTest contains :\n");

        Ship ship1 = new Ship("Le Triomphant",3);
        ship1.setyOrigin(0);
        ship1.setxOrigin(0);
        ship1.setDirection(Direction.HORIZONTAL);

        System.out.println("Test en horizontal : \n");

        System.out.println("true : "+ship1.contains(0,0));
        System.out.println("true : "+ship1.contains(1,0));
        System.out.println("true : "+ship1.contains(2,0));
        System.out.println("false : "+ship1.contains(3,0));
        System.out.println("false : "+ship1.contains(4,0));

        Ship ship2 = new Ship("Le Triomphant",3);
        ship2.setyOrigin(0);
        ship2.setxOrigin(0);
        ship2.setDirection(Direction.VERTICAL);

        System.out.println("\nTest en vertical : \n");

        System.out.println("true : "+ship2.contains(0,0));
        System.out.println("true : "+ship2.contains(0,1));
        System.out.println("true : "+ship2.contains(0,2));
        System.out.println("false : "+ship2.contains(0,3));
        System.out.println("false : "+ship2.contains(0,4));

        System.out.println("\nFin du test\n");
    }

}
