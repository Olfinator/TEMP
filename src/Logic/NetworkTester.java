package Logic;

import GUI.GUIMessageType;
import Network.NetworkMessageType;

public class NetworkTester extends ILogic implements Runnable {

	public NetworkTester() {
		Thread t = new Thread(this);
		t.setDaemon(true);
		t.start();
		Log("NetworkTester initialized");
	}
	
	@Override
	public void ReceiveMessage(GUIMessageType messageType, Object[] args) {
		Log(messageType.name());
	}

	@Override
	public void ReceiveMessage(NetworkMessageType messageType, Object[] args) {
		Log(messageType.name());
	}

	@Override
	public void OnClose() {

	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		SendMessage(LogicNetworkMessageType.Connect, new Object[] { "me-pc", 61337 });
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
		}
		Log("Close");
		Close();
	}
}
