

package Logic;

import java.util.ArrayList;

public class Buddylist {

	private  ArrayList<Buddy> ListofBuddys;
	
	public void addBuddy(Buddy x){
		ListofBuddys.add(x);
	}
	
	public void deleteBuddy(Buddy x){
		ListofBuddys.remove(x);
	}
	
	public ArrayList<Buddy> getBuddylist(){
		return ListofBuddys;
	}
}
