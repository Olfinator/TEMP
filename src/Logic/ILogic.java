package Logic;

import java.util.concurrent.Future;

import GUI.IGUI;
import MessageLibrary.GUIMessageType;
import MessageLibrary.LogicGUIMessageType;
import MessageLibrary.LogicNetworkMessageType;
import MessageLibrary.NetworkMessageType;
import Network.INetwork;

public abstract class ILogic extends Layer{
	private IGUI gui;
	private INetwork network;
	private Thread mainThread;

	protected final Future<?> SendMessage(LogicGUIMessageType messageType, Object[] args) {
		return run(new gLogicRunnable(messageType, args));
	}

	protected final Future<?> SendMessage(LogicNetworkMessageType messageType, Object[] args) {
		return run(new nLogicRunnable(messageType, args));
	}

	public final void SetGui(IGUI g) {
		if (gui != null)
			throw new RuntimeException("May only be executed once!");
		gui = g;
		gui.SetLogic(this);
	}

	public final void SetNetwork(INetwork net) {
		if (network != null)
			throw new RuntimeException("May only be executed once!");
		network = net;
		network.SetLogic(this);
	}

	public final void SetThread(Thread t) {
		if (mainThread != null)
			throw new RuntimeException("May only be executed once!");
		mainThread = t;
	}

	public abstract void ReceiveMessage(GUIMessageType messageType, Object[] args);

	public abstract void ReceiveMessage(NetworkMessageType messageType, Object[] args);

	public final void Close() {
		mainThread.interrupt();
	}

	public abstract void OnClose();
	
	final class nLogicRunnable implements Runnable {
		LogicNetworkMessageType messageType;
		Object[] args;
		public nLogicRunnable (LogicNetworkMessageType m, Object[] o) {
			messageType = m; args = o;
		}

		@Override
		public void run() {
			network.ReceiveMessage(messageType, args);
		}
	}
	
	class gLogicRunnable implements Runnable {
		LogicGUIMessageType messageType;
		Object[] args;
		public gLogicRunnable (LogicGUIMessageType m, Object[] o) {
			messageType = m; args = o;
		}

		@Override
		public void run() {
			gui.ReceiveMessage(messageType, args);
		}
	}
}
