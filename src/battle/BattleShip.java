package battle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is then responsible
 * for starting a new game by creating
 * an instance of Game with the information
 * obtained from the file and sending it
 * the message "start".
 * @author Kierian Tirlemont
 */
public class BattleShip {

    private int width;
    private int height;
    private final String DELIMITER = "\\s*:\\s*";

    private Mode mode;
    private Game gamePlay;
    private ArrayList<Ship> fleet;

    /**
     * The constructor of the class which initialises the attribute.
     * @param fileName The name of the file which contains the size of the grid.
     * @param playerName1 The name of the first player.
     * @param playerName2 The name of the second player.
     */
    public BattleShip(String fileName, String playerName1, String playerName2) throws IllegalArgumentException{
       try{
            if(fileName == null){
                throw new IllegalArgumentException("BattleShip : file is null");
            }
            if(playerName1 == null){
                throw new IllegalArgumentException("BattleShip : playerName1 is null");
            }
            if(playerName2 == null){
                throw new IllegalArgumentException("BattleShip : playerName1 is null");
            }

            this.fleet = new ArrayList<Ship>();

           //-----------------------------------
           //-----File access for the jar-------
           //-----------------------------------
           String path = null;
           try {
               path = URLDecoder.decode(BattleShip.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }

           String[] data = path.split("/");
           path ="";
           for(int i=0;i<data.length-2;i++){
               path+=data[i]+"/";
           }

           path += "data/";
           path += fileName;
           //----------------------------------------

            this.configure(path);
            //this.printConfiguration();

            this.gamePlay = new Game(this.fleet,playerName1,playerName2,this.width,this.height,this.mode);
            this.gamePlay.start();

       } catch (IllegalArgumentException e) {
            System.err.println(e);
       }


    }

    /**
     * This method reads the configuration's file and initialises the attribute link to the file.
     * @param fileName The name of the configuration's file.
     */
    private void configure(String fileName){
        try{
            final String FINALFILENAME = fileName;
            //Open the file
            Scanner in = new Scanner (new FileReader(FINALFILENAME));
            in.useDelimiter(this.DELIMITER);

            //The variable that make the method works
            String theMode; //The mode
            String theLine; //A line of the configuration file
            String [] theInformation; // Every Information of a line
            boolean isNumberUn = false; //The boolean which is the result of the first argument (if it's a number)
            boolean isNumberDeux = false; //The boolean which is the result of the first argument (if it's a number)

            while(in.hasNextLine()){
                theLine = in.nextLine();
                //Split every information of the line and take it in the array
                theInformation = theLine.split(this.DELIMITER);
                //Test if the first information is a number
                try {
                    Integer.parseInt(theInformation[0]);
                    isNumberUn = true;
                } catch (NumberFormatException e){
                    isNumberUn = false;
                }
                //Test if the second information is a number
                try {
                    Integer.parseInt(theInformation[1]);
                    isNumberDeux = true;
                } catch (NumberFormatException e){
                    isNumberDeux = false;
                }
                //If both are true, we speak about the height and the width
                if(isNumberDeux && isNumberUn){
                    if(Integer.parseInt(theInformation[0])<0){
                        throw new IllegalArgumentException("Configure : error : The width is inferior to 0 ");
                    }
                    if(Integer.parseInt(theInformation[1])<0){
                        throw new IllegalArgumentException("Configure : error : The height is inferior to 0 ");
                    }
                    this.width = Integer.parseInt(theInformation[0]);
                    this.height = Integer.parseInt(theInformation[1]);
                    //If there are no number, we speak about the mode
                }else if(!isNumberDeux && !isNumberUn && theInformation[0].equalsIgnoreCase("mode")){
                    if(theInformation[1].equalsIgnoreCase("AA") ||
                            theInformation[1].equalsIgnoreCase("HA") ||
                            theInformation[1].equalsIgnoreCase("HH")){
                        theMode = theInformation[1];
                        this.mode = Mode.valueOf(theMode);
                    }else{
                        throw new IllegalArgumentException("Configure : error : The selected mode is not available");
                    }
                    //If the first information is a word and the second a number, we speak about a new ship
                }else if(!isNumberUn && isNumberDeux){
                    if(Integer.parseInt(theInformation[1])<=0){
                        throw new IllegalArgumentException("Configure : error : The size of the boat " +
                                "can't be inferior to 0");
                    }
                    Ship ship = new Ship(theInformation[0],Integer.parseInt(theInformation[1]));
                    this.fleet.add(ship);
                }

                isNumberDeux = false;
                isNumberUn = false;
                theInformation = null;
            }

            in.close();

        } catch (FileNotFoundException e){
            System.out.println("readFile - This fill doesn't exist : "+fileName);
        }
    }

    /**
     * This class allows to print the attributes of the class.
     * And it allows to print the ship's elements.
     */
    public void printConfiguration(){
        System.out.println("BattleShip{\nwidth=" + this.width + "\nheight=" + height + "\nDELIMITER='" + this.DELIMITER+
                '\''+"\nmode='" + this.mode + '\'' + "\nfleet = [");
        for(Ship s : this.fleet){
            System.out.println(s.toString());
        }
        System.out.println("]");
    }
}
