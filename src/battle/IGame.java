package battle;

/**
 * This interface is implemented by the class game.
 * It implements three methods.
 * @author Kierian Tirlemont
 */
public interface IGame {

    /**
     * This method returns the description of the game as a String.
     * This description can be read by a file.
     * @return The description of the game as a String.
     */
    public String description();

    /**
     * This method is the game loop which displays the
     * description at the start, indicates who starts
     * and launches the game loop which asks the current
     * player to shoot each turn, then analyzes the result.
     */
    public void start();

    /**
     * This class realizes what needs to be done when the
     * game is over and gives the winner.
     */
    public void endOfGame();

}
