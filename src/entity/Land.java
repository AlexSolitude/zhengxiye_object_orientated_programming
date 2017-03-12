package entity;

/**
 * This represents an instance of a Land.
 */
public class Land{  
    private String userName;
    private int landNo;
    private String seedName;
    private long plantTime;
    private long harvestTime;
    private long wiltTime;
	
    /**
     * Creates a Land object with the specified player's username, landNo, seedName, plantTime, harvestTime and wiltTime
     * @param userName the player's username
     * @param landNo the land number
     * @param seedName the name of the seed
     * @param plantTime the time when the seed is planted
     * @param harvestTime the time when the crop can be harvest upon reaching maturity
     * @param wiltTime the time crop is wilted
     */      
	public Land(String userName, int landNo, String seedName, long plantTime, long harvestTime, long wiltTime){
		this.userName = userName;
        this.landNo = landNo;
        this.seedName = seedName;
        this.plantTime = plantTime;
        this.harvestTime = harvestTime;
        this.wiltTime = wiltTime;
	}
    
    /**
     * Gets the userName of this land
     * @return the userName of this land
     */   
	public String getUserName(){
		return userName;
	}

    /**
     * Gets the land number of this land
     * @return the land number of this land
     */       
	public int getLandNo(){
		return landNo;
	}

    /**
     * Gets the seedName of this land
     * @return the seedName of this land
     */       
	public String getSeedName(){
		return seedName;
	}

    /**
     * Gets the plant time of this land
     * @return the plant time of this land
     */       
	public long getPlantTime(){
		return plantTime;
	}

    /**
     * Gets the harvestTime of this land
     * @return the harvestTime of this land
     */       
	public long getHarvestTime(){
		return harvestTime;
	}

    /**
     * Gets the wiltTime of this land
     * @return the wiltTime of this land
     */       
	public long getWiltTime(){
		return wiltTime;
	}
    
    /**
     * Sets the userName of this land
     * @param userName the userName of this land
     */ 	
	public void setUserName(String userName){
		this.userName = userName;
	}

    /**
     * Sets the landNo of this land
     * @param landNo the landNo of this land
     */     
	public void setLandNo(int landNo){
		this.landNo = landNo;
	}

    /**
     * Sets the seedName of this land
     * @param seedName the seedName of this land
     */         
	public void setSeedName(String seedName){
		this.seedName = seedName;
	}

    /**
     * Sets the plantTime of this land
     * @param plantTime the plantTime of this land
     */     
	public void setPlantTime(long plantTime){
		this.plantTime = plantTime;
	}

    /**
     * Sets the harvestTime of this land
     * @param harvestTime the harvestTime of this land
     */     
	public void setHarvestTime(long harvestTime){
		this.harvestTime = harvestTime;
	}

    /**
     * Sets the wiltTime of this land
     * @param wiltTime the wiltTime of this land
     */     
	public void setWiltTime(long wiltTime){
		this.wiltTime = wiltTime;
	}

}