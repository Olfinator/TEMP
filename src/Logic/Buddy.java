package Logic;

public class Buddy {
	private String Name;
	private String jid;
	private boolean isOnline;
	
	public String getName(){
		return Name;
	}
	
	public String getjid(){
		return jid;
	}
	
	public boolean getIsOnline(){
		return isOnline;
	}
	
	public Buddy(String _Name, String _jid){
		Name = _Name;
		jid = _jid;
	}

}
