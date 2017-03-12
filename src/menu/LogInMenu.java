package menu;
import entity.*;
import dao.*;
import controller.*;
import java.util.*;

/**
* LogInMenu allows player to enter the username and password in order to connect to FarmCity
*/
public class LogInMenu{
	private MainMenu mainMenu;
    
    private PlayerController playerCtrl;
    private LandController landCtrl;
    private RankController rankCtrl;
    private SeedController seedCtrl;
    private CropController cropCtrl;
    private FriendController friendCtrl;
    private GiftController giftCtrl;

    /**
    * LogInMenu constructor
    */ 	
	public LogInMenu(){
		mainMenu = new MainMenu();
	}

    /**
    * Displays the inventory menu with the list of seeds the player has
    * @param playerCtrl retrieve player on his username
    * @param landCtrl the landController
    * @param rankCtrl the rankController
    * @param seedCtrl the seedController
    * @param cropCtrl the cropController
    * @param friendCtrl the friendController
    * @param giftCtrl the giftController
    */ 	
	public void displayLogIn(PlayerController playerCtrl, LandController landCtrl, RankController rankCtrl, SeedController seedCtrl, CropController cropCtrl, FriendController friendCtrl, GiftController giftCtrl){
        //set controllers
        this.playerCtrl = playerCtrl;
        this.landCtrl = landCtrl;
        this.rankCtrl = rankCtrl;
        this.seedCtrl = seedCtrl;
        this.cropCtrl = cropCtrl;
        this.friendCtrl = friendCtrl;
        this.giftCtrl = giftCtrl;
        
		Scanner sc = new Scanner(System.in);
        
		//Display login menu
        System.out.println("\n== Farm City  :: Login ==\n");
        
		//Prompt for username
		System.out.print("Enter your username > ");
        String userName = sc.nextLine();
		
		//Prompt for password
        System.out.print("Enter your password > ");
        String password = sc.nextLine();
        
		//Retrieve specific player based on username
		Player player = playerCtrl.retrieve(userName);
        
		//Player does not exist
		if (player == null) {
            System.out.println("Incorrect username!");
        } else {
			//Verify player's entered password with his correct password
			if (!player.getPassword().equals(password)) {
                System.out.println("Incorrect password!");
            } else {
                System.out.println("Sucessfully Login");                
				mainMenu.readMenuOption(player, playerCtrl, landCtrl, rankCtrl, seedCtrl, cropCtrl, friendCtrl, giftCtrl);
            }
        }
	}
}