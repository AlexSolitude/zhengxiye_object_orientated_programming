package dao;
import entity.*;
import java.io.*;
import java.util.*;

/**
 * LandDAO is used to extract and update all the data from land.csv file
 */
public class LandDAO {
    private ArrayList<Land> landList;

    /**
     * Creates LandDAO object and initialize landList
     */    
    public LandDAO() {
        landList = new ArrayList<Land>();
    }

    /**
     * Get the list of land data from land.csv
     * @return an ArrayList of Land
     */    
    public ArrayList<Land> importLandData() {
        try {
            //Try to access land.csv in the given directory
			File file = new File("data\\land.csv");
            Scanner fileIn = new Scanner(file);           
            
            fileIn.skip("userName,landNo,seedName,plantTime,harvestTime,wiltTime");
            
			//Use comma as delimiter in extracting various land info
			fileIn.useDelimiter(",|\r\n|\n");
            
            while (fileIn.hasNext()) {
                String userName = fileIn.next().trim();
                int landNo = fileIn.nextInt();
                String seedName = fileIn.next().trim();
                long plantTime = fileIn.nextLong();
                long harvestTime = fileIn.nextLong();
                long wiltTime = fileIn.nextLong();
                
				//Create new land based on extracted info
                Land land = new Land(userName, landNo, seedName, plantTime, harvestTime, wiltTime);
				
				//Add land to landlist
                landList.add(land);
            }
            
        }
        
        catch (IOException e) {
            //Specify the location of IOException
			e.printStackTrace();
        }
		
		return landList;    
    }

    /**
     * Update land.csv after changes are made
     * @param landList an arrayList of Land 
     */    
    public void updateLandDAO(ArrayList<Land> landList) {
        try {
			PrintStream writer = new PrintStream(new FileOutputStream("data\\land.csv", false));
			writer.println("userName,landNo,seedName,plantTime,harvestTime,wiltTime");
            
			for(Land land: landList){
				writer.println(land.getUserName() + "," + land.getLandNo() + "," + 
				land.getSeedName() + "," + land.getPlantTime() + "," + land.getHarvestTime() + "," + land.getWiltTime());
			}
			writer.close();
		}
			
		catch (IOException e) {
			//Specify the location of IOException
			e.printStackTrace();
		}	 
    }

}