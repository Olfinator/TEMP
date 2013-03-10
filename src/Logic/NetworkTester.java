package Logic;

import MessageLibrary.GUIMessageType;
import MessageLibrary.LogicNetworkMessageType;
import MessageLibrary.NetworkMessageType;


public class NetworkTester extends ILogic implements Runnable {
	String host;

	public NetworkTester() {
		this("me-pc");
	}

	public NetworkTester(String h) {
		host = h;
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
		SendMessage(LogicNetworkMessageType.Connect, new Object[] { host, 61337 });
		SendMessage(LogicNetworkMessageType.sendmessage, new Object[] { "Test" });
		try {
			Thread.sleep(45000);
		} catch (InterruptedException e) {
		}
		logger.info("Close");
		Close();
	}

	@Override
	public void GuiInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NetworkInit() {
		// TODO Auto-generated method stub
		
	}
}
