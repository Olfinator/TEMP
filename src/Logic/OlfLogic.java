package Logic;

import MessageLibrary.GUIMessageType;
import MessageLibrary.LogicNetworkMessageType;
import MessageLibrary.NetworkMessageType;


public class OlfLogic extends ILogic {
	Chat c = new Chat();
	Buddylist b = new Buddylist();
	@Override
	public void ReceiveMessage(GUIMessageType messageType, Object[] args) {
		switch(messageType) {
		case sendmessage:
			SendMessage(LogicNetworkMessageType.sendmessage, args);
			c.makeChat(args[1], args[2]); // args1 = message args2 = buddy
			break;
		case sendnewbuddy: 
			SendMessage(LogicNetworkMessageType.sendnewbuddy, args);
			b.newBuddy((String)args[1],(String)args[2]); // args1 = Name args2 = jid 
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

	@Override
	public void GuiInit() {

	}

	@Override
	public void NetworkInit() {
		
	}

}
