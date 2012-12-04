package Network;

import Logic.LogicNetworkMessageType;

public interface INetwork {
	public void ReceiveMessage(LogicNetworkMessageType messageType, Object[] args);
}
