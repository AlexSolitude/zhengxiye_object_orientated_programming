package controller;
import entity.*;
import dao.*;
import java.util.*;

/**
 * CropController is the controller class for crop related function
 */
public class CropController {
    private ArrayList<Crop> cropList;
    private CropDAO cropDAO;

    /**
     * Creates CropController object and initialize cropList with crop data from cropDAO
     */    
    public CropController(){
        cropDAO = new CropDAO();
        cropList = cropDAO.importCropData();
    }

    /**
     * Get the crop based on seedName
     * @param seedName seedName of the crop
     * @return the crop based on seedName
     */    
    public Crop retrieveCrop(String seedName) {
        Crop cropToReturn = null;
        
        for(int i = 0; i <cropList.size(); i++) {
            Crop crop = cropList.get(i);
            String cropName = crop.getName();
			
            if(cropName.equals(seedName)) {
                cropToReturn = crop;
            }
        }
        return cropToReturn;
    }

    /**
     * Get the list of all crop
     * @return an ArrayList of Crop
     */  	
	public ArrayList<Crop> retrieveAll(){
		return cropList;
	}
	
}