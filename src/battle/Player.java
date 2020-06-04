package battle;

import view.GridTableFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 * This abstract class includes everything that is common to all types of players.
 * The human player or the automatic player have the same characteristics and
 * inherit of the same attributes and the same methods of Player.
 * @author Kierian Tirlemont
 */
public abstract class Player {

    protected String name;
    protected int width;
    protected int height;

    protected ArrayList<Ship> fleet;
    protected Square[][] myGrid;
    protected Square[][] opponentGrid;

    public GridTableFrame opponentGridFrame;
    public GridTableFrame myGridFrame;

    /**
     * The constructor of the class which initialize the attributes.
     * @param fleet The list of ship.
     * @param name The name of the player.
     * @param width The width of the grid.
     * @param height The height of the grid.
     */
    public Player(ArrayList<Ship> fleet, String name, int width, int height) throws IllegalArgumentException{

        try{

            if(name == null){
                throw new IllegalArgumentException("Player : error : The name is null");
            }
            if(fleet==null){
                throw new IllegalArgumentException("Player : error : The fleet is null");
            }
            if(width<=0){
                throw new IllegalArgumentException("Player : error : The width is inferior to 0");
            }
            if(height<=0){
                throw new IllegalArgumentException("Player : error : The height is inferior to 0");
            }

            this.name = name;
            this.width = width;
            this.height = height;

            this.fleet = new ArrayList<Ship>();
            this.createCopy(fleet);

            this.initializeOpponentGrid();
            this.initializeMyGrid();

        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }

    }

    /**
     * This method creates a copy of the fleet as players
     * will modify the characteristics of the ships.
     * @param fleet The list of boats.
     */
    protected void createCopy(ArrayList<Ship> fleet) throws IllegalArgumentException{

        try {

            if(fleet == null){
                throw new IllegalArgumentException("createCopy : error : fleet is null");
            }

            ArrayList<Ship> newFleet = new ArrayList<Ship>();
            for (Ship ship : fleet){
                newFleet.add(new Ship(ship.getName(), ship.getSize()));
            }
            this.fleet = newFleet;

        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }


    }

    /**
     * Creates the Square array and uses it by creating
     * each square that contains a Square object. And
     * calls the ship placement method.
     */
    protected void initializeMyGrid(){

        this.myGrid = new Square[this.width][this.height];
        for(int y = 0; y< this.width; y++){
            for(int x = 0; x< this.height; x++){
                this.myGrid[y][x]=new Square(y,x);
            }
        }

        this.shipPlacement();

    }

    /**
     * Creates the Square array and uses it by creating
     * each square that contains a Square object.
     */
    protected void initializeOpponentGrid(){

        this.opponentGrid = new Square[this.width][this.height];
        for(int y = 0; y<this.width;y++){
            for(int x = 0 ; x<this.height;x++){
                this.opponentGrid[y][x]=new Square(y,x);
            }
        }

    }

    /**
     * This method allows to print the attribute my grid.
     */
    public void displayMyGrid(){
        this.myGridFrame = new GridTableFrame(this.myGrid);
        this.myGridFrame.showIt("MyGrid");

        this.myGridFrame.setLocation(0,0);
    }

    /**
     * This method allows to print the opponent grid.
     */
    public void displayOppenentGrid(){
        this.opponentGridFrame = new GridTableFrame(this.opponentGrid);
        this.opponentGridFrame.showIt("OpponentGrid");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.opponentGridFrame.setLocation(dim.width-this.opponentGridFrame.getSize().width,50);
    }

    /**
     * This method sees if a ship has been hit and increment the
     * attribute hitNumber from ship and set the boolean
     * hit from square to true. If the ship is sunk return true
     * else false.
     * We need to find the direction of the ship to find the
     * highest or the left most point of the ship to find which
     * it's.
     * @param x The position of the ship according to the x axe.
     * @param y The position of the ship according to the y axe.
     * @return If the ship is sunk return true else false.
     */
    public boolean isSunk(int x, int y){
        boolean ret = false;
        Direction direction = null;
        boolean find = false;
        int xPos = x;
        int yPos = y;
        Ship theShip = null;
        //Checks the value in parameter
        try {
            if(xPos<0 || xPos>=this.width || yPos<0 || yPos>=this.height){
                throw new IllegalArgumentException("isSunk : The parameter are wrong");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        //Checks is the square is free
        //if it's so I search the ship
        //Else nothing return false
        if(!this.myGrid[xPos][yPos].isFree()){
            //Search the direction of the ship
            if(yPos==0 && xPos==0){
                if(!this.myGrid[(xPos+1)][yPos].isFree()){
                    direction = Direction.HORIZONTAL;
                }else if(!this.myGrid[xPos][(yPos+1)].isFree()){
                    direction = Direction.VERTICAL;
                }
            }else if(xPos==(this.width -1) && yPos==(this.height -1)){
                if(!this.myGrid[(xPos-1)][yPos].isFree()){
                    direction = Direction.HORIZONTAL;
                }else if(!this.myGrid[xPos][(yPos-1)].isFree()){
                    direction = Direction.VERTICAL;
                }
            }else if(yPos==0 && xPos==(this.width -1)){
                if(!this.myGrid[(xPos-1)][yPos].isFree()){
                    direction = Direction.HORIZONTAL;
                }else if(!this.myGrid[xPos][(yPos+1)].isFree()){
                    direction = Direction.VERTICAL;
                }
            }else if(yPos==(this.height -1) && xPos==0){
                if(!this.myGrid[(xPos+1)][yPos].isFree()){
                    direction = Direction.HORIZONTAL;
                }else if(!this.myGrid[xPos][(yPos-1)].isFree()){
                    direction = Direction.VERTICAL;
                }
            }else if(yPos==0){
                if(!this.myGrid[(xPos+1)][yPos].isFree() || !this.myGrid[(xPos-1)][yPos].isFree()){
                    direction = Direction.HORIZONTAL;
                }else if(!this.myGrid[xPos][(yPos+1)].isFree()){
                    direction = Direction.VERTICAL;
                }
            }else if(yPos==(this.height -1)){
                if(!this.myGrid[(xPos+1)][yPos].isFree() || !this.myGrid[(xPos-1)][yPos].isFree()){
                    direction = Direction.HORIZONTAL;
                }else if(!this.myGrid[xPos][(yPos-1)].isFree()){
                    direction = Direction.VERTICAL;
                }
            }else if(xPos==0){
                if(!this.myGrid[(xPos+1)][yPos].isFree()){
                    direction = Direction.HORIZONTAL;
                }else if(!this.myGrid[xPos][(yPos-1)].isFree() || !this.myGrid[xPos][(yPos+1)].isFree()){
                    direction = Direction.VERTICAL;
                }
            }else if(xPos==(this.width -1)){
                if(!this.myGrid[(xPos-1)][yPos].isFree()){
                    direction = Direction.HORIZONTAL;
                }else if(!this.myGrid[xPos][(yPos-1)].isFree() || !this.myGrid[xPos][(yPos+1)].isFree()){
                    direction = Direction.VERTICAL;
                }
            }else{
                if(this.myGrid[(xPos+1)][yPos].isFree() || this.myGrid[(xPos-1)][yPos].isFree()){
                    direction = Direction.HORIZONTAL;
                }else if(this.myGrid[xPos][(yPos+1)].isFree() || this.myGrid[xPos][(yPos-1)].isFree()){
                    direction = Direction.VERTICAL;
                }
            }

            //search the max point of the ship in function of the direction
            if(direction.equals(Direction.VERTICAL)){
                while(!find){
                    if(yPos<=0 || yPos>= this.height){
                        find = true;
                        yPos++;
                    }

                    if(this.myGrid[xPos][(yPos-1)].isFree()){
                        find = true;
                        yPos++;
                    }
                    yPos--;
                }
            }else if(direction.equals(Direction.HORIZONTAL)){
                while(!find){
                    if(this.myGrid[(xPos-1)][yPos].isFree()){
                        find = true;
                        xPos++;
                    }

                    xPos--;

                    if(xPos<0 || yPos>= this.width){
                        find = true;
                        xPos++;
                    }
                }
            }

            find = false;

            //Find the ship's object with the position
            int i = 0;
            while(!find && i<this.fleet.size()){
                if(this.fleet.get(i).getxOrigin() == xPos && this.fleet.get(i).getyOrigin() == yPos){
                    theShip = this.fleet.get(i);
                    find = true;
                }
                i++;
            }

            //Checks if the ship has been found
            if(theShip != null){
                theShip.addHit();
                ret = theShip.isSunk();
            }
        }
        return ret;
    }

    /**
     * This method allows to know if every ships
     * of the current are sunk.
     * @return true : if every ships are sunk.
     *         false : if the player has still
     *         ships.
     */
    public boolean allSunk(){
        boolean ret = true;

        for(Ship s : this.fleet){
            if(!s.isSunk()){
                ret = false;
            }
        }

        return ret;
    }

    /**
     * Requests 2 shooting coordinates to the player
     * and returns them in an array of 2 integers.
     * @return an of two integers with the shooting
     * coordinates.
     */
    public abstract int[] newShot();


    /**
     * Realises the arrangement of the departure ships.
     */
    public abstract void shipPlacement();

    /**
     * Getter of the attribute myGrid.
     * @return the attribute myGrid.
     */
    public Square[][] getMyGrid() {
        return this.myGrid;
    }

    /**
     * Getter of the attribute opponentGrid.
     * @return The attribute opponentGrid
     */
    public Square[][] getOpponentGrid() {
        return this.opponentGrid;
    }

    /**
     * This method returns the values of the attributes in a String.
     * @return The values of the attributes in a String.
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + this.name + '\'' +
                ", width=" + this.width +
                ", height=" + this.height +
                ", fleet=" + this.fleet +
                '}';
    }

    /**
     * Getter of the attribute fleet.
     * @return The fleet of ship.
     */
    public ArrayList<Ship> getFleet() {
        return this.fleet;
    }

    /**
     * Getter of the attribute name.
     * @return The name of the player.
     */
    public String getName() {
        return this.name;
    }
}
