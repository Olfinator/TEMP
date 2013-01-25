package Network;

import Logic.LogicNetworkMessageType;
/*
 * Dummy for Network-Element. For Test-Purposes of Interaction with the other
 * Elements, this Class may be copied and modified. This class may not be 
 * modified directly!
 */
public class NetworkDummy extends INetwork{

	public NetworkDummy(boolean debug) {
	}

	@Override
	public void ReceiveMessage(LogicNetworkMessageType messageType,
			Object[] args) {
	}
}
