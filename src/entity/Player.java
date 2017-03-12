package entity;

/**
 * This represents an instance of a Player.
 */
public class Player {       
    private String userName;
    private String fullName;
    private String password;
    private int gold;
    private int exp;
    private int noOfLand;
    
    
    /**
     * Creates a Player object with the specified player's username, fullName, password, gold, exp, noOfLand
     * @param userName the player's username
     * @param fullName the player's fullname
     * @param password the player's password
     * @param gold the player's password
     * @param exp the player's experience point
     * @param noOfLand the number of land the player has
     */  
    public Player (String userName, String fullName, String password, int gold, int exp, int noOfLand) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.gold = gold;
        this.exp = exp;
        this.noOfLand = noOfLand; 
    }
    
    /**
     * Gets the userName of this player
     * @return the userName of this player
     */    
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the fullName of this player
     * @return the fullName of this player
     */     
    public String getFullName() {
        return fullName;
    }

    /**
     * Gets the password of this player
     * @return the password of this player
     */     
    public String getPassword() {
        return password;
    }

    /**
     * Gets the gold of this player
     * @return the gold of this player
     */     
    public int getGold() {
        return gold;
    }    

    /**
     * Gets the experience point of this player
     * @return the experience point of this player
     */     
    public int getExp() {
        return exp;
    }

    /**
     * Gets the number of land this player has
     * @return the number of land this player has
     */     
    public int getNoOfLand() {
        return noOfLand;
    }
    
    /**
     * Sets the userName of this player
     * @param userName the userName of this player
     */     
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the fullName of this player
     * @param fullName the fullName of this player
     */    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Sets the password of this player
     * @param password the password of this player
     */    
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the gold of this player
     * @param gold the gold of this player
     */    
	public void setGold(int gold){
		this.gold = gold;
	}

    /**
     * Add gold to this player
     * @param gold the amount of gold to be added
     */	
    public void addGold(int gold) {
        this.gold += gold;
    }

    /**
     * Gets whether player has sufficient gold to deduct
     * @param gold the amount of gold to be deducted
     * @return true if there is sufficient gold; false if gold is insufficient
     */	
	public boolean deductGold(int gold){
		if(this.gold < gold){
			return false;
		}
		this.gold -= gold;
		return true;
	}

    /**
     * Sets the experience points of this player
     * @param exp the experience points to be set for this player
     */    
	public void setExp(int exp){
		this.exp = exp;
	}

    /**
     * Add experience points for this player
     * @param exp the amount of experience points to be added
     */	
    public void addExp(int exp) {
        this.exp += exp;
    }

    /**
     * Deduct the experience point this player
     * @param exp the amount of experience point to be deducted
     */	
	public void deductExp(int exp){
		this.exp -= exp;
	}

    /**
     * Sets the number of land of this player
     * @param noOfLand the number of land to be set
     */    
    public void setNoOfLand(int noOfLand) {
        this.noOfLand = noOfLand;
    }
    
}