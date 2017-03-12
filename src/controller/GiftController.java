package controller;
import entity.*;
import dao.*;
import java.util.*;

/**
 * GiftController is the controller class for Gift function
 */
public class GiftController {
    private ArrayList<Gift> giftList;
    private GiftDAO giftDAO;

    /**
     * Creates GiftController object and initialize giftList with gift data from GiftDAO
     */     
    public GiftController() {
        giftDAO = new GiftDAO();
        giftList = giftDAO.importGiftData();
    }

    /**
     * Get the list of all gift
     * @return an ArrayList of Gift
     */   
    public ArrayList<Gift> retrieveAll() {
        return giftList;
    }

    /**
     * Check if the player is sending gift to himself
     * @param player the player object
     * @param friend the player object of player's friend
     * @return true if player is not sending gift to himself; false if player is sending gift to himself
     */     
    public boolean checkIfGivingSelf(Player player, Player friend) {
        String playerName = player.getUserName();
        String friendName = friend.getUserName();
        if (playerName.equals(friendName)) {
            return false; 
        }
        return true; 
    }

    /**
     * Check if the gift the player is sending to is his friend
     * @param player the player object
     * @param friend the player object of player's friend
     * @param friendCtrl friendController to access player's friendList
     * @return true if friend is in player's friendList; false if friend is not in the player's friendList
     */    
    public boolean checkIfInFriendList(Player player, Player friend, FriendController friendCtrl) {
        String playerName = player.getUserName();
        String friendName = friend.getUserName();
        
        ArrayList<Friend> friendList = friendCtrl.retrieveAll();
        
        for (int i=0; i<friendList.size(); i++) {
            Friend friendinList = friendList.get(i);
            String friendListName = friendinList.getUserName();
            if (playerName.equals(friendListName)) {
                //retrieve player's friendList
                ArrayList<String> listOfFriends = friendinList.getFriendList();
                for (int a=0; a<listOfFriends.size(); a++) {
                    //check if friend is in player's friendList
                    String nameOfFriend = listOfFriends.get(a);
                    if (friendName.equals(nameOfFriend)) {
                        return true; 
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check if the player's seedList has the seed he wants to send as gift
     * @param seedCtrl seedController to access the player's seedList
     * @param player the player object
     * @param choice the choice of seed the player wish to send
     * @return true if seed is in seedList (can send gift); false if seed is not in seedList (cannot send gift)
     */     
    public boolean checkIfHaveGift(SeedController seedCtrl, Player player, int choice) {
        
        String nameOfSeed = "";
        
        if (choice == 1) {
            nameOfSeed = "Papaya";
        } else if (choice == 2) {
            nameOfSeed = "Pumpkin";
        } else if (choice == 3) {
            nameOfSeed = "Sunflower";
        } else if (choice == 4) {
            nameOfSeed = "Watermelon";
        }
        
        //retrieve player seedList
        ArrayList<Seed> playerSeedList = seedCtrl.retrieveSeed(player);
        for (int i=0; i<playerSeedList.size(); i++) {
            Seed seed = playerSeedList.get(i);
            if (seed.getUserName().equals(player.getUserName()) && seed.getSeedName().equals(nameOfSeed)) {
                if (seed.getQuantity() > 0) {
                    return true; 
                }
            }
        }
        return false;
    }
    
    /**
     * Check if player is able to send a gift to his friend for that day
     * @param player the player object
     * @param friend the player object of player's friend
     * @return true if player can send gift to his friend; false if player cannot send gift to his friend
     */     
    public boolean checkIfCanGift(Player player, Player friend) {
        for (int i = 0; i<giftList.size(); i++) {
            Gift gift = giftList.get(i);
            String giftFriendName = gift.getFriendName();
            String giftPlayerName = gift.getPlayerName();
            if (giftPlayerName.equals(player.getUserName()) && giftFriendName.equals(friend.getUserName())) {
                //comparing the time the friend can get a next gift with the current time
                if (gift.getTimeForNextGift() > Calendar.getInstance().getTime().getTime()) { 
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Check if player has hit the daily limit of sending 5 gifts 
     * @param player the player object
     * @return true if player has not meet the daily limit; false if player has hit the daily limit
     */      
    public boolean checkDailyLimit(Player player) {
        int counter = 0;
        
        Calendar timeLimit = Calendar.getInstance();
        timeLimit.add(Calendar.DAY_OF_MONTH, +1);
        timeLimit.set(Calendar.HOUR_OF_DAY, 0);
        timeLimit.set(Calendar.MINUTE, 0);
        timeLimit.set(Calendar.SECOND, 0); 
        long timeLimitInLong = timeLimit.getTime().getTime(); 
        
        for (int i=0; i<giftList.size(); i++) {
            Gift gift = giftList.get(i);
            long giftTime = gift.getTimeForNextGift();
            
            if (gift.getPlayerName().equals(player.getUserName())) {
                System.out.println(gift.getFriendName() + "," + (giftTime<timeLimitInLong));
                
                if (giftTime < timeLimitInLong) {
                    counter++;
                }
            }
        }
        
        if (counter >= 5) {
            return false; 
        }
        return true; 
    }

    /**
     * Sends a gift to player's friend
     * @param seedCtrl seedController to process add/remove seed and access player's seedList
     * @param choice the choice of seed to be sent
     * @param player the player object
     * @param friend the player object of player's friend
     */      
    public void executeGift(SeedController seedCtrl, int choice, Player player, Player friend) {
        String playerName = player.getUserName();
        String friendName = friend.getUserName();
        
        Calendar timeForNextGift = Calendar.getInstance();
        timeForNextGift.add(Calendar.DAY_OF_MONTH, +1);
        timeForNextGift.set(Calendar.HOUR_OF_DAY, 0);
        timeForNextGift.set(Calendar.MINUTE, 0);
        timeForNextGift.set(Calendar.SECOND, 0);     
        
        long timeForNextGiftInLong = timeForNextGift.getTime().getTime(); 
        //create gift object and set timeForNextGift
        Gift gift = new Gift(playerName, friendName, timeForNextGiftInLong);
        giftList.add(gift);
        giftDAO.updateGiftDAO(giftList);
        
        ArrayList<Seed> wholeList = seedCtrl.retrieveAll();
        
        if (choice == 1) {
            Seed papayaSeed = new Seed(playerName, "Papaya", 0);
            seedCtrl.removeSeed(wholeList, papayaSeed, seedCtrl);
            seedCtrl.addSeed(friendName, "Papaya", 1, seedCtrl);
        } else if (choice == 2) {
            Seed pumpkinSeed = new Seed(playerName, "Pumpkin", 0);
            seedCtrl.removeSeed(wholeList, pumpkinSeed,seedCtrl);   
            seedCtrl.addSeed(friendName, "Pumpkin", 1, seedCtrl);
        } else if (choice == 3) {
            Seed sunflowerSeed = new Seed(playerName, "Sunflower", 0);
            seedCtrl.removeSeed(wholeList, sunflowerSeed, seedCtrl);
            seedCtrl.addSeed(friendName, "Sunflower", 1, seedCtrl);
        } else if (choice == 4) {
            Seed watermelonSeed = new Seed(playerName, "Watermelon", 0);
            seedCtrl.removeSeed(wholeList, watermelonSeed, seedCtrl);      
            seedCtrl.addSeed(friendName, "Watermelon", 1, seedCtrl);
        }
        
    }
}