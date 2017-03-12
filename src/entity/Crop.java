package entity;
/**
 * This represents an instance of a Crop.
 */
public class Crop {
    private String name;
    private int cost;
    private int time;
    private int xp;
    private int minYield;
    private int maxYield;
    private int salePrice;
    
    /**
     * Creates a Crop object with the specified name, cost, time, minimum and maximum yield and sale price.
     * @param name The name of the crop
     * @param cost Number of Gold coins required to purchase one bag of seed of the crop
     * @param time Time in minutes it takes for the specific seed planted to grow to maturity
     * @param xp Number of Experience Points earned for one unit of crop produced
     * @param minYield Minimum yield (in units) produced by planting one bag of seed of the given crop
     * @param maxYield Maximum yield (in units) produced by planting one bag of seed of the given crop
     * @param salePrice Number of gold coins one unit of produce gives the farmer
     */    
    public Crop(String name, int cost, int time, int xp, int minYield, int maxYield, int salePrice) {
        this.name = name;
        this.cost = cost;
        this.time = time;
        this.xp = xp;
        this.minYield = minYield;
        this.maxYield = maxYield;
        this.salePrice = salePrice;
    }
    
    /**
     * Gets the name of this crop
     * @return the name of this crop
     */    
    public String getName() {
        return name;
    }
    
    /**
     * Gets the cost of this crop
     * @return the cost of this crop
     */     
    public int getCost() {
        return cost;
    }

    /**
     * Gets the time in minutes of this crop to grow to maturity
     * @return the time in minutes of this crop to grow to maturity
     */     
    public int getTime() {
        return time;
    }

    /**
     * Gets the experience points for one unit of crop produced
     * @return the experience points for one unit of crop produced
     */     
    public int getXp() {
        return xp;
    }

    /**
     * Gets the Minimum yield (in units) produced by planting one bag of seed of this crop
     * @return the Minimum yield (in units) produced by planting one bag of seed of this crop
     */       
    public int getMinYield() {
        return minYield;
    }

    /**
     * Gets the Maximum yield (in units) produced by planting one bag of seed of this crop
     * @return the Maximum yield (in units) produced by planting one bag of seed of this crop
     */       
    public int getMaxYield() {
        return maxYield;
    }

    /**
     * Gets the Number of gold coins per unit of produce
     * @return the Number of gold coins per unit of produce
     */     
    public int getSalePrice() {
        return salePrice;
    }
    

    /**
     * Sets the name of this crop
     * @param name the crop's name
     */    
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the cost of this crop
     * @param cost the crop's cost
     */    
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Sets the time in minutes taken for the seed to grow to maturity
     * @param time the time in minutes taken for the seed to grow to maturity
     */    
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Sets the experience points earned for one unit of crop produced
     * @param xp the experience points earned for one unit of crop produced
     */     
    public void setXp(int xp) {
        this.xp = xp;
    }

    /**
     * Sets the minimum yield (in units) produced by planting one bag of seed of the given crop
     * @param minYield the minimum yield (in units) produced by planting one bag of seed of the given crop
     */        
    public void setMinYield(int minYield) {
        this.minYield = minYield;
    }

    /**
     * Sets the maximum yield (in units) produced by planting one bag of seed of the given crop
     * @param maxYield the maximum yield (in units) produced by planting one bag of seed of the given crop
     */      
    public void setMaxYield(int maxYield) {
        this.maxYield = maxYield;
    }

    /**
     * Sets the number of gold coins per unit of produce
     * @param salePrice the number of gold coins per unit of produce
     */      
    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }
    
}