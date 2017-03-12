package dao;
import entity.*;
import java.io.*;
import java.util.*;

/**
 * FriendDAO is used to extract and update all the data from friend.csv file
 */
public class FriendDAO {
    public static ArrayList<Friend> fList;

    /**
     * Creates FriendDAO object and initialize the fList
     */    
    public FriendDAO() {
        fList = new ArrayList<Friend>();
    }

    /**
     * Gets the list of friend data from friend.csv
     * @return an ArrayList of Friend
     */    
    public ArrayList<Friend> importFriendData(){
        try {
            //Try to access friend.csv in the given directory
			File file = new File("data\\friend.csv");
            Scanner fileIn = new Scanner(file);
            
            fileIn.skip("user,friend,status");
            
			//Use comma as delimiter in extracting various friend info
			fileIn.useDelimiter(",|\r\n|\n");
          
            boolean isUnique = true;           
            while (fileIn.hasNext()) {
                //read file
                String userName = fileIn.next().trim();
                String friendName = fileIn.next().trim();
                char status = fileIn.next().charAt(0);
                
                isUnique = true;
                if(fList.size() !=0){
                    for(int i = 0; i<fList.size(); i++) {
                        Friend friend = fList.get(i);
                        //check if friend exists in fList
                        if(friend.getUserName().equals(userName)){
                            isUnique = false;                       
                            if(status == 'f') {
                                //add to friendlist
                                ArrayList<String> friendList = friend.getFriendList();
                                friendList.add(friendName);
                            } else { //add to request list
                                ArrayList<String> requestList = friend.getRequestList();
                                 requestList.add(friendName);
                            }  
                        }                                            
                    }
                    
                    //if username is not found in the fList, create new friend object    
                    if(isUnique) {
                            //Add friend to fList
                            Friend newFriend = new Friend(userName);
                            fList.add(newFriend);
                            if(status == 'f') { 
                                //add to friendlist
                                ArrayList<String> friendList = newFriend.getFriendList();
                                friendList.add(friendName);
                            } else { //add to request list
                                ArrayList<String> requestList = newFriend.getRequestList();
                                requestList.add(friendName);
                            }                                
                    }
                } else { //if fList size = 0, add first Friend object into the list
                    Friend firstFriend = new Friend(userName);				
                    //Add friend to fList
                    fList.add(firstFriend);
                    if(status == 'f') {
                        //add to friend list
                        ArrayList<String> friendList = firstFriend.getFriendList();
                        friendList.add(friendName);
                    } else { //add to request list
                        ArrayList<String> requestList = firstFriend.getRequestList();
                        requestList.add(friendName);
                    }                         
                   
                }  				
            }            
        }       
        catch (IOException e) {
            //Specify the location of IOException
			e.printStackTrace();
        }        
		return fList;
    }

    /**
     * Update friend.csv after changes are made
     * @param fList an ArrayList of Friend
     */    
    public void updateFriendDAO(ArrayList<Friend> fList){
        try {
			PrintStream writer = new PrintStream(new FileOutputStream("data\\friend.csv", false));
			//write the headings for friend csv
            writer.println("user,friend,status");
            
			for(Friend friend: fList){
                //retrieve each friend's friend list and request list
                ArrayList<String> friendList = friend.getFriendList();
                ArrayList<String> requestList = friend.getRequestList();
                
                //write to file if it's friend
                for(int i = 0; i<friendList.size(); i++){
                    writer.println(friend.getUserName() + "," + friendList.get(i) + "," + 'f');
                }
                
                //write to file if it's request
                for(int j = 0; j<requestList.size(); j++) {
                    writer.println(friend.getUserName() + "," + requestList.get(j) + "," + 'r');
                }
			}
            writer.close();
		}
			
		catch (IOException e) {
			//Specify the location of IOException
			e.printStackTrace();
		}	 
    }
}