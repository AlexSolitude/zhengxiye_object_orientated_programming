package entity;

/**
 * This represents an instance of a Seed.
 */
public class Seed {
    private String userName;
    private String seedName;
    private int quantity;
    
    /**
     * Creates a Seed object with the specified player's userName, seedName, quantity
     * @param userName the userName of the player
     * @param seedName the name of the seed
     * @param quantity the quantity of the seed
     */
    public Seed(String userName, String seedName, int quantity) {
        this.userName = userName;
        this.seedName = seedName;
        this.quantity = quantity;
    }
    
    /**
     * Gets the player's userName of this seed
     * @return the player's userName of this seed
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the seedName of this seed
     * @return the seedName of this seed
     */    
    public String getSeedName() {
        return seedName;
    }

    /**
     * Gets the quantity of this seed
     * @return the quantity of this seed
     */    
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Sets the player's userName of this seed
     * @param userName the player's userName of this seed
     */  
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the seedName of this seed
     * @param seedName the seedName of this seed
     */     
    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    /**
     * Sets the quantity of this seed
     * @param quantity the quantity of this seed
     */     
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * deduct the quantity of this seed
     */         
    public void deductQuantity() {
        quantity--;
    }

    /**
     * Add the quantity of this seed
     * @param num the quantity to be added
     */ 	
	public void addQuantity(int num){
		quantity += num;
	}
}