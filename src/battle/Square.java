package battle;

/**
 * The grid of the game are array of Square's type.
 * @author Kierian Tirlemont
 */
public class Square {

    private int x;
    private int y;
    /**
     * The content of the square is empty (true) or occupied (false)
     */
    private boolean free;
    /**
     * The boolean if the square is touched.
     */
    private boolean hit;

    /**
     * The constructor of the class which initialize the attributes.
     * @param x The number of line.
     * @param y The number of column.
     */
    public Square(int x, int y) throws IllegalArgumentException{

        try {

            if(x<0){
                throw new IllegalArgumentException("Square : x is inferior to 0");
            }
            if(y<0){
                throw new IllegalArgumentException("Square : y is inferior to 0");
            }

            this.free = true;
            this.hit = false;
            this.x = x;
            this.y = y;

        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }
    }

    /**
     * This method changes the state of the attribute free.
     * The new state is false, because the square is busy.
     */
    public void setBusy(){
        this.setFree(false);
    }

    /**
     * Getter of the attribute x.
     * @return The number of line.
     */
    public int getX() {
        return x;
    }

    /**
     * Setter of the attribute x.
     * @param x The number of line.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter of the attribute y.
     * @return The number of column.
     */
    public int getY() {
        return y;
    }

    /**
     * Setter of the attribute y.
     * @param y The number of column.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter of the attribute free.
     * @return The content of the box.
     */
    public boolean isFree() {
        return free;
    }

    /**
     * Setter of the attribute free.
     * @param free The content of the box.
     */
    public void setFree(boolean free) {
        this.free = free;
    }

    /**
     * Getter of the attribute hit.
     * @return The boolean if the square is touched.
     */
    public boolean isHit() {
        return hit;
    }

    /**
     * Setter of the attribute hit.
     * @param hit The boolean if the square is touched.
     */
    public void setHit(boolean hit) {
        this.hit = hit;
    }

    /**
     * Return the String with the value of the attribute.
     * @return The String with the value of the attribute.
     */
    @Override
    public String toString() {
        return "Square{" +
                "x=" + x +
                ", y=" + y +
                ", free=" + free +
                ", hit=" + hit +
                '}';
    }
}
