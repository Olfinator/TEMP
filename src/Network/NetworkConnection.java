package Network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Logic.LogicNetworkMessageType;

public class NetworkConnection extends INetwork {
	private Socket socket;

	public NetworkConnection() {
	}

	@Override
	protected void finalize() throws Throwable {

	}

	private void Connect(String host, int port) {
		try {
			socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			SendMessage(NetworkMessageType.ConnectResult, new Object[] { 0xF1 });
			return;
		} catch (IOException e) {
			SendMessage(NetworkMessageType.ConnectResult, new Object[] { 0xFF });
			return;
		}
		OpenXMLStream();
		SendMessage(NetworkMessageType.ConnectResult, new Object[] { 0x00 });
	}

	private void OpenXMLStream() {
		//TODO ...
	}

	@Override
	public void ReceiveMessage(LogicNetworkMessageType messageType,
			Object[] args) {
		switch (messageType) {
		case Connect: {
			if (!(socket == null || socket.isClosed())) {
				Object[] argsR = new Object[] { 0xF0 };
				SendMessage(NetworkMessageType.ConnectResult, argsR);
				return;
			}
			String host = (String) args[0];
			int port = (int) args[1];
			Connect(host, port);
		}
			break;
		default:
			break;
		}
	}

	@Override
	public void OnClose() {

	}
}
