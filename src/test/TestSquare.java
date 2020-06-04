package test;

import battle.Square;

/**
 * This class tests the class's Square.
 */
public class TestSquare {

    /**
     * The entry point of the program.
     * @param ars The arguments.
     */
    public static void main(String[] ars){
        testSquare();
    }

    /**
     * This method tests the Square's class.
     */
    public static void testSquare(){

        System.out.println("Test of the Square's class :\n\nWe'll begin with a normal case :");

        System.out.println("Creation of a square's object : view no error : \n");

        Square square1 = new Square(0,0);

        System.out.println("We see that there is no error : the constructor works in a normal case\n" +
                "Print the value of the object (toString) : \n");

        System.out.println(square1.toString());

        System.out.println("\nNow, we'll try the method setBusy() in a normal case :\n" +
                "Print the value of the object (toString) : \n");

        square1.setBusy();

        if(!square1.isFree()){
            System.out.println("OK ! The square is not free");
        }else{
            System.out.println("Not ok !");
        }

        System.out.println(square1.toString());

        System.out.println("\nEnd of the test in normal case.\nTest the square's class in error case : \n");

        System.out.println("Creation of the square's object in an error case : \nView error :\n");

        Square square2 = new Square(-5,-2);

        System.out.println("\nWe see that the program blocks the forbidden value.\n");

        System.out.println("End of the test.");

    }
}
