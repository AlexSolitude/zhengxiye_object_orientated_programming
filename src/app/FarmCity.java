package app;
import controller.*;
import menu.WelcomeMenu;

/**
* FarmCity application will be started by running the welcome menu
*/
public class FarmCity{

    /**
    * Main method
    */
	public static void main(String[] args){
    
        //creating the welcomeMenu
		WelcomeMenu welcomeMenu = new WelcomeMenu();
        
        //creating all the controllers
        PlayerController playerCtrl = new PlayerController();
        LandController landCtrl = new LandController();
        RankController rankCtrl = new RankController();
        SeedController seedCtrl = new SeedController();
        CropController cropCtrl = new CropController();
        FriendController friendCtrl = new FriendController();
        GiftController giftCtrl = new GiftController();
		
        //calling the method from welcomeMenu
		welcomeMenu.readWelcomeOption(playerCtrl, landCtrl, rankCtrl, seedCtrl, cropCtrl, friendCtrl, giftCtrl);
	}
}