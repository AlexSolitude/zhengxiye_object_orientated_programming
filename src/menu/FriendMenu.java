package menu;
import entity.*;
import dao.*;
import controller.*;
import java.util.*;
import java.util.HashMap;

/**
* FarmMenu allows the player to accept and reject friend request, unfriend and send friend request.
*/
public class FriendMenu{
    private Player loggedInPlayer;
    private FriendController friendCtrl;
    private PlayerController playerCtrl;

    /**
    * FriendMenu constructor
    */    
    public FriendMenu() {
        
    }
    
    /**
    * Displays the friend menu with the player's friend information
    * @param player the player of this farm
    * @param friendCtrl to access friendController and perform friend related functions e.g(handling of accept/reject request)
    * @param playerCtrl to retrieve the list of players
    */    
    public void displayFriendMenu(Player player, FriendController friendCtrl, PlayerController playerCtrl) {
        //Set loggedInPlayer and controllers
        loggedInPlayer = player;
        this.friendCtrl = friendCtrl;
        this.playerCtrl = playerCtrl;
        
        //print my friend menu
        System.out.println("\n== Farm City :: My Friend ==");
        System.out.println("Welcome, " + loggedInPlayer.getFullName() + "!");
        
        //retrieve friend outer arraylist
        ArrayList<Friend> fList = friendCtrl.retrieveAll();
        ArrayList<String> friendList = new ArrayList<String>();
        ArrayList<String> requestList = new ArrayList<String>();
        
        System.out.println();
                
        //get player's friend and request list
        for(int i = 0; i<fList.size(); i++) {
            Friend friend = fList.get(i);
            String username = friend.getUserName();
            
            if(username.equals(player.getUserName())){
                friendList = friend.getFriendList();
                requestList = friend.getRequestList();
            }            
        }    
        
        System.out.println("My Friends:");
        
        //store friends in hashmap with respective id
        HashMap<Integer,String> friendMap = new HashMap<Integer,String>();
        
        //print friend list
        if(friendList.size() == 0) {
            System.out.println("You have no friends!");         
        } else {
            for(int j = 0; j<friendList.size(); j++) {
                int friendID = (j+1);
                String friendName = friendList.get(j);
                System.out.println(friendID + ". " + friendName);
                friendMap.put(friendID, friendName);
            }
        }    

        System.out.println();
        
        System.out.println("My Requests:");
        
        //store requests in hashmap with respective id
        HashMap<Integer,String> requestMap = new HashMap<Integer,String>();
        
        //print request list
        if(requestList.size() == 0) {
            System.out.println("You have no friend request!");           
        } else {
            for(int k = 0; k<requestList.size(); k++) {
                int requestID = friendList.size()+(k+1);
                String requestName = requestList.get(k);
                System.out.println(requestID + ". " + requestName);
                requestMap.put(requestID, requestName);
            }
        }    
        
        //display friend operation options
        String playerChoice = "";
		char choiceLetter = 'a';
        int choiceNumber = 0;
        do{
            boolean check = false;
            Scanner sc = new Scanner(System.in);
            try { 
                System.out.print("[M]ain | [U]nfriend | re[Q]uest | [A]ccept | [R]eject > ");
                playerChoice = sc.nextLine().toUpperCase();
                choiceLetter = playerChoice.charAt(0);
            
                //validate playerchoice to see if there's any invalid character. e.g ~!@#$%^&*()-+
                if(choiceLetter == 'U' || choiceLetter == 'A' || choiceLetter == 'R'){
                    String strOfChoiceNumber = playerChoice.substring(1); 
                    choiceNumber = Integer.parseInt(strOfChoiceNumber);
                } 
            }catch(NumberFormatException nfe) {
                check = true;          
            }
            
            //validate playerchoice    
            while(check || playerChoice.length() == 0 ||  (playerChoice.charAt(0) != 'Q' && playerChoice.charAt(0) != 'A' && playerChoice.charAt(0) != 'U' &&
                playerChoice.charAt(0) != 'R' && playerChoice.charAt(0) != 'M') || 
                (playerChoice.charAt(0) == 'Q' && playerChoice.length() > 1) ||
                (playerChoice.charAt(0) == 'A' && playerChoice.length() == 1) || 
                (playerChoice.charAt(0) == 'U' && playerChoice.length() == 1) || 
                (playerChoice.charAt(0) == 'R' && playerChoice.length() == 1)
            ){  
                check = false;
                
				System.out.println("Wrong input! Please follow the following format:");
                System.out.println("To remove a friend, please enter: [U][Friend Number]");
                System.out.println("To send a friend request, please enter: [Q]");
                System.out.println("To accept a friend request, please enter: [A][Request Number]");
                System.out.println("To reject a friend request, please enter: [R][Request Number]\n");
                               
				try { 
                    System.out.print("[M]ain | [U]nfriend | re[Q]uest | [A]ccept | [R]eject > ");
                    playerChoice = sc.nextLine().toUpperCase();
                    choiceLetter = playerChoice.charAt(0);
            
                    //validate playerchoice to see if there's any invalid character. e.g ~!@#$%^&*()-+
                    if(choiceLetter == 'U' || choiceLetter == 'A' || choiceLetter == 'R'){
                        String strOfChoiceNumber = playerChoice.substring(1); 
                        choiceNumber = Integer.parseInt(strOfChoiceNumber);
                    } 
                }catch(NumberFormatException nfe) {
                    check = true;          
                }
			}
            choiceLetter = playerChoice.charAt(0);
            
            switch(choiceLetter) {
                case 'M':
                    //return to main menu
                    return;
                    
                case 'U':
                    //validation
                    if(choiceNumber > friendList.size() || friendList.size() == 0 || choiceNumber == 0) {
                        System.out.println("Invalid choice number!");                                                                                                  
                    } else { //proceed to remove friend if choice number is valid                       
                        friendCtrl.removeFriend(player.getUserName(), friendMap.get(choiceNumber), friendList);
                        System.out.println(friendMap.get(choiceNumber) + " is no longer your friend.");
                    }
                    //return to friend menu
                    displayFriendMenu(player, friendCtrl, playerCtrl);                   
                    break;
                    
                case 'Q':                    
                    System.out.print("Enter username > ");
                    String newRequest = sc.nextLine();
                    
                    boolean isFriend = false;
                    //check if request username is already a friend
                    for(int z = 0; z<friendList.size(); z++) {
                        String friendName = friendList.get(z);
                        if(newRequest.equals(friendName)) {
                            isFriend = true;                                                      
                        }
                    }
                    //check if request username is a request in player's request list
                    boolean isRequest = false;
                    for(int y = 0; y<requestList.size(); y++) {
                        String requestName = requestList.get(y);
                        if(newRequest.equals(requestName)) {
                            isRequest = true;                                                      
                        }
                    }
                    
                    //check if the same request has been sent twice 
                    boolean isTwice = false;
                    for(int s = 0; s<fList.size(); s++) {
                        Friend friend = fList.get(s);
                        if(friend.getUserName().equals(newRequest)) {
                            ArrayList<String> requesteeRequestList = friend.getRequestList();
                            for(int x = 0; x<requesteeRequestList.size(); x++){
                                String requester = requesteeRequestList.get(x);
                                if(player.getUserName().equals(requester)) {
                                    isTwice = true;
                                }
                            }
                        }
                    }
                
                    //check if the request username is a registered player in farmcity.
                    Player requestPlayer = playerCtrl.retrieve(newRequest);
                    
                    //print error message if above validation fails
                    if(requestPlayer == null) {
                        System.out.println("Please enter a valid username.");
                    } else if (newRequest.equals(player.getUserName())) {
                        System.out.println("You cannot add yourself as friend.");  
                    } else if (isFriend) {
                        System.out.println(newRequest + " is already your friend."); 
                    } else if (isRequest) {
                        System.out.println("Please accept the friend request from " + newRequest + ".");                        
                    } else if (isTwice) {
                        System.out.println("You have already sent a friend request to " + newRequest + ".");                       
                    }        
                    else{ //proceed to send request if no validation error
                        friendCtrl.requestFriend(newRequest, player.getUserName());
                        System.out.println("A friend request is sent to " + newRequest + ".");                        
                    }
                    
                    //return to friend menu
                    displayFriendMenu(player, friendCtrl, playerCtrl);
                    break;
                    
                case 'A':
                    //validation 
                    if(choiceNumber > (friendList.size() + requestList.size()) || choiceNumber <= friendList.size() || requestList.size() == 0 || choiceNumber == 0) {                      
                        System.out.println("Invalid choice number!");                       
                    } else {   //proceed to accept friend if choice is valid                
                        friendCtrl.acceptFriend(player.getUserName(), requestMap.get(choiceNumber), friendList, requestList);
                        System.out.println(requestMap.get(choiceNumber) + " is now your friend.");
                    }
                    //return to friend menu
                    displayFriendMenu(player, friendCtrl, playerCtrl);
                    break;
        
                case 'R':
                    //validation
                    if(choiceNumber > (friendList.size() + requestList.size()) || choiceNumber <= friendList.size() || requestList.size() == 0 || choiceNumber == 0) {
                        System.out.println("Invalid choice number!");                       
                    } else {    //proceed to reject request if choice is valid
                        friendCtrl.rejectFriend(requestMap.get(choiceNumber), requestList);
                        System.out.println("You have reject the request from " + requestMap.get(choiceNumber) + ".");
                    }
                    //return to friend menu
                    displayFriendMenu(player, friendCtrl, playerCtrl);
                    break;                    
                default:
                    displayFriendMenu(player, friendCtrl, playerCtrl);              
            }           
        }while(choiceLetter != 'M' && choiceLetter != 'U' && choiceLetter != 'Q' && choiceLetter != 'A' && choiceLetter != 'R');              
    }
}