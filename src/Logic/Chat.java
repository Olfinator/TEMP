package Logic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class Chat {

	public Chat(){
		
	}
	private Buddy Buddy;
	private ArrayList<Textmessage> savedChat;
	private Hashtable<Buddy, Textmessage> Textmessages;
	
	public void writeGespeichertenChat(){
		try{
			OutputStream chattext = new FileOutputStream("\\logs\\chatlog.txt");
			ObjectOutputStream chatstream = new ObjectOutputStream(chattext);
			String trte = "test";
			chatstream.writeObject(trte);
			chatstream.close();
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
	public void makeChat(Object text, Object person){
		Textmessage message = new Textmessage();
		message.setContent((String)text);
		Textmessages.put((Buddy)person,message);
		
	}
	
	public Textmessage getChat(Buddy buddy){
		return Textmessages.get(buddy);
	}
public void main(String[] args){
	Chat x = new Chat();
}
}
