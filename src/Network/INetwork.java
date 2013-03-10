package Network;

import java.util.concurrent.Future;

import Logic.ILogic;
import Logic.Layer;
import MessageLibrary.LogicNetworkMessageType;
import MessageLibrary.NetworkMessageTester;
import MessageLibrary.NetworkMessageType;

public abstract class INetwork extends Layer {
	private ILogic logic;
	private static final NetworkMessageTester tester;

	static {
		tester = new NetworkMessageTester();
	}

	protected final Future<?> SendMessage(NetworkMessageType messageType, Object[] args) {
		return run(new networkRunnable(messageType, args));
	}

	public final void SetLogic(ILogic l) {
		if (logic != null)
			throw new RuntimeException("May only be executed once!");
		logic = l;
	}

	public abstract void ReceiveMessage(LogicNetworkMessageType messageType, Object[] args);

	public abstract void OnClose();
	
	final class networkRunnable implements Runnable {
		NetworkMessageType messageType;
		Object[] args;
		public networkRunnable (NetworkMessageType m, Object[] o) {
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
