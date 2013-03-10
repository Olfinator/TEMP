package MessageLibrary;

import java.util.Dictionary;

public enum LogicNetworkMessageType {
	sendmessage,
	sendnewbuddy,
	deletebuddy,
	Connect
	;
	static void paramDefs(Dictionary<LogicNetworkMessageType, Class<?>[]> dict) {
	}
}
