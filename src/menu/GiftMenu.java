package menu;
import entity.*;
import dao.*;
import controller.*;
import java.util.*;

/**
* GiftMenu allows the player to send up to 5 free gifts(seeds) per day to his friend
*/
public class GiftMenu {
    private Player loggedInPlayer;
    private RankController rankCtrl; 
    private SeedController seedCtrl;
    private PlayerController playerCtrl;
    private GiftController giftCtrl;
    private FriendController friendCtrl;

    /**
    * GiftMenu constructor
    */    
    public GiftMenu() {
    
    }

    /**
    * Displays the gift menu with the gift available to send
    * @param loggedInPlayer the player of this farm
    * @param rankCtrl to retrieve player rank info
    * @param seedCtrl to check if player has seeds to send  
    * @param playerCtrl to retrieve player's friend player object
    * @param giftCtrl to retrieve/perform gift related functions
    * @param friendCtrl to check if the sendee is a friend of player
    */    
    public void displayGiftMenu(Player loggedInPlayer, RankController rankCtrl, SeedController seedCtrl, PlayerController playerCtrl, GiftController giftCtrl, FriendController friendCtrl) {
        //Set loggedInPlayer and controllers        
        this.loggedInPlayer = loggedInPlayer;
        this.rankCtrl = rankCtrl;
        this.seedCtrl = seedCtrl;
        this.playerCtrl = playerCtrl;
        this.giftCtrl = giftCtrl;
        this.friendCtrl = friendCtrl;
        
        System.out.println();
        System.out.println("== Farm City :: Send a Gift ==");
        displayPlayerDetails();
        System.out.println("Gifts Available: ");
        System.out.println("1. 1 Bag of Papaya Seeds");
        System.out.println("2. 1 Bag of Pumpkin Seeds");
        System.out.println("3. 1 Bag of Sunflower Seeds");
        System.out.println("4. 1 Bag of Watermelon Seeds");
        System.out.print("[M]ain | Select choice > ");
        
        Scanner sc = new Scanner(System.in);
        String input = "";
        char inputChar = '-';
        int choice = 0;
        String friendNames = "";
        input = sc.next(); //scan for gift option
        
        //validation
        while(!(input.equals("m")) && !(input.equals("M")) && !(input.equals("1")) && !(input.equals("2")) && !(input.equals("3")) && !(input.equals("4"))) {
            System.out.println("Error! The input has to be a number between 1 to 4 or M.");
            System.out.print("Please re-enter your choice: ");
            input = sc.next();
        }
        
        inputChar = input.charAt(0);
		if (inputChar == 'm' || inputChar == 'M') {
            return;
        } else {
            choice = Integer.parseInt(input);
        }
        sc.nextLine();
		
        System.out.print("Send to> ");
        friendNames = sc.nextLine(); // scan for friend name
        String friendName = "";
        Scanner sc1 = new Scanner(friendNames);
        sc1.useDelimiter(",");
        while (sc1.hasNext()) {
            friendName = sc1.next().trim();
        
            Player friend = playerCtrl.retrieve(friendName); //get the specific friend object
            
            if (friend == null) {
                System.out.println("You can only give gifts to valid users and " + friendName + " is not a valid user.");
            } else {
            
                //call check method in GiftController to check if giving to himself                
                boolean himselfStatus = giftCtrl.checkIfGivingSelf(loggedInPlayer, friend);
                //call check method in GiftController to check if friend is in friend list
                boolean friendListStatus = giftCtrl.checkIfInFriendList(loggedInPlayer, friend, friendCtrl);
                //call check method in GiftController to check if there is such an existing gift.
                boolean giftAvailablityStatus = giftCtrl.checkIfHaveGift(seedCtrl, loggedInPlayer, choice);
                //call check method in GiftController to check if can gift.
                boolean giftStatus = giftCtrl.checkIfCanGift(loggedInPlayer, friend);
                //call check method in GiftController to check whether have met daily limit.
                boolean dailyLimitStatus = giftCtrl.checkDailyLimit(loggedInPlayer);
                
                if (himselfStatus == false) {
                    System.out.println("You cannot give a gift to yourself.");
                } else if (friendListStatus == false) {
                    System.out.println("You can only give gifts to your friends and " + friendName + " is not one of your friends.");
                } else if (giftAvailablityStatus == false) {
                    System.out.println("You do not have the selected seed.");
                } else if (giftStatus == false) {
                    System.out.println("You have given " + friendName + " a gift today, please wait till the next day to give again.");
                } else if (dailyLimitStatus == false) {
                    System.out.println("You have reached your daily limit of 5 gifts.");
                    return; 
                } else {
                    giftCtrl.executeGift(seedCtrl, choice, loggedInPlayer, friend);
                    System.out.println("Gift sent to " + friend.getUserName());
                }  
            }
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