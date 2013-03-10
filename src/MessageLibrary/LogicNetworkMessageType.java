package MessageLibrary;

import java.util.Dictionary;

public enum LogicNetworkMessageType {
	Connect
	/*
	 * Connect-Attempt
	 * args:
	 * 	0 - Host : String 
	 *  1 - port : int
	 */,
	 sendmessage
	 /*
	  * Message to send
	  * args:
	  *  0 - Text : String
	  */
	;
	static void paramDefs(Dictionary<LogicNetworkMessageType, Class<?>[]> dict) {
		dict.put(Connect, new Class<?>[] { String.class, Integer.class });
		dict.put(sendmessage, new Class<?>[] { String.class });
	}
}
