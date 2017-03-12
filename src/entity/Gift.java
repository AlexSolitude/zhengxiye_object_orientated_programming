package entity;

/**
 * This represents an instance of a Gift.
 */
public class Gift {
    private String playerName;
    private String friendName;
    private long timeForNextGift;

    /**
     * Creates a Gift object with the specified player's username, friendName and timeForNextGift
     * @param playerName the player's playerName
     * @param friendName the player's friendName
     * @param timeForNextGift the next gift time for player to send a gift to his friend
     */      
    public Gift(String playerName, String friendName, long timeForNextGift) {
        this.playerName = playerName;
        this.friendName = friendName;
        this.timeForNextGift = timeForNextGift;
    }

    /**
     * Gets the playerName of this gift
     * @return the playerName of this gift
     */      
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the friendName of this gift
     * @return the friendName of this gift
     */     
    public String getFriendName() {
        return friendName;
    }

    /**
     * Gets the time for next gift
     * @return the time for next gift
     */     
    public long getTimeForNextGift() {
        return timeForNextGift;
    }

    /**
     * Sets the playerName of this gift
     * @param playerName the playerName of this gift
     */      
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Sets the friendName of this gift
     * @param friendName the friendName of this gift
     */      
    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    /**
     * Sets the timeForNextGift 
     * @param timeForNextGift the timeForNextGift of the player to his friend
     */      
    public void setTimeForNextGift(long timeForNextGift) {
        this.timeForNextGift = timeForNextGift;
    }    
}