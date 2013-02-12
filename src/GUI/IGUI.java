package GUI;

import Logic.ILogic;
import Logic.Layer;
import Logic.LogicGUIMessageType;

public abstract class IGUI extends Layer {
	private ILogic logic;

	private final void SendMessage(GUIMessageType messageType, Object[] args) {
		run(new guiRunnable(messageType, args));
	}

	public final void SetLogic(ILogic l) {
		if (logic != null)
			throw new RuntimeException("May only be executed once!");
		logic = l;
	}

	public abstract void ReceiveMessage(LogicGUIMessageType message, Object[] args);

	public abstract void OnClose();
	
	class guiRunnable implements Runnable {
		GUIMessageType messageType;
		Object[] args;
		public guiRunnable (GUIMessageType m, Object[] o) {
			messageType = m; args = o;
		}

		@Override
		public void run() {
			logic.ReceiveMessage(messageType, args);
		}
	}
}
