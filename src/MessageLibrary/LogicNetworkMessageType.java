package MessageLibrary;

import java.util.Dictionary;

public enum LogicNetworkMessageType {
	sendmessage,
	sendnewbuddy
	;
	static void paramDefs(Dictionary<LogicNetworkMessageType, Class<?>[]> dict) {
	}
}
