package controller;
import entity.*;
import dao.*;
import java.util.*;

/**
 * SeedController is the controller class for seed related function
 */
public class SeedController {
    private ArrayList<Seed> seedList;
    private SeedDAO seedDAO;

    /**
     * Creates SeedController object and initialize seedList with seed data from SeedDAO
     */     
    public SeedController(){
        seedDAO = new SeedDAO();
        seedList = seedDAO.importSeedData();
    }
    
    /**
     * Get the list of all seed
     * @return an ArrayList of Seed
     */   
    public ArrayList<Seed> retrieveAll() {
        return seedList;
    }
    
    /**
     * Get the seedList for the specified player
     * @param p the player of the seedList to be retrieved
     * @return an ArrayList of Seed that belongs to the player
     */   
    public ArrayList<Seed> retrieveSeed(Player p) {
        ArrayList<Seed> listToReturn = new ArrayList<Seed>();
        String userName = p.getUserName();
        
        //retrieve the seedlist based on username
        for(int i = 0; i<seedList.size(); i++) {
            Seed seed = seedList.get(i);
            if(seed.getUserName().equals(userName)) {
                listToReturn.add(seed);
            }
        }        
        return listToReturn;
    }

    /**
     * Deduct the quantity of the seed after player has plant or send it as gift
     * @param sList the player's seedList
     * @param seed the seed use to deduct quantity
     * @param seedCtrl seedController
     */       
    public void removeSeed(ArrayList<Seed> sList, Seed seed, SeedController seedCtrl) {       
        //get seedname of the seed to be remove
        String seedToRemove = seed.getSeedName();
        String seedUserName = seed.getUserName();
		
		ArrayList<Seed> allSeedList = seedCtrl.retrieveAll();
        
        //find the seed in the player's seed arraylist 
        for(int i = 0; i<sList.size(); i++) {
            Seed s = sList.get(i);
            if(s.getSeedName().equals(seedToRemove) && s.getUserName().equals(seedUserName)){
                if(s.getQuantity() == 1){
                    //remove seed from arrayList
                    allSeedList.remove(s);
                } else {
                    //minus one seed
                    s.deductQuantity();
                }
            }
        }		
        seedDAO.updateSeedDAO(allSeedList);
    }

    /**
     * Add seed to player seedList
     * @param username the username of the player
     * @param seedName the name of the seed to be added
     * @param quantity the quantity of the seed to be added
     * @param seedCtrl seedController
     */       
    public void addSeed(String username, String seedName, int quantity, SeedController seedCtrl){
		Seed seed = new Seed(username, seedName, quantity);
		
		for(Seed currentSeed: seedCtrl.retrieveAll()){
			if(currentSeed.getUserName().equals(username) && currentSeed.getSeedName().equals(seedName)){
				currentSeed.addQuantity(quantity);
				seedDAO.updateSeedDAO(seedList);
				return;
			}
		}
		
		seedList.add(seed);
		seedDAO.updateSeedDAO(seedList);
	}
}