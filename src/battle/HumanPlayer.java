package battle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Subclass of Player and must implement the methods to
 * place its ships at the start "shipPlacement" and propose
 * a "newShot" shot.
 * @author Kierian Tirlemont
 */
public class HumanPlayer extends Player {

    JFrame frame;
    private final String DELIMITER = "\\s*:\\s*";

    /**
     * The constructor of the class which initialize the attributes.
     * @param fleet  The list of ship.
     * @param name   The name of the player.
     * @param width  The width of the grid.
     * @param height The height of the grid.
     */
    public HumanPlayer(ArrayList<Ship> fleet, String name, int width, int height) throws IllegalArgumentException{

        super(fleet, name, width, height);

        try{

            if(fleet == null){
                throw new IllegalArgumentException("HumanPlayer : erreur : fleet is null");
            }
            if(name == null){
                throw new IllegalArgumentException("HumanPlayer : erreur : name is null");
            }
            if(width<=0){
                throw new IllegalArgumentException("HumanPlayer : error : The width is inferior to 0");
            }
            if(height<=0){
                throw new IllegalArgumentException("HumanPlayer : error : The height is inferior to 0");
            }

            this.frame = new JFrame();

        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }
    }

    /**
     * Requests 2 shooting coordinates to the player
     * and returns them in an array of 2 integers.
     * shot[0] = x
     * shot[1] = y
     * @return An of two integers with the shooting
     * coordinates.
     */
    @Override
    public int[] newShot() {
        int[] ret = new int[2];

        ret[0] = -1;
        ret[1] = -1;

        String positionX;
        String positionY;

        while(ret[0]<0 || ret[0]>= this.width){
            positionX = JOptionPane.showInputDialog(this.frame,"Enter the x position");
            if(positionX == null){
                System.exit(0);
            }
            try {

                ret[0] = Integer.parseInt(positionX);
                if(ret[0]<0 || ret[0]>= this.width){
                    throw new NumberFormatException("newShot : The value "+positionX+" entered is not correct");
                }

            }catch(NumberFormatException e){
                e.printStackTrace();
            }
        }

        while(ret[1]<0 || ret[1]>=super.height){
            positionY = JOptionPane.showInputDialog(this.frame,"Enter the y position");
            if(positionY == null){
                System.exit(0);
            }
            try {

                ret[1] = Integer.parseInt(positionY);
                if(ret[1]<0 || ret[1]>=super.height){
                    throw new NumberFormatException("newShot : The value "+positionY+" entered is not correct");
                }

            }catch(NumberFormatException e){
                e.printStackTrace();
            }
        }

        return ret;
    }

    /**
     * Realises the arrangement of the departure ships.
     */
    @Override
    public void shipPlacement() {
        String fileName = null;
        File fileConfig;
        boolean findFile = false;

        //-----------------------------------
        //-----File access for the jar-------
        //-----------------------------------
        String path = null;
        try {
            path = URLDecoder.decode(HumanPlayer.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String[] data = path.split("/");
        path ="";
        for(int i=0;i<data.length-2;i++){
            path+=data[i]+"/";
        }

        path += "data/";
        //----------------------------------------

        while (!findFile){
            fileName = JOptionPane.showInputDialog(this.frame,"Enter the file name for the ship placement");
            path += fileName;
            fileConfig = new File(path);
            if(fileConfig.exists()){
                findFile = true;
            }else{
                data = path.split("/");
                path ="";
                for(int i=0;i<data.length-2;i++){
                    path+=data[i]+"/";
                }
                path += "data/";
            }
        }

        this.readConfiguration(path);


        int x,y;

        try{
            for(Ship s : super.fleet){
                x = s.getxOrigin();
                y = s.getyOrigin();
                if(s.getDirection().equals(Direction.HORIZONTAL)){
                    /*
                    Test the first square :
                    Why need I test only one square ? And not every squares together ?
                    Because I need to check every squares around the first
                    square if there is a square that can be problematic. Whereas on
                    the others squares I just need to check in front and on the sides;
                    especially not behind because the program will say "there is a ship
                    behind you, you can't place the part of your ship here" : he don't understand
                    that is the part of HIS ship. So, this a way to bypass this error.
                     */
                    if(x<0 || x>=super.width){
                        throw new ArrayIndexOutOfBoundsException(super.myGrid[x][y]+"shipPlacement : " +
                                "The position of the ship is outside of the grid");
                    }
                    if(y==0 && x==0){
                        if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y + 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(y==(this.height -1) && x==(this.width -1)){
                        if(!super.myGrid[(x - 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(y==0 && x==(this.width -1)){
                        if(!super.myGrid[(x - 1)][y].isFree() || !super.myGrid[x][(y + 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(y==(this.height -1) && x==0){
                        if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(y==0){
                        if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[(x - 1)][y].isFree()
                                || !super.myGrid[x][(y + 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(y==(this.height -1)){
                        if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree() ||
                                !super.myGrid[(x - 1)][y].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(x==0){
                        if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree() ||
                                !super.myGrid[x][(y + 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(x==(this.width -1)){
                        if(!super.myGrid[(x - 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree() ||
                                !super.myGrid[x][(y + 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else{
                        if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree() ||
                                !super.myGrid[(x - 1)][y].isFree() || !super.myGrid[x][(y + 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }

                    super.myGrid[x][y].setBusy();
                    x++;
                    /*
                    Test the others squares in the same spirit as the previous explanation.
                     */
                    for(int i = 1; i<=s.getSize()-1;i++){
                        if(x<0 || x>=super.width){
                            throw new ArrayIndexOutOfBoundsException("shipPlacement : " +
                                    "The position of the ship is outside of the grid");
                        }else{
                            if(y==0 && x==0){
                                if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y + 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(y==(this.height -1) && x==(this.width -1)){
                                if(!super.myGrid[x][(y - 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(y==0 && x==(this.width -1)){
                                if(!super.myGrid[x][(y + 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(y==(this.height -1) && x==0){
                                if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(y==0){
                                if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y + 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(y==(this.height -1)){
                                if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(x==0){
                                if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree() ||
                                        !super.myGrid[x][(y + 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(x==(this.width -1)){
                                if(!super.myGrid[x][(y - 1)].isFree() || !super.myGrid[x][(y + 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else{
                                if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree()
                                        || !super.myGrid[x][(y + 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }

                            super.myGrid[x][y].setBusy();
                            x++;

                        }
                    }
                }else if(s.getDirection().equals(Direction.VERTICAL)){

                    if(y<0 || y>=super.height){
                        throw new ArrayIndexOutOfBoundsException("super.myGrid[x][y]+shipPlacement : " +
                                "The position of the ship is outside of the grid");
                    }

                    /*
                    Test the first square :
                    Why need I test only one square ? And not every squares together ?
                    Because I need to check every squares around the first
                    square if there is a square that can be problematic. Whereas on
                    the others squares I just need to check in front and on the sides;
                    especially not behind because the program will say "there is a ship
                    behind you, you can't place the part of your ship here" : he don't understand
                    that is the part of HIS ship. So, this a way to bypass this error.
                     */
                    if(y==0 && x==0){
                        if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y + 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(y==(this.height -1) && x==(this.width -1)){
                        if(!super.myGrid[(x - 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(y==0 && x==(this.width -1)){
                        if(!super.myGrid[(x - 1)][y].isFree() || !super.myGrid[x][(y + 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(y==(this.height -1) && x==0){
                        if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(y==0){
                        if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[(x - 1)][y].isFree()
                                || !super.myGrid[x][(y + 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(y==(this.height -1)){
                        if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree() ||
                                !super.myGrid[(x - 1)][y].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(x==0){
                        if(!super.myGrid[(x+1)][y].isFree() || !super.myGrid[x][(y-1)].isFree() ||
                                !super.myGrid[x][(y+1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else if(x==(this.width -1)){
                        if(!super.myGrid[(x - 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree() ||
                                !super.myGrid[x][(y + 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }else{
                        if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y - 1)].isFree() ||
                                !super.myGrid[(x - 1)][y].isFree() || !super.myGrid[x][(y + 1)].isFree()){
                            throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                        }
                    }

                    super.myGrid[x][y].setBusy();
                    y++;

                    /*
                    Test the others squares in the same spirit as the previous explanation.
                     */
                    for(int i = 1; i<=s.getSize()-1;i++){
                        if(y<0 || y>=super.height){
                            throw new ArrayIndexOutOfBoundsException(super.myGrid[x][y]+"shipPlacement : " +
                                    "The position of the ship is outside of the grid");
                        }else{
                            if(y==0 && x==0){
                                if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[x][(y + 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(y==(this.height -1) && x==(this.width -1)){
                                if(!super.myGrid[(x - 1)][y].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(y==0 && x==(this.width -1)){
                                if(!super.myGrid[(x - 1)][y].isFree() || !super.myGrid[x][(y + 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(y==(this.height -1) && x==0){
                                if(!super.myGrid[(x + 1)][y].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(y==0){
                                if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[(x - 1)][y].isFree()
                                        || !super.myGrid[x][(y + 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(y==(this.height -1)){
                                if(!super.myGrid[(x + 1)][y].isFree() || !super.myGrid[(x - 1)][y].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(x==0){
                                if(!super.myGrid[(x+1)][y].isFree() ||
                                        !super.myGrid[x][(y+1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else if(x==(this.width -1)){
                                if(!super.myGrid[(x - 1)][y].isFree() || !super.myGrid[x][(y + 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }else{
                                if(!super.myGrid[(x + 1)][y].isFree()|| !super.myGrid[(x - 1)][y].isFree() ||
                                        !super.myGrid[x][(y + 1)].isFree()){
                                    throw new ForbiddenPlacementException(super.myGrid[x][y]+"shipPlacement : This disposition is forbidden");
                                }
                            }

                            super.myGrid[x][y].setBusy();
                            y++;
                        }
                    }
                }
            }

        } catch (ArrayIndexOutOfBoundsException | ForbiddenPlacementException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method allows to read the configuration for
     * the placement of the ship.
     * @param fileName The name of the file where we can find
     *                 placement of the ship.
     */
    private void readConfiguration(String fileName){

        Scanner in = null;

        try{
            final String FINALFILENAME = fileName;
            //Open the file
            in = new Scanner (new FileReader(FINALFILENAME));
            in.useDelimiter(this.DELIMITER);

            //The variable that make the method works
            String theLine; //A line of the configuration file
            String [] theInformation; // Every Information of a line
            String shipName; //The name of the ship
            Direction direction = null; //The direction of the ship
            int xPos = 0; //The position of the ship according the x axe
            int yPos = 0; //The position of the ship according the y axe
            int i = 0; //a counter
            int j = 0; //a counter
            /*
            This arrayList allows to mark the ships in the test loop to don't initialize the same ship
             */
            ArrayList<Integer> markShipTest = new ArrayList<Integer>();
            /*
            This arrayList allows to mark the ships in the initialize loop to don't initialize the same ship
             */
            ArrayList<Integer> markShipInitialize = new ArrayList<Integer>();
            boolean find = false;

            boolean ok = false;

            while(in.hasNextLine()){
                theLine = in.nextLine();
                //Split every information of the line and take it in the array
                theInformation = theLine.split(this.DELIMITER);

                //Puts the name of the boat in this string
                shipName = theInformation[0];

                //The test loop
                for(Ship s : super.fleet){
                    if(s.getName().equalsIgnoreCase(shipName) && !markShipTest.contains(j) && !find){
                        ok = true;
                        find = true;
                        markShipTest.add(j);
                    }
                    j++;
                }
                j=0;
                find = false;

                //Test if the second and third argument are number
                //And if this is a correct number
                try {
                    xPos = Integer.parseInt(theInformation[1]);
                    yPos = Integer.parseInt(theInformation[2]);
                    if(xPos<0 || yPos<0 || xPos>super.height || yPos>super.width){
                        throw new NumberFormatException("readConfiguration : The position of the ship is not available");
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                //Test if the direction is a correct direction
                try{
                    if(theInformation[3].equalsIgnoreCase("HORIZONTAL") ||
                            theInformation[3].equalsIgnoreCase("VERTICAL")){
                        direction = Direction.valueOf(theInformation[3]);
                    }else{
                        throw new IllegalArgumentException("readConfiguration : error : The selected mode " +
                                "is not available");
                    }
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                }

                /*
                Test if the boolean ok is true :
                if it's equal to true then it will place the ship
                if it's equal to false then it creates an exception
                 */
                try {
                    if(ok){
                        //Initialize loop
                        for(Ship s : super.fleet){
                            if(s.getName().equalsIgnoreCase(shipName) && !markShipInitialize.contains(j) && !find){
                                s.setDirection(direction);
                                s.setxOrigin(xPos);
                                s.setyOrigin(yPos);
                                markShipInitialize.add(j);
                                find = true;
                            }
                            j++;
                        }
                    }else{
                        throw new IllegalArgumentException("readConfiguration : The name of the ship doesn't exist !");
                    }
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                }

                theInformation = null;
                i++;
                j=0;
                find = false;
            }

            //Test if there are the same number of ship between
            //the configuration file and the placement Ship file
            try{
                if(super.fleet.size() != i){
                    throw new IllegalArgumentException("readConfiguration : There is a difference about the number" +
                            " of ship between both configuration file.");
                }
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }

            in.close();

        } catch (FileNotFoundException e){
            System.out.println("readConfiguration - This fill doesn't exist : "+fileName);
        }

    }
}
