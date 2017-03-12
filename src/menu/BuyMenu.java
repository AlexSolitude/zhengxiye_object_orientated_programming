package menu;
import entity.*;
import dao.*;
import controller.*;
import java.util.*;

/**
* BuyMenu allows the player to buy seeds from the store
*/
public class BuyMenu {
    private Player loggedInPlayer;
    private CropController cropCtrl;
    private RankController rankCtrl;
    private SeedController seedCtrl;
    private PlayerController playerCtrl;

    /**
    * BuyMenu constructor
    */    
    public BuyMenu() {
    
    }

    /**
    * Displays the buy menu with the list of seeds and options
    * @param loggedInPlayer the player to buy seeds
    * @param cropCtrl to access cropController and display the list of crops available for sale
    * @param rankCtrl to retrieve player rank info
    * @param seedCtrl to add purchased seeds into player's seedList
    * @param playerCtrl to display player details
    */    
    public void displayBuyMenu(Player loggedInPlayer, CropController cropCtrl, RankController rankCtrl, SeedController seedCtrl, PlayerController playerCtrl) {
        //Set loggedInPlayer and controllers
        this.loggedInPlayer = loggedInPlayer;
        this.cropCtrl = cropCtrl;
        this.rankCtrl = rankCtrl;
        this.seedCtrl = seedCtrl;
        this.playerCtrl = playerCtrl;
        
        System.out.println();
        System.out.println("== Farm City :: Store ==");
        displayPlayerDetails();
        System.out.println("Seed Available: ");
        ArrayList<Crop> listOfCrops = cropCtrl.retrieveAll();
        
        //display the seeds available for sale
        for (int i=0 ; i<listOfCrops.size(); i++) {
            Crop crop = listOfCrops.get(i);
            System.out.println((i+1) + ". " + crop.getName() + " costs: " + crop.getCost() + " gold");
            
            if(crop.getTime() <= 60){
                System.out.println("   Harvests in: " + crop.getTime() + " mins");
            }else{
                System.out.println("   Harvests in: " + crop.getTime() / 60 + " hours");
            }    
            System.out.println("   XP Gained: " + crop.getXp());
        }
        
        System.out.println();
        System.out.print("[M]ain | Select choice > ");
        
        //handles player choice
        String playerChoice = "";
		char choiceLetter = 'a';
        int choiceNumber = 0;
        do {
            //Extract player input
			Scanner sc = new Scanner(System.in);
			playerChoice = sc.nextLine();
			
			choiceLetter = playerChoice.charAt(0);
			switch(choiceLetter){
				case 'm':
				choiceLetter = 'M';
				break;
			}
			
            //validation
			while(playerChoice.length() != 1 || choiceLetter != '1' && choiceLetter != '2' && choiceLetter != '3' 
			&& choiceLetter != '4' && choiceLetter != 'M'){
				System.out.print("\nError! The input has to be a number between 1 to 4 or M!\nPlease re-enter your choice: ");
				playerChoice = sc.nextLine();
				
				choiceLetter = playerChoice.charAt(0);
				
				switch(choiceLetter){
					case 'm':
					choiceLetter = 'M';
					break;
				}
			}
            
			switch(choiceLetter) {
                case 'M':
                    return;
                case '1':
                    processBuy(1, listOfCrops, seedCtrl);
                    break;
                case '2': 
                    processBuy(2, listOfCrops, seedCtrl);
                    break;
                case '3':
                    processBuy(3, listOfCrops, seedCtrl);
                    break;
                case '4': 
                    processBuy(4, listOfCrops, seedCtrl);
                    break;
                default :
                    return;
            }               
        }while(choiceLetter != 'M' && choiceLetter != '1' && choiceLetter != '2' && choiceLetter != '3' && choiceLetter != '4');
    }

    /**
    * Process the buying of seed and add it into player's seedList
    * @param option the option the player has selected
    * @param listOfCrops the list of crops available for sale
    * @param seedCtrl to add purchased seeds into player's seedList
    */    
    public void processBuy(int option, ArrayList<Crop> listOfCrops, SeedController seedCtrl) {
        
        Scanner sc2 = new Scanner(System.in);
        System.out.print("   Enter quantity > ");
		
		String quantity = sc2.next();
		int inputQuantity = 0;
		boolean testQuantity = false;
		
        //validation
		try{
			inputQuantity = Integer.parseInt(quantity);
		}catch(NumberFormatException e){
			testQuantity = true;
		}
		
		while(testQuantity){
			testQuantity = false;
			
			System.out.print("\nError! Please enter a number!\n	Please re-enter quantity:");
			
			quantity = sc2.next();
			
			try{
				inputQuantity = Integer.parseInt(quantity);
			}catch(NumberFormatException e){
				testQuantity = true;
			}
		}
		
        //check if player has sufficient gold to make the purchase
        Crop selectedCrop = listOfCrops.get(option - 1);
        int cost = inputQuantity * selectedCrop.getCost();
        if (cost > loggedInPlayer.getGold()) {
            System.out.println("Insufficent Gold!");
            displayBuyMenu(loggedInPlayer, cropCtrl, rankCtrl, seedCtrl, playerCtrl);
        } else {
            loggedInPlayer.deductGold(cost);
            playerCtrl.updatePlayer();
            seedCtrl.addSeed(loggedInPlayer.getUserName(), selectedCrop.getName(), inputQuantity, seedCtrl);
            System.out.println("   " + inputQuantity + " bags of seeds purchased for " + cost + " gold.");
        }
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