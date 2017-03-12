package menu;
import entity.*;
import dao.*;
import controller.*;
import java.util.*;

/**
* InventoryMenu shows the list of seeds the player has
*/
public class InventoryMenu {

    private BuyMenu buyMenu;
    private GiftMenu giftMenu;

    private SeedController seedCtrl;
    private RankController rankCtrl;
    private CropController cropCtrl;
    private PlayerController playerCtrl;
    private Player loggedInPlayer;
    private GiftController giftCtrl;
    private FriendController friendCtrl;

    /**
    * InventoryMenu constructor
    */
    public InventoryMenu() {
        buyMenu = new BuyMenu();
        giftMenu = new GiftMenu();
    }

    /**
    * Displays the inventory menu with the list of seeds the player has
    * @param seedCtrl to perform buy/gift function   
    * @param rankCtrl to retrieve player rank info 
    * @param cropCtrl to perform buy function
    * @param playerCtrl to perform buy/gift function
    * @param loggedInPlayer the player of this inventory
    * @param giftCtrl to perform gift related functions
    * @param friendCtrl to perform gift related functions
    */     
    public void displayInventory(SeedController seedCtrl, RankController rankCtrl, CropController cropCtrl, PlayerController playerCtrl, Player loggedInPlayer, GiftController giftCtrl, FriendController friendCtrl) {
        //Set loggedInPlayer and controllers        
        this.seedCtrl = seedCtrl;
        this.rankCtrl = rankCtrl;
        this.cropCtrl = cropCtrl;
        this.playerCtrl = playerCtrl;
        this.loggedInPlayer = loggedInPlayer;
        this.giftCtrl = giftCtrl;
        this.friendCtrl = friendCtrl;
        
        System.out.println();
        System.out.println("== Farm City :: My Inventory ==");
        displayPlayerDetails();
        System.out.println("My Seeds:");
        
        ArrayList<Seed> listOfSeed = seedCtrl.retrieveSeed(loggedInPlayer);
        
        //to consolidate the list of seeds the player has    
        ArrayList<Seed> consolidatedListOfSeed = new ArrayList<Seed>();
        consolidatedListOfSeed.add(new Seed("temp", "Papaya", 0));
        consolidatedListOfSeed.add(new Seed("temp", "Watermelon", 0));
        consolidatedListOfSeed.add(new Seed("temp", "Sunflower", 0));
        consolidatedListOfSeed.add(new Seed("temp", "Pumpkin", 0));
        
        for (int i=0; i<listOfSeed.size(); i++) {
            Seed seed = listOfSeed.get(i);
            for (int a=0; a<consolidatedListOfSeed.size(); a++) {
                Seed consolidatedSeed = consolidatedListOfSeed.get(a);
                if (seed.getSeedName().equals(consolidatedSeed.getSeedName())) {
                    consolidatedSeed.setQuantity(consolidatedSeed.getQuantity() + seed.getQuantity());
                } 
            }
        }
        int counter = 1;
        for (int b=0; b<consolidatedListOfSeed.size(); b++) {
            Seed seed = consolidatedListOfSeed.get(b);
            if (seed.getQuantity() > 0) {
                System.out.println((counter) + ". " + seed.getQuantity() + " Bags of " + seed.getSeedName());
                counter++;
            }
        }      
        inputOption();       
    }

    /**
    * Displays the option for inventory menu
    */     
    public void inputOption() {
        System.out.println();
        System.out.print("[M]ain | [B]uy | [G]ift | Select choice > ");
        
        String playerChoice = "";
		char choiceLetter = 'a';
        
        do {
            //Extract player input
			Scanner sc = new Scanner(System.in);
			playerChoice = sc.next();
			
			choiceLetter = playerChoice.charAt(0);
			
            //validation
			while(playerChoice.length() != 1 || choiceLetter != 'B' && choiceLetter != 'b' && choiceLetter != 'G' && choiceLetter != 'g'
			&& choiceLetter != 'm' && choiceLetter != 'M'){
				System.out.println("\nPlease check the following areas of your input:\n");
				System.out.println("1. The length of input should be 1 character!");
				System.out.println("2. Please only enter the choice given!\n");
				
				System.out.print("[M]ain | [B]uy | [G]ift | Select choice > ");
				
				playerChoice = sc.next();
				choiceLetter = playerChoice.charAt(0);
			}
            
			switch(choiceLetter){
				case 'm':
				choiceLetter = 'M';
				break;
				case 'b':
				choiceLetter = 'B';
				break;
				case 'g':
				choiceLetter = 'G';
				break;
			}
			
            
			switch(choiceLetter) {
                case 'M':
                    return;
                case 'B':
                    buyMenu.displayBuyMenu(loggedInPlayer, cropCtrl, rankCtrl, seedCtrl, playerCtrl);
                    break;
                case 'G': 
					giftMenu.displayGiftMenu(loggedInPlayer, rankCtrl, seedCtrl, playerCtrl, giftCtrl, friendCtrl);
                    break;
                default :
                    return;
            }               
        }while(choiceLetter != 'M' && choiceLetter != 'B' && choiceLetter != 'G');   
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