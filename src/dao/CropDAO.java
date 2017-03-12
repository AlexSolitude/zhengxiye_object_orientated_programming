package dao;
import entity.*;
import java.io.*;
import java.util.*;

/**
 * CropDAO is used to extract all the data from crop.csv file
 */
public class CropDAO {
    private ArrayList<Crop> cropList;

    /**
     * Creates CropDAO object and initialize cropList
     */    
    public CropDAO() {
        cropList = new ArrayList<Crop>();
    }

    /**
     * Gets the list of crop data from crop.csv file
     * @return an ArrayList of Crop
     */    
    public ArrayList<Crop> importCropData() {
        try {
            //Try to access crop.csv in the given directory
			File file = new File("data\\crop.csv");
            Scanner fileIn = new Scanner(file);           
            
            fileIn.skip("Name,Cost,Time,XP,MinYield,MaxYield,SalePrice");
            
			//Use comma as delimiter in extracting various crop info
			fileIn.useDelimiter(",|\r\n|\n");
            
            while (fileIn.hasNext()) {
                String cropName = fileIn.next().trim();
                int cost = fileIn.nextInt();
                int time = fileIn.nextInt();
                int xp = fileIn.nextInt();
                int minYield = fileIn.nextInt();
                int maxYield = fileIn.nextInt();
                int salePrice = fileIn.nextInt();
                
				//Create new crop based on extracted info
                Crop crop = new Crop(cropName,cost,time,xp,minYield,maxYield,salePrice);
				
				//Add crop to cropList
                cropList.add(crop);
            }
            
        }
        
        catch (IOException e) {
            //Specify the location of IOException
			e.printStackTrace();
        }
		
		return cropList;    
    }
}