package entity;
import java.util.*;

/**
 * This represents an instance of a Friend.
 */
public class Friend {
    private String userName;
    private ArrayList<String> friendList;
    private ArrayList<String> requestList;

    /**
     * Creates a Friend object with the specified player's username and creates a friendList and requestList for the player
     * @param userName the player's username
     */     
    public Friend(String userName) {
        this.userName = userName;
        friendList = new ArrayList<String>();
        requestList = new ArrayList<String>();
    }

    /**
     * Gets the username of this friend
     * @return the name of this friend
     */      
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the friendList of this friend
     * @return the friendList of this friend
     */      
    public ArrayList<String> getFriendList(){
        return friendList;
    }

    /**
     * Gets the requestList of this friend
     * @return the requestList of this friend
     */       
    public ArrayList<String> getRequestList(){
        return requestList;
    }
}