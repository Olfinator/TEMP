package GUI;

import Logic.LogicGUIMessageType;

/*
 * Dummy for GUI-Element. For Test-Purposes of Interaction with the other
 * Elements, this Class may be copied and modified. This class may not be 
 * modified directly!
 */

public final class GUIDummy extends IGUI{
	
	public GUIDummy(boolean debug) {
		
	}
	
	@Override
	public void ReceiveMessage(LogicGUIMessageType message, Object[] args) {
		
	}

	@Override
	public void OnClose() {

	}

}
