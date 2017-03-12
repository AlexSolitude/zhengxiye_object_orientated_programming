package controller;
import entity.*;
import dao.*;
import java.util.*;

/**
 * FriendController is the controller class for My Friend function
 */
public class FriendController {
    private ArrayList<Friend> fList;
    private FriendDAO friendDAO;

    /**
     * Creates FriendController object and initialize fList with friend data from FriendDAO
     */    
    public FriendController() {
        friendDAO = new FriendDAO();
        fList = friendDAO.importFriendData();
    }

    /**
     * Get the list of all friend
     * @return an ArrayList of Friend
     */    
    public ArrayList<Friend> retrieveAll(){
        return fList;
    }

    /**
     * Accepts a friend request
     * @param username the username of the player
     * @param requestName the username of the requester
     * @param friendList the friendList of the player
     * @param requestList the requestList of the player
     */    
    public void acceptFriend(String username, String requestName, ArrayList<String> friendList, ArrayList<String> requestList) {
        //add friend to player friendlist & remove request from request list
        friendList.add(requestName);
        requestList.remove(requestName);
        
        //add player as friend of the requester
        boolean isUnique = true;
        for(int i = 0; i<fList.size(); i++) {
            Friend friend = fList.get(i);
            //check if requester friend object have been initialised
            if(friend.getUserName().equals(requestName)) {
                isUnique = false;
                ArrayList<String> requesterFriendList = friend.getFriendList();
                requesterFriendList.add(username);
            }
        }
        if (isUnique) { //create friend object for requester if not found in fList and proceed to add player as friend
            Friend requester = new Friend(requestName);
            ArrayList<String> requesterFriendList = requester.getFriendList();
            requesterFriendList.add(username);
            fList.add(requester);
        }
  
        friendDAO.updateFriendDAO(fList);        
    }

    /**
     * Rejects a friend request
     * @param requestName the username of the requester
     * @param requestList the requestList of the player
     */     
    public void rejectFriend(String requestName, ArrayList<String> requestList) {
        //remove request from player request list 
        requestList.remove(requestName);
        friendDAO.updateFriendDAO(fList);
    }

    /**
     * Remove a friend from the player friendList
     * @param username the username of the player
     * @param friendName the username of the friend
     * @param friendList the friendList of the player
     */     
    public void removeFriend(String username, String friendName, ArrayList<String> friendList) {
        //remove friend from player friendlist
        friendList.remove(friendName);
        
        //remove player from removed friend's friendlist
        for(int i = 0; i<fList.size(); i++) {
            Friend friend = fList.get(i);
            if(friend.getUserName().equals(friendName)) {
                ArrayList<String> removedFriendList = friend.getFriendList();
                removedFriendList.remove(username);
            }
        }   
        
        friendDAO.updateFriendDAO(fList);
    }

    /**
     * Sends a friend request
     * @param requestName the username of the requestee
     * @param username the username of the player
     */     
    public void requestFriend(String requestName, String username) {
        boolean isUnique = true;
        //create first friend object if fList is empty and proceed to send friend request
        if(fList.size() == 0) {
            Friend firstFriend = new Friend(requestName);
            ArrayList<String> firstRequestList = firstFriend.getRequestList();
            firstRequestList.add(username);
            fList.add(firstFriend);
        } else { //send friend request if flist size > 0
            for(int i = 0; i<fList.size(); i++) {
                Friend friend = fList.get(i);
                if(friend.getUserName().equals(requestName)) {
                    //check if requestee friend object have been initialised
                    isUnique = false;
                    ArrayList<String> requestList = friend.getRequestList();
                    requestList.add(username);
                }                  
            }
            if(isUnique) { //create friend object for requestee if requestee not found in fList and proceed to send friend request
                    Friend uniqueFriend = new Friend(requestName);
                    ArrayList<String> uniqueRequestList = uniqueFriend.getRequestList();
                    uniqueRequestList.add(username);
                    fList.add(uniqueFriend);
                }
        }    
        friendDAO.updateFriendDAO(fList);
    }
}