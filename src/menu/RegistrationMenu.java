package menu;
import entity.*;
import dao.*;
import controller.*;
import java.util.*;

/**
* RegistrationMenu allows new user to register an account in FarmCity
*/
public class RegistrationMenu{
	private PlayerController playerCtrl;
    private LandController landCtrl;
	
   /**
    * RegistrationMenu constructor
    */
	public RegistrationMenu(){
	}

    /**
    * Display the registration menu with the required fields to register an account
    * @param playerCtrl the playerController
    * @param landCtrl the landController
    */	
	public void displayRegistration(PlayerController playerCtrl, LandController landCtrl){
        //set controllers
		this.playerCtrl = playerCtrl;
        this.landCtrl = landCtrl;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("\n== Farm City  :: Registration ==\n");
		
		//Prompt for username
		System.out.print("Enter your username > ");
		String username = sc.nextLine();
		
		//Retrieve player list
		ArrayList<Player> playerList = playerCtrl.retrieveAll();
		for(Player p: playerList){
			
			//Ensures that no repeated username will be taken up
			while(p.getUserName().equals(username)){
				System.out.println("\nError! The username has been taken.\n");
				System.out.print("Please re-enter your username > ");
				username = sc.nextLine();
			}
		}
		
		for(int i = 0; i < username.length(); i++){
			char c = username.charAt(i);
			int index = (int)c;
			
			//Ensures that all the characters of username are alphanumeric 
			while(index < 48 || (index > 58 && index < 65) || (index > 91 && index < 97)){
				System.out.println("\nError! Username should contain only alphanumeric characters.\n");
				System.out.print("Please re-enter your username > ");
				username = sc.nextLine();
				
				for(int j = 0; j < username.length(); j++){
					c = username.charAt(j);
					index = (int)c;
				}
			}
		}
		
		//Prompt for full name
		System.out.print("Enter your full name > ");
		String fullname = sc.nextLine();
		
		//Prompt for password
		System.out.print("Enter your password > ");
		String pw1 = sc.nextLine();
		
		//Prompt for password again
		System.out.print("Confirm your password > ");
		String pw2 = sc.nextLine();
		
		//Ensures that both passwords entered are the same
		while(!pw1.equals(pw2)){
			System.out.println("\nError! Password does not match.\n");
			System.out.print("Please re-enter your password > ");
			pw1 = sc.nextLine();
			
			System.out.print("Please confirm your password > ");
			pw2 = sc.nextLine();
		}
		
		//Add the new player to playerController
        playerCtrl.addPlayer(username, fullname, pw2, landCtrl);
        
        System.out.println("Hi, " + username + "! Your account is successfully created!");
	}
}