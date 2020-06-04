package battle;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.util.ArrayList;

/**
 * This class represents a specific game with two players and a fleet.
 * Create the two players according to the mode which indicates
 * the types of the players and her fleet.
 * This class is responsible to manage the progression of the game.
 * @author Kierian Tirlemont
 */
public class Game implements IGame {

    private Player current;
    private Player captain;
    private Player auto;

    private ArrayList<Ship> fleet;
    private ShotResult result;
    private Mode mode;

    /**
     * The constructor of the class which initialize the attribute.
     * @param fleet The list of ship of the player.
     * @param playerName1 The name of the first player.
     * @param playerName2 The name of the second player.
     * @param width The width of the grid.
     * @param height The height of the grid.
     * @param mode The mode of the game.
     */
    public Game(ArrayList<Ship> fleet, String playerName1, String playerName2, int width, int height, Mode mode)
            throws IllegalArgumentException{

        try {

            if(fleet == null){
                throw new IllegalArgumentException("Game : error : The fleet is null");
            }
            if(playerName1 == null){
                throw new IllegalArgumentException("Game : error : The playerName1 is null");
            }
            if(playerName2 == null){
                throw new IllegalArgumentException("Game : error : The playerName2 is null");
            }
            if(width<=0){
                throw new IllegalArgumentException("Game : error : The width is inferior to 0");
            }
            if(height<=0){
                throw new IllegalArgumentException("Game : error : The height is inferior to 0");
            }

            if(mode == Mode.HH){
                this.captain = new HumanPlayer(fleet,playerName1,width,height);
                this.auto = new HumanPlayer(fleet,playerName2,width,height);
            }else if(mode == Mode.HA){
                this.captain = new HumanPlayer(fleet,playerName1,width,height);
                this.auto = new AutoPlayer(fleet,playerName2,width,height);
            }else if(mode == Mode.AA){
                this.captain = new AutoPlayer(fleet,playerName1,width,height);
                this.auto = new AutoPlayer(fleet,playerName2,width,height);
            }

            this.mode = mode;
            this.current = this.captain;

        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }

    }

    /**
     * This method allows to change the current player.
     */
    private void changeCurrent(){
        if(this.current == this.auto){
            this.current = this.captain;
        }else{
            this.current = this.auto;
        }
    }

    /**
     * This method allows to call the method which calls to a
     * player the new shot and this method return it.
     * @param player The player who shot.
     * @return The position of the shot.
     */
    public int[] readShot(Player player){
        if(player==null){
            throw new IllegalArgumentException("readShot : player is null");
        }
        int [] ret = player.newShot();
        return ret;
    }

    /**
     * This method analyses the shot. It watch if the shot hit a ship.
     * @param shot The position of the shot.
     * @return If the ship is hit or sunk or miss
     */
    public ShotResult analyzeShot(int[] shot){
        ShotResult ret = ShotResult.MISS;

        if(this.current == this.auto){
            if(!this.captain.myGrid[shot[0]][shot[1]].isFree()  && !this.captain.myGrid[shot[0]][shot[1]].isHit()) {

                for(Ship s : this.captain.fleet){
                    if(s.getDirection() == Direction.HORIZONTAL && shot[0]>=s.getxOrigin() &&
                            shot[0]<= (s.getxOrigin() + s.getSize() -1) && shot[1] == s.getyOrigin()){

                        ret = ShotResult.HIT;
                        s.addHit();

                        if(s.isSunk()){
                            ret = ShotResult.SUNK;
                        }

                    }else if(s.getDirection() == Direction.VERTICAL && shot[1]>=s.getyOrigin() &&
                            shot[1]<= (s.getyOrigin() + s.getSize() -1) && shot[0] == s.getxOrigin()){

                        ret = ShotResult.HIT;
                        s.addHit();

                        if(s.isSunk()){
                            ret = ShotResult.SUNK;
                        }

                    }
                }

            }

            if(ret == ShotResult.MISS){
                this.captain.myGrid[shot[0]][shot[1]].setHit(true);
                this.auto.opponentGrid[shot[0]][shot[1]].setHit(true);
            }else if(ret == ShotResult.HIT || ret == ShotResult.SUNK){
                this.captain.myGrid[shot[0]][shot[1]].setHit(true);
                this.auto.opponentGrid[shot[0]][shot[1]].setHit(true);
                this.auto.opponentGrid[shot[0]][shot[1]].setBusy();
            }

        }else if(this.current == this.captain){
            if(!this.auto.myGrid[shot[0]][shot[1]].isFree() && !this.auto.myGrid[shot[0]][shot[1]].isHit()) {

                for(Ship s : this.auto.fleet){
                    if(s.getDirection() == Direction.HORIZONTAL && shot[0]>=s.getxOrigin() &&
                            shot[0]<= (s.getxOrigin() + s.getSize() -1) && shot[1] == s.getyOrigin()){

                        ret = ShotResult.HIT;
                        s.addHit();

                        if(s.isSunk()){
                            ret = ShotResult.SUNK;
                        }

                    }else if(s.getDirection() == Direction.VERTICAL && shot[1]>=s.getyOrigin() &&
                            shot[1]<= (s.getyOrigin() + s.getSize() -1) && shot[0] == s.getxOrigin()){

                        ret = ShotResult.HIT;
                        s.addHit();

                        if(s.isSunk()){
                            ret = ShotResult.SUNK;
                        }

                    }
                }

            }

            if(ret == ShotResult.MISS){
                this.auto.myGrid[shot[0]][shot[1]].setHit(true);
                this.captain.opponentGrid[shot[0]][shot[1]].setHit(true);
            }else if(ret == ShotResult.HIT || ret == ShotResult.SUNK){
                this.auto.myGrid[shot[0]][shot[1]].setHit(true);
                this.captain.opponentGrid[shot[0]][shot[1]].setHit(true);
                this.captain.opponentGrid[shot[0]][shot[1]].setBusy();
            }

        }

        return ret;
    }

    public boolean allSunk(Player aPlayer){
        return aPlayer.allSunk();
    }

    /**
     * This method returns the description of the game as a String.
     * This description can be read by a file.
     * @return The description of the game as a String.
     */
    @Override
    public String description() {
        String retName = " _           _   _   _           _     _       \n" +
                "| |         | | | | | |         | |   (_)      \n" +
                "| |__   __ _| |_| |_| | ___  ___| |__  _ _ __  \n" +
                "| '_ \\ / _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\ \n" +
                "| |_) | (_| | |_| |_| |  __/\\__ \\ | | | | |_) |\n" +
                "|_.__/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/ \n" +
                "                                        | |    \n" +
                "                                        |_|   ";

        String retDessin = "                                     |__\n" +
                "                                     |\\/\n" +
                "                                     ---\n" +
                "                                     / | [\n" +
                "                              !      | |||\n" +
                "                            _/|     _/|-++'\n" +
                "                        +  +--|    |--|--|_ |-\n" +
                "                     { /|__|  |/\\__|  |--- |||__/\n" +
                "                    +---------------___[}-_===_.'____                 /\\\n" +
                "                ____`-' ||___-{]_| _[}-  |     |_[___\\==--            \\/   _\n" +
                " __..._____--==/___]_|__|_____________________________[___\\==--____,------' .7\n" +
                "|                                                                     BB-61/\n" +
                " \\_________________________________________________________________________|";

        String retDessinAircraftCarrier = "\n" +
                "                     -+-\n" +
                "                   ---#---\n" +
                "                   __|_|__            __\n" +
                "                   \\_____/           ||\\________\n" +
                "     __   __   __  \\_____/            ^---------^\n" +
                "    ||\\__||\\__||\\__|___  | '-O-`\n" +
                "    -^---------^--^----^___.-------------.___.--------.___.------\n" +
                "    `-------------|-------------------------------|-------------'\n" +
                "           \\___      |     \\    o O o    /     |      ___/\n" +
                "               \\____/        \\         /        \\____/\n" +
                "                   |           \\     /           |\n" +
                "                   |             \\|/             |\n" +
                "                   |              |              |\n" +
                " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        String desc = "-----------------------------------------------------------------------------------\n"
                     +"|                  Welcome in the battleship's application game !                 |\n"
                     +"|Before you start playing, I must explain the rules:                              |\n"
                     +"|                                                                                 |\n"
                     +"|First of all, you've a file name data. In this file, you find every required file|\n"
                     +"|such as the configuration file which is very important. Indeed, in this file you |\n"
                     +"|can change the size of the grid, the game mode, and the ship (and her size).     |\n"
                     +"|Be careful to respect the writing in this file otherwise there will be an error. |\n"
                     +"|A good example of the writing in this file :                                     |\n"
                     +"|                                                                                 |\n"
                     +"|10:15:                                                                           |\n"
                     +"|mode:HH:                                                                         |\n"
                     +"|fregate:4:                                                                       |\n"
                     +"|Of course, you can put several ships.                                            |\n"
                     +"|                                                                                 |\n"
                     +"|Then, you will find another file named for the placement of the ship. This is the|\n"
                     +"|second file that ask to the launch of the application. Once again, be careful to |\n"
                     +"|respect the writing in this file. Moreover, every ship declares in the configura-|\n"
                     +"|tion must be call in the ship placement file with the same name.                 |\n"
                     +"|You have the name of the ship, the X position, the Y position, the orientation.  |\n"
                     +"|A good example of the writing in this file :                                     |\n"
                     +"|fregate:0:4:HORIZONTAL:                                                          |\n"
                     +"|                                                                                 |\n"
                     +"|That's it for the configuration file. Now, you must know that there is two mode  |\n"
                     +"|HH for the human against human or HA human against computer. You can change it in|\n"
                     +"|the configuration file. Otherwise, this is a normal battleship game, I'll ask you|\n"
                     +"|and you will know if your shot hit your enemy's ship.                            |\n"
                     +"|                                                                                 |\n"
                     +"|Be careful in the mode HA or AA, the number of ship is restricted, you can't have|\n"
                     +"|more than 80% of square which is prohibited. In a normal case, there is no       |\n"
                     +"|problem.                                                                         |\n"
                     +"|                                                                                 |\n"
                     +"|                              GOOD LUCK SAILOR !                                 |\n"
                     +"-----------------------------------------------------------------------------------\n";

        return retName+"\n\n\n"+retDessin+"\n\n\n"+retDessinAircraftCarrier+"\n\n\n"+desc;
    }

    /**
     * This method is the game loop which displays the
     * description at the start, indicates who starts
     * and launches the game loop which asks the current
     * player to shoot each turn, then analyzes the result.
     */
    @Override
    public void start() {
        System.out.println(description());
        int[] shot;
        JFrame frame = new JFrame();

        if(this.mode == Mode.HH){
            while(!allSunk(captain) && !allSunk(auto)){
                this.current.displayOppenentGrid();
                this.current.displayMyGrid();
                shot = readShot(this.current);
                JOptionPane.showMessageDialog(frame,this.current.getName()+" : "+analyzeShot(shot));
                this.current.myGridFrame.dispose();
                this.current.opponentGridFrame.dispose();
                changeCurrent();
            }
        }else if(this.mode == Mode.HA){
            while(!allSunk(this.captain) && !allSunk(this.auto)) {
                if(this.current == this.captain) {
                    this.current.displayOppenentGrid();
                    this.current.displayMyGrid();
                    shot = readShot(this.current);
                    JOptionPane.showMessageDialog(frame,this.current.getName()+" : "+analyzeShot(shot));
                    this.current.myGridFrame.dispose();
                    this.current.opponentGridFrame.dispose();
                    changeCurrent();
                }else if(this.current == this.auto){
                    shot = readShot(this.current);
                    JOptionPane.showMessageDialog(frame,this.current.getName()+" : "+analyzeShot(shot));
                    changeCurrent();
                }
            }
        }else if(this.mode == Mode.AA){
            while(!allSunk(captain) && !allSunk(auto)){
                this.current.displayOppenentGrid();
                this.current.displayMyGrid();
                shot = readShot(this.current);
                System.out.println(this.current.getName()+" : "+analyzeShot(shot));
                this.current.myGridFrame.dispose();
                this.current.opponentGridFrame.dispose();
                changeCurrent();
            }
        }

        endOfGame();

    }

    /**
     * This class realizes what needs to be done when the
     * game is over and gives the winner.
     */
    @Override
    public void endOfGame() {
        JFrame frame = new JFrame();
        Player winner = null;
        if(allSunk(this.auto)){
            winner = this.captain;
        }else if(allSunk(this.captain)){
            winner = this.auto;
        }

        winner.displayOppenentGrid();
        winner.displayMyGrid();
        JOptionPane.showMessageDialog(frame,"The winner is "+winner.getName());

    }

    /**
     * Getter of the attribute current.
     * @return The attribute current.
     */
    public Player getCurrent() {
        return this.current;
    }

    /**
     * Getter of the attribute captain.
     * @return The attribute captain.
     */
    public Player getCaptain() {
        return this.captain;
    }

    /**
     * Getter of the attribute auto.
     * @return The attribute auto.
     */
    public Player getAuto() {
        return this.auto;
    }
}
