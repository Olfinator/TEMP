

package Logic;

import java.util.ArrayList;
import java.util.Hashtable;

public class Buddylist {

	private  Hashtable<String, Buddy> ListofBuddys;
	
	public void newBuddy(String Name, String jid){
		Buddy b = new Buddy(Name, jid);
		ListofBuddys.put(jid, b);
	}
	
	
	
	public void deleteBuddy(String jid){
		ListofBuddys.remove(jid);
	}
	
	public Hashtable<String, Buddy> getBuddylist(){
		return ListofBuddys;
	}
}
