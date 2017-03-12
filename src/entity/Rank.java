package entity;

/**
 * This represents an instance of a Rank.
 */
public class Rank{
	private String rankName;
	private int xp;
	private int plots;

    /**
     * Creates a Rank object with the specified rankName, xp, plots
     * @param rankName The name of the rank
     * @param xp The number of Experience points required by the player to obtain the rank
     * @param plots The number of plots of land a player of a particular rank owns
     */ 	
	public Rank(String rankName, int xp, int plots){
		this.rankName = rankName;
		this.xp = xp;
		this.plots = plots;
	}

    /**
     * Gets the rankName of this rank
     * @return the rankName of this rank
     */	
	public String getRankName(){
		return rankName;
	}

    /**
     * Gets the experience point required to obtain this rank
     * @return the experience point required to obtain this rank
     */	
	public int getXp(){
		return xp;
	}

    /**
     * Gets the number of plots of land this rank can have
     * @return Gets the number of plots of land this rank can have
     */	    	
	public int getPlots(){
		return plots;
	}
	
    /**
     * Sets the rankName of this rank
     * @param anotherRankName the rankName of this rank
     */     
	public void setRankName(String anotherRankName){
		rankName = anotherRankName;
	}

    /**
     * Sets the experience points of this rank
     * @param anotherXp the experience points of this rank
     */ 	
	public void setXp(int anotherXp){
		xp = anotherXp;
	}

    /**
     * Sets the number of plots of this rank
     * @param anotherPlots the number of plots of this rank
     */ 	
	public void setPlots(int anotherPlots){
		plots = anotherPlots;
	}
}