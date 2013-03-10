package MessageLibrary;

import java.util.Dictionary;

public enum GUIMessageType {
	sendmessage,
	sendnewbuddy,
	deletebuddy,
	close
	;
	static void paramDefs(Dictionary<GUIMessageType, Class<?>[]> dict) {
	}
}
