package controller;
import entity.*;
import dao.*;
import java.util.*;

/**
 * LandController is the controller class for My Farm function
 */
public class LandController{
	private ArrayList<Land> landList;
	private LandDAO landDAO;
	private PlayerDAO playerDAO;
	private PlayerController playerCtrl;
	private CropController cropCtrl;

    /**
     * Creates LandController object and initialize landList with land data from LandDAO
     */  	
	public LandController(){
		landDAO = new LandDAO();
		playerDAO = new PlayerDAO();
		landList = landDAO.importLandData();
	}

    /**
     * Get the list of all land
     * @return an ArrayList of Land
     */ 	
	public ArrayList<Land> retrieveAll(){
		return landList;
	}
    
    /**
     * Add Land for player
     * @param player the player to be added
     * @param noOfLand the number of land to add
     */ 	
	public void addLand(Player player, int noOfLand){
        //get player current noOfLand
		int playerNoOfLand = player.getNoOfLand();
		
		for(int i = playerNoOfLand + 1; i <= playerNoOfLand + noOfLand; i++){
			landList.add(new Land(player.getUserName(), i, null, 0L, 0L, 0L));
		}
		
		player.setNoOfLand(playerNoOfLand + noOfLand);
		
		landDAO.updateLandDAO(landList);
	}

    /**
     * Get the list of land that belongs to this player
     * @param player to retrieve land based on this player
     * @return an ArrayList of Land that belongs to this player
     */ 	
	public ArrayList<Land> retrieveLand(Player player){
		ArrayList<Land> playerLandList = new ArrayList<Land>();
		
		for(Land land: landList){
			if(land.getUserName().equals(player.getUserName())){
				playerLandList.add(land);
			}
		}
		
		return playerLandList;
	}

    /**
     * Get the crop's growth progress on this land
     * @param land the land to check the growth progress
     * @return the growthProgress of the crop on this land
     */ 		
	public double growthProgress(Land land){
		Calendar current = Calendar.getInstance();
		
		double currentGrowth = (double)current.getTime().getTime() - land.getPlantTime();
		double totalGrowth = (double)land.getHarvestTime() - land.getPlantTime();
		
		double growthPercentage = currentGrowth / totalGrowth * 100;
		
		return growthPercentage;
	}

    /**
     * Update the land information after player has plant seed
     * @param seed the seed that was planted
     * @param land the land to be updated
     * @param cropCtrl to retrieve the crop based on the planted seed
     */ 	
	public void updateLand(Seed seed, Land land, CropController cropCtrl) { 
        Calendar plantTime = Calendar.getInstance();
        Calendar harvestTime = Calendar.getInstance();
        Calendar wiltTime = Calendar.getInstance();
		
        //get the crop based on seedname
        Crop crop = cropCtrl.retrieveCrop(seed.getSeedName());
		
        //Calculate the harvest time 
		harvestTime.add(Calendar.MINUTE, crop.getTime());
		
		//Calculate the wilt time
		wiltTime.add(Calendar.MINUTE, 2 * crop.getTime());
        
        //Player username
        String userName = land.getUserName();        
        int landNo = land.getLandNo();
        
        for(int i = 0; i<landList.size(); i++) {
            Land landToUpdate = landList.get(i);
            String landUserName = landToUpdate.getUserName();
            int landNoToUpdate = landToUpdate.getLandNo();
            
            //check if the land to update belongs to the player and proceed to update 
            if(landUserName.equals(userName)) {
                if(landNoToUpdate == landNo) {
					landToUpdate.setSeedName(seed.getSeedName());
                    landToUpdate.setPlantTime(plantTime.getTime().getTime());
                    landToUpdate.setHarvestTime(harvestTime.getTime().getTime());
                    landToUpdate.setWiltTime(wiltTime.getTime().getTime());
                }
            }
        }
        
        landDAO.updateLandDAO(landList);
    }

    /**
     * Harvest the crop upon maturity
     * @param player the player who wants to harvest
     * @param cropCtrl to retrieve the crop
     * @param playerCtrl to retrieve playerList and update player rank based on experience points gained
     * @param rankCtrl to update player rank after harvesting based on experience points gained
     * @return three HashMap of the harvested crop details (units, gold, experience harvested)
     */ 	
	public HashMap<String, HashMap<String, Integer>> harvestCrop(Player player, CropController cropCtrl, PlayerController playerCtrl, RankController rankCtrl){
		ArrayList<Land> playerLandList = retrieveLand(player);
		
		ArrayList<Land> harvestLandList = new ArrayList<Land>();
		
        //check if growthPercentage reaches 100 (can be harvest)
		for(Land land: playerLandList){
			double growthPercentage = growthProgress(land);
			
			if(growthPercentage >= 100.0 && growthPercentage <= 200.0){
				harvestLandList.add(land);
			}
		}
		
		int totalGold = 0;
		int totalExp = 0;
		
        //creates HashMap to store the harvest details
		HashMap<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String, Integer>>();
		
		HashMap<String, Integer> names = new HashMap<String, Integer>();
		HashMap<String, Integer> units = new HashMap<String, Integer>();
		HashMap<String, Integer> gold = new HashMap<String, Integer>();
		HashMap<String, Integer> exp = new HashMap<String, Integer>();
		
        //retrieve the crop information of each land that be harvest
		for(Land land: harvestLandList){
			String seedName = land.getSeedName();
			
			names.put(seedName, 1);
			
			Crop crop = cropCtrl.retrieveCrop(seedName);
			
			int minYield = crop.getMinYield();
			int maxYield = crop.getMaxYield();
			int salePrice = crop.getSalePrice();
			int xp = crop.getXp();
			
			int cropUnits = minYield + ((int) (Math.random() * (maxYield-minYield)));
			
			int cropExp = crop.getXp() * cropUnits;
			
			int cropGold = crop.getSalePrice() * cropUnits;
			
			totalGold += cropGold;
			totalExp += cropExp;
			
            //check if seedName is already in HashMap; add the harvest units
			if(units.containsKey(seedName) == false){
				units.put(seedName, cropUnits);
			}else{
				int hashUnits = units.get(seedName);
				hashUnits += cropUnits;
				units.put(seedName, hashUnits);
			}
			
            //check if seedName is already in HashMap; add the gold gained from harvest
			if(gold.containsKey(seedName) == false){
				gold.put(seedName, cropGold);
			}else{
				int hashGold = gold.get(seedName);
				hashGold += cropGold;
				units.put(seedName, hashGold);
			}
			
            //check if seedName is already in HashMap; add the experience points gained from harvest
			if(exp.containsKey(seedName) == false){
				exp.put(seedName, cropExp);
			}else{
				int hashExp = exp.get(seedName);
				hashExp += cropExp;
				units.put(seedName, hashExp);
			}
			
            //clear the land after harvest
			land.setSeedName(null);
			land.setPlantTime(0L);
			land.setHarvestTime(0L);
			land.setWiltTime(0L);
		}
		
		landDAO.updateLandDAO(harvestLandList);
		
		ArrayList<Player> pList = playerCtrl.retrieveAll();
		
        //update player information based on harvest details
		player.addGold(totalGold);
		int noOfLandToAdd = playerCtrl.updateRank(player, totalExp, rankCtrl);
		
		addLand(player, noOfLandToAdd);
		
		for(Player p: pList){
			if(p.getUserName().equals(player.getUserName())){
				p = player;
			}
		}
		
		playerDAO.updatePlayerDAO(pList);

        //place harvest details in HashMap
		map.put("units", units);
		map.put("gold", gold);
		map.put("exp", exp);
		map.put("names", names);
		
		return map;
	}

    /**
     * Clear the land for the player when crop wilted
     * @param player the player who wish to clear the land
     * @param land the land to be cleared
     * @param playerCtrl to retrieve playerList 
     * @return true if land is able to clear; false if land is unable to clear
     */ 	
	public boolean clearLand(Player player, Land land, PlayerController playerCtrl){
		ArrayList<Land> playerLandList = retrieveLand(player);
		this.playerCtrl = playerCtrl;
		
		boolean deductTest = false;
		boolean clearTest = false;
		
        //perform clearing
		for(Land playerLand: playerLandList){
			if(playerLand.getLandNo() == land.getLandNo()){
				if(growthProgress(playerLand) > 200.0){
					land.setSeedName(null);
					land.setPlantTime(0L);
					land.setHarvestTime(0L);
					land.setWiltTime(0L);
				
					landDAO.updateLandDAO(playerLandList);
					
					clearTest = true;
				}
			}
		}
				
		ArrayList<Player> playerList = playerCtrl.retrieveAll();
		
        //deduct gold upon clearing
		for(Player finedPlayer: playerList){
			if(finedPlayer.getUserName().equals(finedPlayer.getUserName())){
				deductTest = finedPlayer.deductGold(5);
				
                //helps player to deduct even if insufficient gold
				if(deductTest == false){
					System.out.println("Your balance is not enough to deduct!\nBut we do clearing for free this time.\nRemember to top up your balance!");
				}
			}
		}
		
		playerDAO.updatePlayerDAO(playerList);
		
		return clearTest;
	}
}