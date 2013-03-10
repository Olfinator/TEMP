package Logic;

import MessageLibrary.GUIMessageType;
import MessageLibrary.LogicNetworkMessageType;
import MessageLibrary.NetworkMessageType;

public class OlfLogic extends ILogic {

	@Override
	public void ReceiveMessage(GUIMessageType messageType, Object[] args) {
		switch(messageType) {
		case sendmessage:
			SendMessage(LogicNetworkMessageType.sendmessage, args);
			break;
		default:
			break;
		}

	}

	@Override
	public void ReceiveMessage(NetworkMessageType messageType, Object[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnClose() {
		// TODO Auto-generated method stub

	}

}
