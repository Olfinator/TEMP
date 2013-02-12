package Network;

import Logic.LogicNetworkMessageType;

public class NetworkConnection extends INetwork{
	

	public NetworkConnection() {
	}

	@Override
	protected void finalize() throws Throwable {
		
	}

	@Override
	public void ReceiveMessage(LogicNetworkMessageType messageType,
			Object[] args) {
		switch (messageType) {
			case Connect: {
				if (false /*connected*/)
					; //Error
				
			}	break;
			default:
				break;
		}
	}
}
