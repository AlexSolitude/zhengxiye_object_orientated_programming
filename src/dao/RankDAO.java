package dao;
import entity.*;
import java.io.*;
import java.util.*;

/**
 * RankDAO is used to extract all the data from rank.csv file
 */
public class RankDAO{
	private ArrayList<Rank> rankList;

    /**
     * Creates RankDAO object and initialize rankList
     */	
	public RankDAO(){
		rankList = new ArrayList<Rank>();
	}

    /**
     * Get the list of rank data from rank.csv
     * @return an ArrayList of Rank
     */	
	public ArrayList<Rank> importRankData(){
		try {
            //Try to access rank.csv in the given directory
            File file = new File("data\\rank.csv");
            Scanner fileIn = new Scanner(file);
			
			fileIn.skip("RankName,XP,Plots");
			
            //Use comma as delimiter in extracting various rank info
            fileIn.useDelimiter(",|\r\n|\n");
			
            while (fileIn.hasNext()) {
                String rankName = fileIn.next().trim();
                int xp = fileIn.nextInt();
                int plots = fileIn.nextInt();
                
                //Create new rank based on extracted info
				Rank rank = new Rank(rankName, xp, plots);
                
                //Add rank to rankList
                rankList.add(rank);
                
            }
            
        }catch (IOException e) {
            e.printStackTrace();
        }
		
		return rankList;
	}
    
}
