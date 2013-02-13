package GUI;

import java.util.concurrent.Future;

import Logic.ILogic;
import Logic.Layer;
import MessageLibrary.GUIMessageTester;
import MessageLibrary.GUIMessageType;
import MessageLibrary.LogicGUIMessageType;

public abstract class IGUI extends Layer {
	private ILogic logic;
	private static final GUIMessageTester tester;

	static {
		tester = new GUIMessageTester();
	}

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
			if (tester.Test(messageType, args))
				logic.ReceiveMessage(messageType, args);
			else {
				StringBuilder s = new StringBuilder("Message Argument Failure: ");
				s.append(messageType.name());
				for (int i = 0; i < args.length; i++) {
					s.append(", ");
					if (args[i] == null)
						s.append("()");
					else
						s.append(args[i].toString());
				}
				logger.severe(s.toString());
			}
		}
	}
}
