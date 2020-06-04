package battle;

/**
 * This class represents a ship and his position on the grid.
 * @author Kierian Tirlemont
 */
public class Ship {

    private Direction direction;
    private String name;
    private int size;
    private int xOrigin;
    private int yOrigin;
    private int hitNumber;

    /**
     * The constructor of the class which initialises the attributes.
     * @param name The name of the ship.
     * @param size The size of the ship.
     */
    public Ship(String name, int size) throws IllegalArgumentException{

        try {
            if(name == null){
                throw new IllegalArgumentException("Ship : the name is null");
            }
            if(size<=0){
                throw new IllegalArgumentException("Ship : size is inferior to 0");
            }

            this.name = name;
            this.size = size;
            this.xOrigin = 0;
            this.yOrigin = 0;
            this.hitNumber = 0;
            this.direction = direction.VERTICAL;

        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }


    }

    /**
     * This method allows to add a touch to the ship.
     */
    public void addHit(){
        this.hitNumber++;
    }

    /**
     * This method compares the number of hits to the size
     * and if the size is equal to the number of hits
     * the boat is sunk.
     * @return If the boat is sunk : True
     * If the boat is not sunk : False.
     */
    public boolean isSunk(){
        boolean ret = false;
        if(hitNumber>=size){
            ret = true;
        }
        return ret;
    }

    /**
     * This method compares the xPosition and the
     * yPosition to the position (x,y) past in
     * parameter.
     * @param x The position depending the x axis.
     * @param y The position depending the y axis.
     * @return If the x (past in parameter) is equal
     * to the xOrigin and the y (past in parameter) is
     * equal to the yOrigine : True
     * Else : False
     */
    public boolean contains(int x, int y){
        boolean ret = false;

        if( x >= 0 && y >= 0){

            if(this.direction == Direction.HORIZONTAL){
                if(x <= (this.xOrigin + this.size -1)  && x >= this.xOrigin && y == this.yOrigin){
                    ret = true;
                }
            } else if(this.direction == Direction.VERTICAL){
                if(y <= (this.yOrigin + this.size -1) && y >= this.yOrigin && x == this.xOrigin){
                    ret = true;
                }
            }
        }
        else {
            System.out.println("Contains : error : the parameters must not be null and greater than 0.");
        }
        return ret;
    }

    /**
     * This class returns every attributes in a String.
     * @return Every Attributes.
     */
    public String toString() {
        return "Ship{" +
                "direction=" + this.direction +
                ", name='" + this.name + '\'' +
                ", size=" + this.size +
                ", xOrigin=" + this.xOrigin +
                ", yOrigin=" + this.yOrigin +
                ", hitNumber=" + this.hitNumber +
                '}';
    }

    /**
     * Return the attribute name.
     * @return The name of the boat.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the attribute size.
     * @return The size of the boat.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Setter of the attribute direction.
     * @param direction The direction of the ship.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Setter of the attribute xOrigin.
     * @param xOrigin The position of the ship according
     *                to the x axe.
     */
    public void setxOrigin(int xOrigin) {
        this.xOrigin = xOrigin;
    }

    /**
     * Setter of the attribute yOrigin.
     * @param yOrigin The position of the ship according
     *                to the y axe.
     */
    public void setyOrigin(int yOrigin) {
        this.yOrigin = yOrigin;
    }

    /**
     * Getter of the attribute direction.
     * @return The direction of the ship.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Getter of the attribute xOrigin.
     * @return The position of the ship according
     *         to the x axe.
     */
    public int getxOrigin() {
        return xOrigin;
    }

    /**
     * Getter of the attribute yOrigin.
     * @return The position of the ship according
     *         to the y axe.
     */
    public int getyOrigin() {
        return yOrigin;
    }

    /**
     * Getter of the attribute hitNumber.
     * @return The number of hit on the ship.
     */
    public int getHitNumber() {
        return hitNumber;
    }
}
