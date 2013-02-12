package Network;

import Logic.ILogic;
import Logic.LogicNetworkMessageType;
import Logic.Layer;

public abstract class INetwork extends Layer {
	private ILogic logic;

	protected final void SendMessage(NetworkMessageType messageType, Object[] args) {
		run (new networkRunnable(messageType, args));
	}

	public final void SetLogic(ILogic l) {
		if (logic != null)
			throw new RuntimeException("May only be executed once!");
		logic = l;
	}

	public abstract void ReceiveMessage(LogicNetworkMessageType messageType, Object[] args);

	public abstract void OnClose();
	
	class networkRunnable implements Runnable {
		NetworkMessageType messageType;
		Object[] args;
		public networkRunnable (NetworkMessageType m, Object[] o) {
			messageType = m; args = o;
		}

		@Override
		public void run() {
			logic.ReceiveMessage(messageType, args);
		}
	}
}
