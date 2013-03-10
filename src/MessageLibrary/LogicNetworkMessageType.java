package MessageLibrary;

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
}
