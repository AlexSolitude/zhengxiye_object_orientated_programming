package menu;
import entity.*;
import dao.*;
import controller.*;
import java.util.*;

/**
* MainMenu allows player to select My Friend, My Farm, My Inventory or Logout options
*/
public class MainMenu {
    private Player loggedInPlayer;
    private FarmMenu farmMenu;
    private InventoryMenu inventoryMenu;
    private FriendMenu friendMenu;
    
    private PlayerController playerCtrl;
    private LandController landCtrl;
    private RankController rankCtrl;
    private SeedController seedCtrl;
    private CropController cropCtrl;
    private FriendController friendCtrl;
    private GiftController giftCtrl;

    /**
    * MainMenu constructor
    */    
    public MainMenu(){
        farmMenu = new FarmMenu();
        inventoryMenu = new InventoryMenu();
        friendMenu = new FriendMenu();
    }   

    /**
    * Display the main menu
    * @param loggedInPlayer the player of this farm
    */    
    public void displayMainMenu(Player loggedInPlayer) {

        System.out.println("\n= Farm City :: Main Menu =\nWelcome, " + loggedInPlayer.getFullName() + "!\n");
		System.out.println("1. My Friends");
		System.out.println("2. My Farm");
		System.out.println("3. My Inventory");
        System.out.println("4. Logout");        
		System.out.print("Enter your choice > ");
    }

    /**
    * Reads the main menu option selected by player
    * @param player the player of this farm
    * @param playerCtrl the playerController
    * @param landCtrl the landController
    * @param rankCtrl the rankController
    * @param seedCtrl the seedController
    * @param cropCtrl the cropController
    * @param friendCtrl the friendController
    * @param giftCtrl the giftController
    */ 	    
     public void readMenuOption(Player player, PlayerController playerCtrl, LandController landCtrl, RankController rankCtrl, SeedController seedCtrl, CropController cropCtrl, FriendController friendCtrl, GiftController giftCtrl) {
        //set loggedInPlayer and controllers
        loggedInPlayer = player;        
        this.playerCtrl = playerCtrl;
        this.landCtrl = landCtrl;
        this.rankCtrl = rankCtrl;
        this.seedCtrl = seedCtrl;
        this.cropCtrl = cropCtrl;
        this.friendCtrl = friendCtrl;
        this.giftCtrl = giftCtrl;
        
		String userChoice = "";
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            //Display functions menu               
			displayMainMenu(loggedInPlayer);
            
			//Extract player input for functions menu
			
			//validation starts here: 
			userChoice = sc.next();
			
			while(!(userChoice.equals("1")) && !(userChoice.equals("2")) && !(userChoice.equals("3")) && !(userChoice.equals("4"))){
				System.out.print("\nError! The input has to be a number between 1 to 4!\nPlease re-enter your choice: ");
				userChoice = sc.next();
			}
			
			choice = Integer.parseInt(userChoice);
			//validation ends here: 

            switch(choice) {
                case 1:     
                    //perform friend function
					friendMenu.displayFriendMenu(loggedInPlayer, friendCtrl, playerCtrl);
                    break;
                case 2:
                    //Perform farm function
                    farmMenu.displayFarmMenu(loggedInPlayer, playerCtrl, landCtrl, rankCtrl, seedCtrl, cropCtrl);	   
                    break;
                case 3:
                    //Perform inventory function
					inventoryMenu.displayInventory(seedCtrl, rankCtrl, cropCtrl, playerCtrl, loggedInPlayer, giftCtrl, friendCtrl);					
                    break;
                case 4:
                    //Display the welcome page again
                    break;
                default:
                    
					//If player's input is not within the range of 1 to 4, goes back to functions menu
					System.out.println("Please enter a choice between 1 to 4!");  
                    readMenuOption(loggedInPlayer, playerCtrl, landCtrl, rankCtrl, seedCtrl, cropCtrl, friendCtrl, giftCtrl);  
            }               
        }while(choice != 4);        
        
    }    

}