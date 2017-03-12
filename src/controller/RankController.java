package controller;
import entity.*;
import dao.*;
import java.util.*;

/**
 * RankController is the controller class for rank related function
 */
public class RankController {
    private ArrayList<Rank> rankList;
	private RankDAO rankDAO;

    /**
     * Creates RankController object and initialize rankList with rank data from RankDAO
     */      
    public RankController(){
		rankDAO = new RankDAO();
		rankList = rankDAO.importRankData();
	}

    /**
     * Get the list of all rank
     * @return an ArrayList of Rank
     */     
    public ArrayList<Rank> retrieveAll(){
            return rankList;
    }

    /**
     * Get the specific rank based on experience points
     * @param xp the experience points 
     * @return the Rank based on the experience points
     */ 	
	public Rank retrieveRank(int xp){       
		Rank rankToReturn = rankList.get(0);	
        
        //retrieve rank based on experience points
		for(int i = 0; i < rankList.size(); i++){
			Rank rank = rankList.get(i);
			if(xp > rank.getXp()){
				rankToReturn = rank;
			}
		}
		return rankToReturn;
	}
    
    /**
     * Add rank to rankList
     * @param rank the rank to be added
     */ 	
	public void addRank(Rank rank){
		rankList.add(rank);
	}
}