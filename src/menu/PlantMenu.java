package menu;
import entity.*;
import dao.*;
import controller.*;
import java.util.*;

/**
* PlantMenu allows player to select the seed to be planted
*/
public class PlantMenu {
    private ArrayList<Seed> seedList;
    private LandController landCtrl;
    private SeedController seedCtrl;
    private CropController cropCtrl;

    /**
    * PlantMenu constructor
    */      
    public PlantMenu() {
        seedList = new ArrayList<Seed>();
    }
    
    /**
    * Display the plant menu with list of seeds for player to plant
    * @param player the player of this farm
    * @param land the land where seed is to be planted
    * @param landCtrl the landController
    * @param seedCtrl the seedController
    * @param cropCtrl the cropController
    */ 
    public void displayPlantMenu(Player player, Land land, LandController landCtrl, SeedController seedCtrl, CropController cropCtrl) {
        
        //set controllers
        this.landCtrl = landCtrl;
        this.seedCtrl = seedCtrl;
        this.cropCtrl = cropCtrl;
		
        //retrieve player's seedlist
        seedList = seedCtrl.retrieveSeed(player);
		
		if(seedList.size() == 0){
			System.out.print("\nYou have no seeds to plant!\nPlease proceed to 'My Inventory' to buy the seeds to plant!\n");
			return;
		}
        
        //prints the list of seeds/crop that the player has
        System.out.println("Select the crop: ");
        for(int i = 0; i <seedList.size(); i++) {
            Seed seed = seedList.get(i);
            String seedName = seed.getSeedName();
            System.out.println((i+1) + ". " + seedName);
        }
        
        //set user input as char because options contains mixture of alphabets and numbers
        String userChoice = "";
		int input = 0;
		boolean testBoolean = true;
        
        Scanner sc = new Scanner(System.in);
        System.out.print("[M]ain | Select Choice > ");
            
        userChoice = sc.next();
			
		if(userChoice.equals("m") || userChoice.equals("M")){
			return;
		}
		
        //validations        
		for(int i = 1; i <= seedList.size(); i++){
			String testNum = "" + i;
				
			if(userChoice.equals(testNum)){
				testBoolean = false;
			}
		}
			
		while(testBoolean){
			System.out.print("\nError! The input has to be a number between 1 to " + seedList.size() + 
			"!\nPlease re-enter your choice: ");
				
			userChoice = sc.next();
			
			for(int i = 1; i <= seedList.size(); i++){
				String testNum = "" + i;
				
				if(userChoice.equals(testNum)){
					testBoolean = false;
				}
			}
		}
			
		input = Integer.parseInt(userChoice);
			
		//find planted seed from seedlist
		Seed plantedSeed = seedList.get(input-1);
		//update selected land information
		landCtrl.updateLand(plantedSeed, land, cropCtrl);
		//remove planted seed from seedlist
		seedCtrl.removeSeed(seedList,plantedSeed, seedCtrl);
		System.out.println("You have planted " + plantedSeed.getSeedName() + " on plot " + land.getLandNo());
			
	}
}