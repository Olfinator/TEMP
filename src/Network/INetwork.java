package Network;

import Logic.ILogic;
import Logic.LogicNetworkMessageType;

public abstract class INetwork {
	private ILogic logic;

	private final void SendMessage(NetworkMessageType messageType, Object[] args) {
		logic.ReceiveMessage(messageType, args);
	}

	public final void SetLogic(ILogic l) {
		if (logic != null)
			throw new RuntimeException("May only be executed once!");
		logic = l;
	}

	public abstract void ReceiveMessage(LogicNetworkMessageType messageType, Object[] args);

	public abstract void OnClose();
}
