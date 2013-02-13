package MessageLibrary;

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
}
