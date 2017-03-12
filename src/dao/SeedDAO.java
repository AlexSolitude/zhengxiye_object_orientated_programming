package dao;
import entity.*;
import java.io.*;
import java.util.*;

/**
 * SeedDAO is used to extract and update all the data from seed.csv file
 */
public class SeedDAO {
    private ArrayList<Seed> seedList;

    /**
     * Creates SeedDAO object and initialize seedList
     */    
    public SeedDAO() {
        seedList = new ArrayList<Seed>();
    }

    /**
     * Get the list of seed data from seed.csv
     * @return an ArrayList of Seed
     */    
    public ArrayList<Seed> importSeedData() {
        try {
            //Try to access seed.csv in the given directory
			File file = new File("data\\seed.csv");
            Scanner fileIn = new Scanner(file);
            
            fileIn.skip("userName,seedName,quantity");
            
			//Use comma as delimiter in extracting various seed info
			fileIn.useDelimiter(",|\r\n|\n");
            
            while (fileIn.hasNext()) {
                String userName = fileIn.next().trim();
                String seedName = fileIn.next().trim();
                int quantity = fileIn.nextInt();
                
				//Create new seed based on extracted info
                Seed seed = new Seed(userName, seedName, quantity);
				
				//Add seed to seedList
                seedList.add(seed);
            }
            
        }
        
        catch (IOException e) {
            //Specify the location of IOException
			e.printStackTrace();
        }
		
		return seedList;
    }

    /**
     * Update seed.csv after changes are made
     * @param seedList an ArrayList of Seed
     */    
    public void updateSeedDAO(ArrayList<Seed> seedList) {
        try {
			PrintStream writer = new PrintStream(new FileOutputStream("data\\seed.csv", false));
			
			writer.println("userName,seedName,quantity");
			
			for(Seed seed: seedList){
				writer.println(seed.getUserName() + "," + seed.getSeedName() + "," + 
				seed.getQuantity());                				
			}
			writer.close();
		}
			
		catch (IOException e) {
			//Specify the location of IOException
			e.printStackTrace();
		}	 
    
    }
}