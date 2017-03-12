package dao;
import entity.*;
import java.io.*;
import java.util.*;

/**
 * GiftDAO is used to extract and update all the data from gift.csv file
 */
public class GiftDAO {
    private ArrayList<Gift> giftList;

    /**
     * Creates GiftDAO object and initialize giftList
     */    
    public GiftDAO() {
        giftList = new ArrayList<Gift>();
    }

    /**
     * Get the list of gift data from gift.csv
     * @return an ArrayList of Gift
     */    
    public ArrayList<Gift> importGiftData() {
        try {
            //Try to access gift.csv in the given directory
			File file = new File("data\\gift.csv");
            Scanner fileIn = new Scanner(file);    
            
            fileIn.skip("playerName,friendName,timeForNextGift");
            
			//Use comma as delimiter in extracting various gift info
			fileIn.useDelimiter(",|\r\n|\n");
            
            while (fileIn.hasNext()) {
                String playerName = fileIn.next().trim();
                String friendName = fileIn.next().trim();
                long timeForNextGift = fileIn.nextLong();
                
				//Create new gift based on extracted info
                Gift gift = new Gift(playerName, friendName, timeForNextGift);
				
				//Add gift to giftList
                giftList.add(gift);
            }
            
        }
        
        catch (IOException e) {
            //Specify the location of IOException
			e.printStackTrace();
        }
		
		return giftList;    
    }
 
    /**
     * Update gift.csv after changes are made
     * @param giftList an ArrayList of Gift 
     */ 
    public void updateGiftDAO(ArrayList<Gift> giftList) {
        try {
			PrintStream writer = new PrintStream(new FileOutputStream("data\\gift.csv", false));
			writer.println("playerName,friendName,timeForNextGift");
            
			for(Gift gift: giftList){
				writer.println(gift.getPlayerName() + "," + gift.getFriendName() + "," + 
				gift.getTimeForNextGift());
			}
			writer.close();
		}
			
		catch (IOException e) {
			//Specify the location of IOException
			e.printStackTrace();
		}	 
    }

}