package test;

import battle.BattleShip;

/**
 * This class test the class BattleShip.
 * @author Kierian Tirlemont
 */
public class TestBattleShip {

    /**
     * The entry point of the program.
     * @param args The arguments.
     */
    public static void main(String[] args){
        testBattleShip();
    }

    /**
     * This method test the BattleShip's class.
     */
    public static void testBattleShip(){

        System.out.println("Test the BattleShip's class\n");

        System.out.println("Test a normal case on the creation of the object : view any error\n");

        System.out.println("(The constructor will call the method printConfiguration which " +
                "prints the configuration)\n");

        BattleShip battleShip1 = new BattleShip("config1.txt","playerName1",
                "playerName2");

        System.out.println("We see that there is any errors : every works in normal case.\n");

        System.out.println("Now, test an error case with a bad configuration file :\n");

        BattleShip battleShip2 = new BattleShip("configError.txt","playerName1",
                "playerName2");

        System.out.println("We see that in an error case the class stop everything to don't continue with errors\n");

        System.out.println("Now, test an error case with null's argument : \n");

        BattleShip battleShip3 = new BattleShip(null,"playerName1",
                null);

        System.out.println("\nWe see that the program stops when he sees that one argument was null.\n");
        System.out.println("End of the test.");
    }
}
