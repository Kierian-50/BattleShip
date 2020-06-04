package battle;

/**
 * This class is the exception for a wrong placement of the boat.
 * This class is extends to Throwable.
 */
public class ForbiddenPlacementException extends Throwable {

    /**
     * The constructor of the class.
     * @param s The error message.
     */
    public ForbiddenPlacementException(String s){
        super(s);
    }

    /**
     * The constructor of the class without error message.
     */
    public ForbiddenPlacementException(){
        super();
    }
}
