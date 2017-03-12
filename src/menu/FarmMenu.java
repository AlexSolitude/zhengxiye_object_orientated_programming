package menu;
import entity.*;
import controller.*;
import dao.*;
import java.util.*;

/**
* FarmMenu allows the player to plant, clear and harvest crops
*/
public class FarmMenu{
    private Player loggedInPlayer;
    private LandMenu landMenu;
    private PlantMenu plantMenu;

    private PlayerController playerCtrl;
    private LandController landCtrl;
    private RankController rankCtrl;
    private SeedController seedCtrl;
    private CropController cropCtrl;

    /**
    * FarmMenu constructor
    */     
    public FarmMenu(){
        landMenu = new LandMenu();
        plantMenu = new PlantMenu();
    }

    /**
    * Displays the farm menu with the player's land information 
    * @param player the player of this farm
    * @param playerCtrl to display player details
    * @param landCtrl to retrieve/perform land related functions
    * @param rankCtrl to retrieve player rank info
    * @param seedCtrl to pass the planted seed to Plant Menu
    * @param cropCtrl to retrieve crop of the planted seed
    */     
    public void displayFarmMenu(Player player, PlayerController playerCtrl, LandController landCtrl, RankController rankCtrl, SeedController seedCtrl, CropController cropCtrl) {
        //Set loggedInPlayer and controllers
        loggedInPlayer = player;
        this.playerCtrl = playerCtrl;
        this.landCtrl = landCtrl;
        this.rankCtrl = rankCtrl;
        this.seedCtrl = seedCtrl;
        this.cropCtrl = cropCtrl;
        
        //Methods to display player details
		System.out.println("\n== Farm City :: My Farm ==");
        displayPlayerDetails();
        landMenu.displayLandMenu(loggedInPlayer, landCtrl);
        
        System.out.print("[M]ain | [P]lant | C[L]ear | [H]arvest > ");
		
        String playerChoice = "";
		char choiceLetter = 'a';
        String choiceNumber = "";
		int choice = 0;
        do {
            //Extract player input
			Scanner sc = new Scanner(System.in);
			playerChoice = sc.nextLine();
			
            //validation
			while(playerChoice.length() > 2 || playerChoice.length() == 0 
			||(playerChoice.charAt(0) == 'P' && playerChoice.length() != 2) || (playerChoice.charAt(0) == 'p' && playerChoice.length() != 2)
			||(playerChoice.charAt(0) == 'L' && playerChoice.length() != 2) || (playerChoice.charAt(0) == 'l' && playerChoice.length() != 2)){
				System.out.println("Please check the following areas of your input:\n");
				System.out.println("The length of input should be 1 or 2 characters!\n");
				System.out.println("If you wish to plant or clear, the input should be 2 characters in the following format: [P][landNumber] [L][landNumber]\n");
				
				System.out.print("[M]ain | [P]lant | C[L]ear | [H]arvest > ");
				
				playerChoice = sc.nextLine();
			}
			
			choiceLetter = playerChoice.charAt(0);
			switch(choiceLetter){
				case 'm':
				choiceLetter = 'M';
				break;
				case 'p':
				choiceLetter = 'P';
				break;
				case 'l':
				choiceLetter = 'L';
				break;
				case 'h':
				choiceLetter = 'H';
				break;
			}
			
			//validation
			if(playerChoice.length() == 2){
				choiceNumber = "" + playerChoice.charAt(1);
				boolean testBoolean = true;
				
				for(int i = 1; i <= player.getNoOfLand(); i++){
					String testNum = "" + i;
					
					if(choiceNumber.equals(testNum)){
						testBoolean = false;
					}
				}				
				while(testBoolean){
					System.out.print("\nError! The input has to be a number between 1 to " + player.getNoOfLand() + 
					"!\nPlease re-enter the land you want to plant on: ");
					
					choiceNumber = sc.next();
					
					for(int i = 1; i <= player.getNoOfLand(); i++){
						String testNum = "" + i;
					
						if(choiceNumber.equals(testNum)){
							testBoolean = false;
						}
					}
				}
				
				choice = Integer.parseInt(choiceNumber);
			}
			
            //the main, plant, clear, harvest options
			switch(choiceLetter) {
                case 'M':
                    return;
                case 'P':
                    Land land = landCtrl.retrieveLand(player).get(choice - 1);
					
                    //check if selected land to plant is occupied by other crops
					if(land.getSeedName() == null || land.getSeedName().equals("null")){
						plantMenu.displayPlantMenu(player, land, landCtrl, seedCtrl, cropCtrl);
					}else{
						System.out.println("\nUnable to plant one the chosen land.\nThe land is occupied by other crops.");
					}																
                    break;
                    
                case 'L': 
					Land playerLand = landCtrl.retrieveLand(player).get(choice - 1);
					
                    //check if land to clear is empty
					if(playerLand.getSeedName() == null || playerLand.getSeedName().equals("null")){
						System.out.println("\nError! Unable to clear the crop in the chosen land.\nThe land is empty!");
						break;
					}
					
					boolean clearTest = landCtrl.clearLand(player, playerLand, playerCtrl);
					
					if(clearTest == false){
						System.out.println("\nError! The crop in the land you specified is not wilted.\nCrop clearing cannot be cleared!");
					}else{
						System.out.println("\nThe wilted crop in the land you specified is successfully cleared!");
					}					
                    break;
                    
                case 'H': 					
					HashMap<String, HashMap<String, Integer>> map = landCtrl.harvestCrop(player, cropCtrl, playerCtrl, rankCtrl);
					
					HashMap<String, Integer> units = map.get("units");
					HashMap<String, Integer> gold = map.get("gold");
					HashMap<String, Integer> exp = map.get("exp");
					HashMap<String, Integer> names = map.get("names");
					
					Iterator<String> iter = names.keySet().iterator();
					
					while(iter.hasNext()){
						String name = iter.next();
						
						System.out.println("You have harvested " + units.get(name) + " units of " + name + " for " + exp.get(name) +
						" XP and " + gold.get(name) + " gold.");
					}                   
                    break;
                default :
                    return;
            }               
        }while(choiceLetter != 'P' && choiceLetter != 'M' && choiceLetter != 'H' && choiceLetter != 'L');   
    }
    
    /**
    * Displays the player's fullname, rank and gold
    */     
    public void displayPlayerDetails() {
        //prints player fullname
        System.out.println("Welcome, " + loggedInPlayer.getFullName()); 
		
        //retrieve player rank
		Rank rank = rankCtrl.retrieveRank(loggedInPlayer.getExp());
		
        //prints player rank and gold
        System.out.println("Rank: " + rank.getRankName() + "\t\t" + "Gold: " + loggedInPlayer.getGold() + "\n");
    
    }

}