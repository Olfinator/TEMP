package GUI;

import java.util.concurrent.Future;

import Logic.ILogic;
import Logic.Layer;
import MessageLibrary.GUIMessageType;
import MessageLibrary.LogicGUIMessageType;

public abstract class IGUI extends Layer {
	private ILogic logic;

	protected final Future<?> SendMessage(GUIMessageType messageType, Object[] args) {
		return run(new guiRunnable(messageType, args));
	}

	public final void SetLogic(ILogic l) {
		if (logic != null)
			throw new RuntimeException("May only be executed once!");
		logic = l;
	}

	public abstract void ReceiveMessage(LogicGUIMessageType message, Object[] args);

	public abstract void OnClose();
	
	final class guiRunnable implements Runnable {
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
