package Logic;

import GUI.GUIMessageType;
import GUI.IGUI;
import Network.INetwork;
import Network.NetworkMessageType;

public abstract class ILogic {
	private IGUI gui;
	private INetwork network;

	private void SendMessage(LogicGUIMessageType messageType, Object[] args) {
		gui.ReceiceMessage(messageType, args);
	}

	private void SendMessage(LogicNetworkMessageType messageType, Object[] args) {
		network.ReceiveMessage(messageType, args);
	}

	public void SetGui(IGUI g) {
		if (gui != null)
			throw new RuntimeException("May only be executed once!");
		gui = g;
		gui.SetLogic(this);
	}

	public void SetNetwork(INetwork net) {
		if (network != null)
			throw new RuntimeException("May only be executed once!");
		network = net;
		network.SetLogic(this);
	}

	public abstract void ReceiveMessage(GUIMessageType messageType, Object[] args);

	public abstract void ReceiveMessage(NetworkMessageType messageType, Object[] args);
}
