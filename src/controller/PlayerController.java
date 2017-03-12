package controller;
import entity.*;
import dao.*;
import java.util.*;

/**
 * PlayerController is the controller class for player related function
 */
public class PlayerController{
	private ArrayList<Player> playerList;
	private PlayerDAO playerDAO;

    /**
     * Creates PlayerController object and initialize playerList with player data from PlayerDAO
     */  	
	public PlayerController(){
		playerDAO = new PlayerDAO();
		playerList = playerDAO.importPlayerData();
	}

    /**
     * Get the list of all player
     * @return an ArrayList of Player
     */ 	
	public ArrayList<Player> retrieveAll(){
		return playerList;
	}

    /**
     * Add new registered player
     * @param username the username of the player
     * @param fullname the fullname of the player
     * @param password the password of the player
     * @param landCtrl to create 5 plots of land for player
     */ 	
	public void addPlayer(String username, String fullname, String password, LandController landCtrl){
		Player newPlayer = new Player(username, fullname, password, 1000, 0, 0);		
		playerList.add(newPlayer);		
		landCtrl.addLand(newPlayer, 5);		
		playerDAO.updatePlayerDAO(playerList);
	}

    /**
     * Gets the player based on username
     * @param username the username of the player to be retrieved
     * @return a player object
     */ 	
	public Player retrieve(String username){
		for(Player player: playerList){           
			if(player.getUserName().equals(username)){
				return player;
			}
		}
        return null;
	}
    
    /**
     * Updates the player.csv via PlayerDAO
     */     
    public void updatePlayer() {
        playerDAO.updatePlayerDAO(playerList);
    }

    /**
     * Update the rank of player based on experience points
     * @param player the player to be updated
     * @param exp the experience points to be added to the player current experience points
     * @param rankCtrl to retrieve rank based on player's total exp
     * @return the number of land to add for the player based on rank
     */ 	
	public int updateRank(Player player, int exp, RankController rankCtrl){
		int noOfLandToAdd = 0;
		
		for(Player selectedPlayer: playerList){
			if(player.getUserName().equals(selectedPlayer.getUserName())){
				selectedPlayer.addExp(exp);				
				int noOfLand = selectedPlayer.getNoOfLand();
				int totalExp = selectedPlayer.getExp();				
				Rank rank = rankCtrl.retrieveRank(totalExp);				
				int plotsOfLand = rank.getPlots();				
				noOfLandToAdd = plotsOfLand - noOfLand;
			}
		}
		
		return noOfLandToAdd;
	}
}