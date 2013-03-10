package Logic;

import MessageLibrary.GUIMessageType;
import MessageLibrary.NetworkMessageType;

/*
 * Dummy for Logic-Element. For Test-Purposes of Interaction with the other
 * Elements, this Class may be copied and modified. This class may not be 
 * modified directly!
 */
public class DummyLogic extends ILogic{
	public DummyLogic() {
		logger.fine("LogicDummy initialized");
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

	@Override
	public void GuiInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NetworkInit() {
		// TODO Auto-generated method stub
		
	}
}
