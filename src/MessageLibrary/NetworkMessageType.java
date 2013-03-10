package MessageLibrary;

import java.util.Dictionary;

public enum NetworkMessageType {
	ConnectResult
	/*
	 * Result of Connect-Attempt
	 * args[0] - Result : int
	 * 		0x00	Success
	 * 		0xF0	Already Connected
	 * 		0xF1	Host not Found
	 * 		0xFF	Error
	 */
	;
	static void paramDef(Dictionary<NetworkMessageType, Class<?>[]> dict) {
		dict.put(ConnectResult, new Class<?>[] { Integer.class });
	}
}
