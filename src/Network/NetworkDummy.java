package Network;

import MessageLibrary.LogicNetworkMessageType;
/*
 * Dummy for Network-Element. For Test-Purposes of Interaction with the other
 * Elements, this Class may be copied and modified. This class may not be 
 * modified directly!
 */
public final class NetworkDummy extends INetwork{

	public NetworkDummy() {
		logger.fine("NetworkDummy initialized");
	}

	@Override
	public void ReceiveMessage(LogicNetworkMessageType messageType,
			Object[] args) {
	}

	@Override
	public void OnClose() {

	}
}
