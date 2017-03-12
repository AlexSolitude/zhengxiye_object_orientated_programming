package menu;
import entity.*;
import dao.*;
import controller.*;
import java.util.*;

/**
* WelcomeMenu allows user to register, login or exit the FarmCity application.
*/
public class WelcomeMenu{
	
    private RegistrationMenu registrationMenu;
	private LogInMenu loginMenu;
    
    private PlayerController playerCtrl;
    private LandController landCtrl;
    private RankController rankCtrl;
    private SeedController seedCtrl;
    private CropController cropCtrl;
    private FriendController friendCtrl;
    private GiftController giftCtrl;

   /**
    * WelcomeMenu constructor
    */	
	public WelcomeMenu(){
		registrationMenu = new RegistrationMenu();
		loginMenu = new LogInMenu();
	}
    
    /**
    * Display the WelcomeMenu
    */	
	public void displayWelcome(){
		System.out.println("\n= Farm City :: Welcome =\nHi,");
		System.out.println("1. Register");
		System.out.println("2. Login");
		System.out.println("3. Exit");
		System.out.print("Enter your choice > ");

	}

    /**
    * Reads the welcome menu option selected by the user
    * @param playerCtrl the playerController
    * @param landCtrl the landController
    * @param rankCtrl the rankController
    * @param seedCtrl the seedController
    * @param cropCtrl the cropController
    * @param friendCtrl the friendController
    * @param giftCtrl the giftController
    */ 		
	public void readWelcomeOption(PlayerController playerCtrl, LandController landCtrl, RankController rankCtrl, SeedController seedCtrl, CropController cropCtrl, FriendController friendCtrl, GiftController giftCtrl){
		//set controllers
        this.playerCtrl = playerCtrl;
        this.landCtrl = landCtrl;
        this.rankCtrl = rankCtrl;
        this.seedCtrl = seedCtrl;
        this.cropCtrl = cropCtrl;
        this.friendCtrl = friendCtrl;
        this.giftCtrl = giftCtrl;
        
        Scanner sc = new Scanner(System.in);
		String userChoice = "";
		int choice = 0;
		do {
            //Display welcome page
			displayWelcome();
			
			//Extract user input for the welcome page
			
			//validation starts here:
			userChoice = sc.next();
			
			while(!(userChoice.equals("1")) && !(userChoice.equals("2")) && !(userChoice.equals("3"))){
				System.out.print("\nError! The input has to be a number between 1 to 3!\nPlease re-enter your choice: ");
				userChoice = sc.next();
			}
			
			choice = Integer.parseInt(userChoice);
			//validation ends here:
			
			switch (choice) {
				case 1 :
					//Perform registration function
					registrationMenu.displayRegistration(playerCtrl, landCtrl);
					break;
				case 2 :
					//Peform login function. Upon successful login, loggedInPlayer changes from null to logged in player
					loginMenu.displayLogIn(playerCtrl, landCtrl, rankCtrl, seedCtrl, cropCtrl, friendCtrl, giftCtrl); 
					break;
				case 3 :
                    //Exit program
					System.exit(0);
					break;
                    
				default :
					//Ensure input is from 1 to 3
					System.out.println("Enter a choice between 1 to 3 ");
			}           
        } while (choice != 3);
	}
}