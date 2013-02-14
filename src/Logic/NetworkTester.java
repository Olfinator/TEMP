package Logic;

import MessageLibrary.GUIMessageType;
import MessageLibrary.LogicNetworkMessageType;
import MessageLibrary.NetworkMessageType;


public class NetworkTester extends ILogic implements Runnable {

	public NetworkTester() {
		Thread t = new Thread(this);
		t.setDaemon(true);
		t.start();
		logger.fine("NetworkTester initialized");
	}
	
	@Override
	public void ReceiveMessage(GUIMessageType messageType, Object[] args) {
		logger.info(messageType.name());
	}

	@Override
	public void ReceiveMessage(NetworkMessageType messageType, Object[] args) {
		logger.info(messageType.name());
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
			Thread.sleep(45000);
		} catch (InterruptedException e) {
		}
		logger.info("Close");
		Close();
	}
}
