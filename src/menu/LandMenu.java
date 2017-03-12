package menu;
import entity.*;
import dao.*;
import controller.*;
import java.util.*;

/**
* LandMenu displays the player's land related information
*/
public class LandMenu{
    private ArrayList<Land> landList;   
    private LandController landCtrl;
    
    /**
    * LandMenu constructor
    */    
    public LandMenu(){
		landList = new ArrayList<Land>();
    }

    /**
    * Displays the land menu with player's land related information
    * @param player the player of this farm
    * @param landCtrl to perform land related function  
    */     
    public void displayLandMenu(Player player, LandController landCtrl) {
		//set controller
        this.landCtrl = landCtrl;
        
        // call method from land controller to check the number plots of land
        ArrayList<Land> landList = landCtrl.retrieveLand(player);
        
        System.out.println("You have " + landList.size() + " plots of land.");
		
        for(int i = 0; i < landList.size(); i++) {
			Land land = landList.get(i);
            System.out.print(land.getLandNo() + ". ");
			
            //check if land is empty; else print growth progress of the land
            if(land.getSeedName() == null || land.getSeedName().equals("null")) {
                System.out.println("<empty>");
            } else {
                System.out.print(land.getSeedName());
                
				double growthPercentage = landCtrl.growthProgress(land);
				
				int star = (int)(growthPercentage / 10); 
				
                //check if wilted
				if(growthPercentage > 200.0){					
					System.out.println("\t [   wilted   ]");					
				}else if(growthPercentage >= 100.0 && growthPercentage <= 200.0){
					System.out.print("\t[");
					for(int j = 0; j < 10; j++){
						System.out.print("#");
					}
					
					System.out.println("] 100%");
					
				}else{
					System.out.print("\t[");
					
					for(int k = 0; k < star; k++){
						System.out.print("#");
					}
				
					for(int l = 0; l < 10 - star; l++){
						System.out.print("-");
					}
					
					System.out.println("] " + (int)growthPercentage + "%");
				}
				
            }
            
        }    
        
    }
}