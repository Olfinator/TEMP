package MessageLibrary;

import java.util.Dictionary;

public enum GUIMessageType {
	sendmessage,
	sendnewbuddy
	;
	static void paramDefs(Dictionary<GUIMessageType, Class<?>[]> dict) {
	}
}
