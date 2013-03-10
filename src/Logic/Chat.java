package Logic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

public class Chat {

	public Chat(){
		writeGespeichertenChat();
	}
	private Buddy Buddy;
	private ArrayList<Textnachricht> gespeicherterChat;
	private ArrayList<Textnachricht> Tectnachrichten;
	
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
public void main(String[] args){
	Chat x = new Chat();
}
}
