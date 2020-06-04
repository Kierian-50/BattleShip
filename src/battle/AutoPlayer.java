package battle;

import java.util.ArrayList;

/**
 * This class represents an automatic player.
 * Auto player is a subclass of Player and must
 * redefine abstract methods.
 * @author Kierian Tirlemont
 */
public class AutoPlayer extends Player {

    /**
     * The constructor of the class which initialize the attributes.
     * @param fleet  The list of ship.
     * @param name   The name of the player.
     * @param width  The width of the grid.
     * @param height The height of the grid.
     */
    public AutoPlayer(ArrayList<Ship> fleet, String name, int width, int height) throws IllegalArgumentException{
        super(fleet, name, width, height);
        try{
            if(fleet == null){
                throw new IllegalArgumentException("AutoPlayer : erreur : fleet is null");
            }
            if(name == null){
                throw new IllegalArgumentException("AutoPlayer : erreur : name is null");
            }
            if(width<=0){
                throw new IllegalArgumentException("AutoPlayer : error : The width is inferior to 0");
            }
            if(height<=0){
                throw new IllegalArgumentException("AutoPlayer : error : The height is inferior to 0");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }

    }

    /**
     * Requests two shooting coordinates to the player
     * and returns them in an array of two integers.
     * shot[0] = x
     * shot[1] = y
     * @return an of two integers with the shooting
     * coordinates.
     */
    @Override
    public int[] newShot() {
        int[] ret = new int[2];
        boolean sunkThisShip = false;
        int x=-1;
        int y=-1;

        //------------------------------------
        //Try to shot around a shot position
        //This doesn't work, please refer to the readMe
        //------------------------------------
        //Init the array
        boolean[][] alreadySunk = new boolean[super.width][super.height];
        for(boolean[] bool : alreadySunk){
            for (boolean b : bool){
                b = false;
            }
        }

        //Put the sunk ship in the array
        for(Ship s : super.fleet){
            if(s.isSunk()){
                for(int i = 0; i < s.getSize();i++){
                    if(s.getDirection() == Direction.HORIZONTAL){
                        alreadySunk[s.getxOrigin()+i][s.getyOrigin()]=true;
                    }else if(s.getDirection() == Direction.VERTICAL){
                        alreadySunk[s.getxOrigin()][s.getyOrigin()+i]=true;
                    }
                }
            }
        }

        //Find a ship which is not sunk but some part are hit
        //If it exists sunkThisShip = true
        for(Square[] sLine : super.opponentGrid){
            for (Square s : sLine){
                if(s.isHit() && !s.isFree() && !alreadySunk[s.getX()][s.getY()]){
                    sunkThisShip = true;
                    x = s.getX();
                    y = s.getY();
                }
            }
        }

        sunkThisShip = false;

        if (sunkThisShip) {
            //We will look around this point and answer to the question can we find the direction of the ship
            if(x==0 && y==0){
                if(!super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x+i][y].isHit() && !super.opponentGrid[x+i][y].isFree()){
                        i++;
                    }
                    x+=i;
                }else if(!super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                    int i = 0;
                    while (super.opponentGrid[x][y+i].isHit() && !super.opponentGrid[x][y+i].isFree()){
                        i++;
                    }
                    y+=i;
                }else{
                    if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                        x=1;
                        y=0;
                    }else if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                        x=0;
                        y=1;
                    }else{
                        x = 1;
                        y = 0;
                    }
                }

            }else if(x==0 && y==super.height){
                if(!super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x+i][y].isHit() && !super.opponentGrid[x+i][y].isFree()){
                        i++;
                    }
                    x+=i;
                }else if(!super.opponentGrid[x][y-1].isFree() && !super.opponentGrid[x][y-1].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x][y-i].isHit() && !super.opponentGrid[x][y-i].isFree()){
                        i++;
                    }
                    y+=i;
                }else{
                    if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                        x=1;
                        y=super.height-1;
                    }else if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                        x=0;
                        y=super.height-2;
                    }else{
                        x=1;
                        y=super.height-1;
                    }
                }

            }else if(x==super.width-1 && y==super.height-1){
                if(!super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x-i][y].isHit() && !super.opponentGrid[x-i][y].isFree()){
                        i++;
                    }
                    x-=i;
                }else if(!super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x][y-i].isHit() && !super.opponentGrid[x][y-i].isFree()){
                        i++;
                    }
                    y-=i;
                }else{
                    if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                        x-=1;
                    }else if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                        y-=1;
                    }else{
                        x-=1;
                    }
                }

            }else if(x==super.width-1 && y==0){
                if(!super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x-i][y].isHit() && !super.opponentGrid[x-i][y].isFree()){
                        i++;
                    }
                    x-=i;
                }else if(!super.opponentGrid[x][y+1].isFree() && !super.opponentGrid[x][y+1].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x][y+i].isHit() && !super.opponentGrid[x][y+i].isFree()){
                        i++;
                    }
                    y+=i;
                }else{
                    if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                        x-=1;
                    }else if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                        y+=1;
                    }else{
                        x-=1;
                    }
                }

            }else if(x==0){
                if (!super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x+i][y].isHit() && !super.opponentGrid[x+i][y].isFree()){
                        i++;
                    }
                    x+=i;
                }else if(!super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x][y-i].isHit() && !super.opponentGrid[x][y-i].isFree()){
                        i++;
                    }
                    y-=i;
                }else if(!super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x][y+i].isHit() && !super.opponentGrid[x][y+i].isFree()){
                        i++;
                    }
                    y+=i;
                }else{
                    if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                        if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                            x++;
                        }else if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                            y--;
                        }else{
                            y--;
                        }
                    }else if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                        if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                            y++;
                        }else if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                            y--;
                        }else{
                            y--;
                        }
                    }else if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                        if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                            y++;
                        }else if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                            x++;
                        }else{
                            y++;
                        }
                    }else{
                        y--;
                    }
                }

            }else if(y==0){
                if (!super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x+i][y].isHit() && !super.opponentGrid[x+i][y].isFree()){
                        i++;
                    }
                    x+=i;
                }else if(!super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x-i][y].isHit() && !super.opponentGrid[x-i][y].isFree()){
                        i++;
                    }
                    x-=i;
                }else if(!super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x][y+i].isHit() && !super.opponentGrid[x][y+i].isFree()){
                        i++;
                    }
                    y+=i;
                }else{
                    if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                        if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                            y++;
                        }else if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                            x++;
                        }else{
                            y++;
                        }
                    }else if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                        if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                            x--;
                        }else if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                            x++;
                        }else{
                            x++;
                        }
                    }else if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                        if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                            y++;
                        }else if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                            x--;
                        }else{
                            y++;
                        }
                    }else{
                        x--;
                    }
                }

            }else if(x==super.width-1){
                if (!super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x-i][y].isHit() && !super.opponentGrid[x-i][y].isFree()){
                        i++;
                    }
                    x-=i;
                }else if(!super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x][y-i].isHit() && !super.opponentGrid[x][y-i].isFree()){
                        i++;
                    }
                    y-=i;
                }else if(!super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x][y+i].isHit() && !super.opponentGrid[x][y+i].isFree()){
                        i++;
                    }
                    y+=i;
                }else{
                    if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                        if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                            x--;
                        }else if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                            y--;
                        }else{
                            y--;
                        }
                    }else if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                        if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                            y++;
                        }else if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                            y--;
                        }else{
                            y--;
                        }
                    }else if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                        if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                            y++;
                        }else if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                            x--;
                        }else{
                            y++;
                        }
                    }else{
                        y--;
                    }
                }

            }else if(y==super.height-1){
                if (!super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x+i][y].isHit() && !super.opponentGrid[x+i][y].isFree()){
                        i++;
                    }
                    x+=i;
                }else if(!super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x-i][y].isHit() && !super.opponentGrid[x-i][y].isFree()){
                        i++;
                    }
                    x-=i;
                }else if(!super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x][y-i].isHit() && !super.opponentGrid[x][y-i].isFree()){
                        i++;
                    }
                    y-=i;
                }else{
                    if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                        if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                            y--;
                        }else if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                            x++;
                        }else{
                            x++;
                        }
                    }else if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                        if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                            x--;
                        }else if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                            x++;
                        }else{
                            x++;
                        }
                    }else if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                        if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                            y--;
                        }else if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                            x--;
                        }else{
                            y--;
                        }
                    }else{
                        x--;
                    }
                }

            }else{
                if(!super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x+i][y].isHit() && !super.opponentGrid[x+i][y].isFree()){
                        i++;
                    }
                    x+=i;
                }else if(!super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x-i][y].isHit() && !super.opponentGrid[x-i][y].isFree()){
                        i++;
                    }
                    x-=i;
                }else if(!super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                    int i = 0;
                    while(super.opponentGrid[x][y+i].isHit() && !super.opponentGrid[x][y+i].isFree()){
                        i++;
                    }
                    y+=i;
                }else if(!super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()) {
                    int i = 0;
                    while (super.opponentGrid[x][y - i].isHit() && !super.opponentGrid[x][y - i].isFree()) {
                        i++;
                    }
                    y -= i;
                }else{
                    //If we have already hit x+1 position with no ship
                    if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                        //and we have already hit x-1 position with no ship
                        if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                            if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                                y-=1;
                            }else if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                                y+=1;
                            }else{
                                y-=1;
                            }
                            //and we have already hit y+1 position with no ship
                        }else if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isFree()){
                            if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                                y-=1;
                            }else if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                                x-=1;
                            }else{
                                y-=1;
                            }
                            //and we have already hit y-1 position with no ship
                        }else if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                            if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                                x-=1;
                            }else if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                                y+=1;
                            }else{
                                y+=1;
                            }
                        }else{
                            x-=1;
                        }
                    }else if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                        if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                            if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                                y-=1;
                            }else if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                                y+=1;
                            }else{
                                y-=1;
                            }
                        }else if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                            if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                                y-=1;
                            }else if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                                x+=1;
                            }else{
                                y-=1;
                            }
                        }else if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()) {
                            if (super.opponentGrid[x + 1][y].isFree() && super.opponentGrid[x + 1][y].isHit()) {
                                y += 1;
                            } else if (super.opponentGrid[x][y + 1].isFree() && super.opponentGrid[x][y + 1].isHit()) {
                                x += 1;
                            } else {
                                y += 1;
                            }
                        }else{
                            x += 1;
                        }
                    }else if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()){
                        if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                            if (super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()) {
                                y -= 1;
                            } else if (super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()) {
                                x -= 1;
                            } else {
                                y -= 1;
                            }
                        }else if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()){
                            if (super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()) {
                                y -= 1;
                            } else if (super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()) {
                                x += 1;
                            } else {
                                y -= 1;
                            }
                        }else if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                            if (super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()) {
                                x -= 1;
                            } else if (super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()) {
                                x += 1;
                            } else {
                                x -= 1;
                            }
                        }else{
                            y -= 1;
                        }
                    }else if(super.opponentGrid[x][y-1].isFree() && super.opponentGrid[x][y-1].isHit()){
                        if(super.opponentGrid[x+1][y].isFree() && super.opponentGrid[x+1][y].isHit()){
                            if (super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()) {
                                y += 1;
                            } else if (super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()) {
                                x -= 1;
                            } else {
                                y += 1;
                            }
                        }else if(super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()) {
                            if (super.opponentGrid[x + 1][y].isFree() && super.opponentGrid[x + 1][y].isHit()) {
                                x -= 1;
                            } else if (super.opponentGrid[x - 1][y].isFree() && super.opponentGrid[x - 1][y].isHit()) {
                                x += 1;
                            } else {
                                x -= 1;
                            }
                        }else if(super.opponentGrid[x-1][y].isFree() && super.opponentGrid[x-1][y].isHit()) {
                            if (super.opponentGrid[x + 1][y].isFree() && super.opponentGrid[x + 1][y].isHit()) {
                                y += 1;
                            } else if (super.opponentGrid[x][y+1].isFree() && super.opponentGrid[x][y+1].isHit()) {
                                x += 1;
                            } else {
                                x += 1;
                            }
                        }else{
                            x+=1;
                        }
                    }
                }
            }

        }else{
            x = (int) ((Math.random() * (super.width)));
            y = (int) ((Math.random() * (super.height)));
            //prevent to have the same shot two times
            while(super.opponentGrid[x][y].isHit()){
                x = (int) ((Math.random() * (super.width)));
                y = (int) ((Math.random() * (super.height)));
            }
        }

        ret[0] = x;
        ret[1] = y;

        return ret;
    }

    /**
     * Realises the arrangement of the departure ships.
     * It chooses randomly the place of the ship and
     * randomly his direction. This method works in
     * every cases if the every ships (including the empty
     * square nest to it) take 80% of the grid. After this,
     * the method don't work in every cases, and mostly time
     * it finish with the infinite loop because it can't find
     * a place for every ship. This represents approximately
     * ten ships with a size of three in a ten by ten grid.
     * To calculate it, the required square for a ship is :
     * number of ship * ( 2 * the size of the ship + 2).
     * And after it's easy to deduce a percentage.
     */
    @Override
    public void shipPlacement() {

        /*
        First if all, we check that the sum of the maximum required square
        is not superior to 80% of the grid. Else return an error.
         */
        int sommeCaseShip = 0;
        int sommeCaseGrid = (super.height * super.width);

        for(Ship s : super.fleet){
            sommeCaseShip += (2*s.getSize()+2);
        }

        if((sommeCaseShip*100)/sommeCaseGrid > 80){
            throw new IllegalArgumentException("shipPlacement : The grid is too small,\nPlease check the rule of the" +
                    " game or the read me file (error : number of ship square is too height)");
        }

        /*
         * After testing, we can find the placement of each ship.
         */
        Direction shipDirection = null;
        int nbDir,x,y;
        //This array will say if the ship has a place or not.
        boolean[][] isPossible = new boolean[super.width][super.height];

        //Initialize
        for(int z = 0; z<super.height;z++){
            for(int c = 0 ; c<super.width;c++){
                isPossible[z][c] = true;
            }
        }

        boolean trouveXY;
        boolean trouvePlacement = false;
        int i = 0;
        int max;

        for(Ship s : super.fleet){

            trouveXY = false;
            trouvePlacement = false;

            while (!trouveXY) {

                x = (int) ((Math.random() * (super.width)));
                y = (int) ((Math.random() * (super.height)));

                /*
                If the chosen  position is forbidden, it's useless to
                go further : we need to do the loop one more time.
                 */
                if (isPossible[x][y]){
                    trouveXY = true;
                }

                //Chooses the direction of the ship.
                nbDir = 1 + (int) (Math.random() * ((2 - 1) + 1));
                if (nbDir == 1) {
                    shipDirection = Direction.VERTICAL;
                } else if (nbDir == 2) {
                    shipDirection = Direction.HORIZONTAL;
                }

                if (shipDirection == Direction.HORIZONTAL) {
                    //Checks if the size of the ship can enter from the x origin point.
                    if ((x + s.getSize()-1) >= super.width) {
                        trouveXY = false;
                        if (s.getSize() >= super.width) {
                            if (s.getSize() >= super.height) {
                                throw new IllegalArgumentException("shipPlacement : One of the ship can't enter " +
                                        "in the grid : The grid is too small.");
                            }
                        }
                    }
                    /*
                    Checks if the ship can enter in this direction with the position.
                     */
                    if (trouveXY) {
                        //checks in the case x=0 and y=0
                        if (x == 0 && y == 0) {
                            while (i < s.getSize()) {
                                if (!super.myGrid[x + i][y].isFree() || !super.myGrid[x + i][y + 1].isFree()) {
                                    trouveXY = false;
                                }
                                i++;
                            }
                            if (!super.myGrid[s.getSize()][y].isFree()) {
                                trouveXY = false;
                            }

                            if (trouveXY) {
                                trouvePlacement = true;
                            }
                            i = 0;
                        } else if (y == 0) {
                            //checks in the case y=0
                            while (i < s.getSize()) {
                                if (!super.myGrid[x + i][y].isFree() || !super.myGrid[x + i][y + 1].isFree()) {
                                    trouveXY = false;
                                }
                                i++;
                            }

                            //The maximum square according to the x axe
                            //Prevent to go out the grid.
                            max = x+s.getSize()-1;

                            if(max!=super.width-1){
                                if (!super.myGrid[x+s.getSize()][y].isFree()) {
                                    trouveXY = false;
                                }
                            }

                            if (!super.myGrid[x - 1][y].isFree()) {
                                trouveXY = false;
                            }

                            if (trouveXY) {
                                trouvePlacement = true;
                            }
                            i = 0;
                        } else if (y == super.height - 1 && x == 0) {
                            //check in the case y equal the height of the grid and x=0.
                            while (i < s.getSize()) {
                                if (!super.myGrid[x + i][y].isFree() || !super.myGrid[x + i][y - 1].isFree()) {
                                    trouveXY = false;
                                }
                                i++;
                            }
                            if (!super.myGrid[x + s.getSize()][y].isFree()) {
                                trouveXY = false;
                            }

                            if (trouveXY) {
                                trouvePlacement = true;
                            }
                            i = 0;
                        } else if(x == 0){
                            //checks in the case x=0
                            while (i < s.getSize()) {
                                if (!super.myGrid[x + i][y].isFree() || !super.myGrid[x + i][y + 1].isFree()
                                    || !super.myGrid[x+i][y-1].isFree()) {
                                    trouveXY = false;
                                }
                                i++;
                            }

                            if (!super.myGrid[x+s.getSize()][y].isFree()) {
                                trouveXY = false;
                            }

                            if (trouveXY) {
                                trouvePlacement = true;
                            }
                            i = 0;
                        } else if (y == super.height - 1) {
                            //Checks in the case y equals to the height of the grid
                            while (i < s.getSize()) {
                                if (!super.myGrid[x + i][y].isFree() || !super.myGrid[x + i][y - 1].isFree()) {
                                    trouveXY = false;
                                }
                                i++;
                            }

                            //The maximum square according to the x axe
                            //Prevent to go out the grid.
                            max = x+s.getSize()-1;

                            if(max!=super.width-1){
                                if (!super.myGrid[x+s.getSize()][y].isFree()) {
                                    trouveXY = false;
                                }
                            }

                            if (!super.myGrid[x - 1][y].isFree()) {
                                trouveXY = false;
                            }

                            if (trouveXY) {
                                trouvePlacement = true;
                            }
                            i = 0;
                        } else {
                            //In the others case
                            while (i < s.getSize()) {
                                if (!super.myGrid[x + i][y].isFree() || !super.myGrid[x + i][y - 1].isFree() ||
                                        !super.myGrid[x + i][y + 1].isFree()) {
                                    trouveXY = false;
                                }
                                i++;
                            }

                            //The maximum square according to the x axe
                            //Prevent to go out the grid.
                            max = x+s.getSize()-1;

                            if(max!=super.width-1){
                                if (!super.myGrid[x+s.getSize()][y].isFree()) {
                                    trouveXY = false;
                                }
                            }

                            if (!super.myGrid[x - 1][y].isFree()) {
                                trouveXY = false;
                            }

                            if (trouveXY) {
                                trouvePlacement = true;
                            }
                            i = 0;
                        }
                    }

                } else if (shipDirection == Direction.VERTICAL) {
                    //Checks that the dimension of the ship can enter.
                    if ((y + s.getSize()-1) >= super.height) {
                        trouveXY = false;
                        if (s.getSize() >= super.height) {
                            if (s.getSize() >= super.width) {
                                throw new IllegalArgumentException("shipPlacement : One of the ship can't enter " +
                                        "in the grid : The grid is too small.");
                            }
                        }
                    }
                    if (trouveXY) {
                        //check in the case x=0 and y=0
                        if (x == 0 && y == 0) {
                            while (i < s.getSize()) {
                                if (!super.myGrid[x][y + i].isFree() || !super.myGrid[x + 1][y+i].isFree()) {
                                    trouveXY = false;
                                }
                                i++;
                            }
                            if (!super.myGrid[x][y + s.getSize()].isFree()) {
                                trouveXY = false;
                            }

                            if (trouveXY) {
                                trouvePlacement = true;
                            }
                            i = 0;
                        } else if (x == super.width - 1 && y == 0) {
                            //check in the case y equal the height of the grid.
                            while (i < s.getSize()) {
                                if (!super.myGrid[x][y + i].isFree() || !super.myGrid[x - 1][y + i].isFree()) {
                                    trouveXY = false;
                                }
                                i++;
                            }

                            if (!super.myGrid[x][y + s.getSize()].isFree()) {
                                trouveXY = false;
                            }

                            if (trouveXY) {
                                trouvePlacement = true;
                            }
                            i = 0;
                        } else if (x == width - 1) {
                            //Checks in the case x = to the width of the grid
                            while (i < s.getSize()) {
                                if (!super.myGrid[x][y + i].isFree() || !super.myGrid[x - 1][y + i].isFree()) {
                                    trouveXY = false;
                                }
                                i++;
                            }

                            //The maximum square according to the y axe
                            //Prevent to go out the grid.
                            max = y+s.getSize()-1;

                            if(max!=super.height-1){
                                if (!super.myGrid[x][y + s.getSize()].isFree()) {
                                    trouveXY = false;
                                }
                            }

                            if(!super.myGrid[x][y - 1].isFree()){
                                trouveXY = false;
                            }

                            if (trouveXY) {
                                trouvePlacement = true;
                            }
                            i = 0;
                        }  else if (y == 0) {
                            //checks in the case y=0
                            while (i < s.getSize()) {
                                if (!super.myGrid[x][y + i].isFree() || !super.myGrid[x + 1][y + i].isFree() ||
                                        !super.myGrid[x-1][y+i].isFree()) {
                                    trouveXY = false;
                                }
                                i++;
                            }
                            if (!super.myGrid[x][y + s.getSize()].isFree()) {
                                trouveXY = false;
                            }

                            if (trouveXY) {
                                trouvePlacement = true;
                            }
                            i = 0;
                        } else if(x==0){
                            //checks in the case x=0
                            while (i < s.getSize()) {
                                if (!super.myGrid[x][y + i].isFree() || !super.myGrid[x + 1][y + i].isFree()) {
                                    trouveXY = false;
                                }
                                i++;
                            }

                            //The maximum square according to the y axe
                            //Prevent to go out the grid.
                            max = y + s.getSize()-1;

                            if(max!=super.height-1){
                                if (!super.myGrid[x][y + s.getSize()].isFree()) {
                                    trouveXY = false;
                                }
                            }

                            if(!super.myGrid[x][y - 1].isFree()){
                                trouveXY = false;
                            }

                            if (trouveXY) {
                                trouvePlacement = true;
                            }
                            i = 0;
                        }
                        else {
                            //Checks in the others cases
                            while (i < s.getSize()) {
                                if (!super.myGrid[x][y + i].isFree() || !super.myGrid[x - 1][y + i].isFree() ||
                                        !super.myGrid[x + 1][y + i].isFree()) {
                                    trouveXY = false;
                                }
                                i++;
                            }

                            //The maximum square according to the y axe
                            //Prevent to go out the grid.
                            max = y+s.getSize()-1;

                            if(max!=super.height-1){
                                if (!super.myGrid[x][y + s.getSize()].isFree()) {
                                    trouveXY = false;
                                }
                            }

                            if(!super.myGrid[x][y - 1].isFree()){
                                trouveXY = false;
                            }

                            if (trouveXY) {
                                trouvePlacement = true;
                            }
                            i = 0;
                        }
                    }

                }

                /*
                If trouvePlacmement equals to true, the position is found.
                So, here we set the position of the ship and set the value
                in the grid.
                 */
                if (trouvePlacement){
                    s.setyOrigin(y);
                    s.setxOrigin(x);
                    s.setDirection(shipDirection);
                    if(shipDirection == Direction.VERTICAL){
                        while(i<s.getSize()){
                            super.myGrid[x][y+i].setBusy();
                            isPossible[x][y+i] = false;
                            if(x+1 < super.width-1){
                                isPossible[x+1][y+i] = false;
                            }
                            if(x-1 >= 0){
                                isPossible[x-1][y+i] = false;
                            }
                            i++;
                        }
                        i=0;

                        if(y+s.getSize() <= super.height-1){
                            isPossible[x][y+s.getSize()] = false;
                        }

                        if (y-1 >= 0) {
                            isPossible[x][y-1] = false;
                        }
                    }else if(shipDirection == Direction.HORIZONTAL){
                        while(i < s.getSize()){
                            super.myGrid[x+i][y].setBusy();
                            isPossible[x+i][y] = false;
                            if (y + 1 < super.height - 1) {
                                isPossible[x+i][y+1]=false;
                            }
                            if (y - 1 >= 0) {
                                isPossible[x+i][y-1] = false;
                            }
                            i++;
                        }
                        i=0;

                        if (x+s.getSize() <= super.width-1){
                            isPossible[x+s.getSize()][y]=false;
                        }
                        if (x-1>=0){
                            isPossible[x-1][y]=false;
                        }
                    }
                }
            }
        }
    }
}
