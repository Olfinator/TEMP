package Logic;

import GUI.GUIMessageType;
import Network.NetworkMessageType;

public interface ILogic {
	public void ReceiveMessage(GUIMessageType messageType, Object[] args);

	public void ReceiveMessage(NetworkMessageType messageType, Object[] args);
}
