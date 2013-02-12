package Logic;

import GUI.GUIMessageType;
import Network.NetworkMessageType;

/*
 * Dummy for Logic-Element. For Test-Purposes of Interaction with the other
 * Elements, this Class may be copied and modified. This class may not be 
 * modified directly!
 */
public class DummyLogic extends ILogic{
	public DummyLogic(boolean debug) {

	}

	@Override
	public void ReceiveMessage(GUIMessageType messageType, Object[] args) {
		
	}

	@Override
	public void ReceiveMessage(NetworkMessageType messageType, Object[] args) {

	}

	@Override
	public void OnClose() {

	}
}
