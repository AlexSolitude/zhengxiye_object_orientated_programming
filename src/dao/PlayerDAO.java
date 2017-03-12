package dao;
import entity.*;
import controller.*;
import java.io.*;
import java.util.*;

/**
 * PlayerDAO is used to extract and update all the data from player.csv file
 */
public class PlayerDAO{
	private ArrayList<Player> playerList;

    /**
     * Creates PlayerDAO object and initialize playerList
     */	
	public PlayerDAO(){
		playerList = new ArrayList<Player>();
	}

    /**
     * Get the list of player data from player.csv
     * @return an ArrayList of Player
     */	
	public ArrayList<Player> importPlayerData() {
    
        try {
            //Try to access player.csv in the given directory
			File file = new File("data\\player.csv");
            Scanner fileIn = new Scanner(file);
            
            fileIn.skip("userName,fullName,password,gold,exp,noOfLand");
            
			//Use comma as delimiter in extracting various player info
			fileIn.useDelimiter(",|\r\n|\n");
            
            while (fileIn.hasNext()) {
                String userName = fileIn.next().trim();
                String fullName = fileIn.next().trim();
                String password = fileIn.next().trim();
                int gold = fileIn.nextInt();
                int exp = fileIn.nextInt();
				int noOfLand = fileIn.nextInt();
				
				//Create new players based on extracted info
                Player player = new Player(userName, fullName, password, gold, exp, noOfLand);
				
				//Add players to playerList
                playerList.add(player);
            }
            
        }
        
        catch (IOException e) {
            //Specify the location of IOException
			e.printStackTrace();
        }
		
		return playerList;
	}

    /**
     * Update player.csv after changes are made
     * @param playerList an ArrayList of Player
     */    
	public void updatePlayerDAO(ArrayList<Player> playerList){
		try {
			PrintStream writer = new PrintStream(new FileOutputStream("data\\player.csv", false));
			 writer.println("userName,fullName,password,gold,exp,noOfLand");
             
			for(Player player: playerList){
				writer.println(player.getUserName() + "," + player.getFullName() + "," + 
				player.getPassword() + "," + player.getGold() + "," + player.getExp() + "," + player.getNoOfLand());
			}
			writer.close();
		}
			
		catch (IOException e) {
			//Specify the location of IOException
			e.printStackTrace();
		}	 
	}
}