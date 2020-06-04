import battle.BattleShip;

import javax.swing.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * This class is the entry point of the program.
 * @author Kierian Tirlemont
 */
public class LaunchBattle {

    JFrame frame;

    /**
     * This method is the entry point of the program.
     * @param args The arguments
     */
    public static void main(String[] args){

        BattleShip battleShip1 = new BattleShip(nameConfigFile(),namePlayer("first"),namePlayer("second"));

    }

    /**
     * Ask to the user the name of the configuration file.
     * @return The name of the configuration file.
     */
    private static String nameConfigFile(){
        String fileName = null;
        File fileConfig;
        boolean trouve = false;

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
        //----------------------------------------

        while(!trouve){
            fileName = JOptionPane.showInputDialog("Enter the name of the configuration file");
            path += fileName;
            fileConfig = new File(path);
            if(fileConfig.exists()){
                trouve = true;
            }else{
                data = path.split("/");
                path ="";
                for(int i=0;i<data.length-2;i++){
                    path+=data[i]+"/";
                }
                path += "data/";
            }
        }

        return fileName;
    }

    /**
     * Ask to the user the name of the configuration file.
     * @return The name of the configuration file.
     */
    private static String namePlayer(String numberPlayer){
        String playerName;

        playerName = JOptionPane.showInputDialog("Enter the name of the "+numberPlayer+" player");

        return playerName;
    }

}
