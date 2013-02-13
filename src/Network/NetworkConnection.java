package Network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.crypto.CipherOutputStream;
import javax.crypto.NullCipher;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import MessageLibrary.LogicNetworkMessageType;
import MessageLibrary.NetworkMessageType;

public class NetworkConnection extends INetwork {
	private Socket socket;
	
	private CipherOutputStream cipherOut;
	private XMLStreamWriter xmlOut;

	public NetworkConnection() {
	}

	@Override
	protected void finalize() throws Throwable {

	}

	private void Connect(String host, int port) {
		try {
			socket = new Socket(host, port);
			cipherOut = new CipherOutputStream(socket.getOutputStream(), new NullCipher());
			XMLOutputFactory outFactory = XMLOutputFactory.newFactory();
			xmlOut = outFactory.createXMLStreamWriter(cipherOut, "UTF-8");
			OpenXMLStream();
		} catch (UnknownHostException e) {
			logger.info(e.getMessage());
			SendMessage(NetworkMessageType.ConnectResult, new Object[] { 0xF1 });
			return;
		} catch (IOException e) {
			logger.info(e.getMessage());
			SendMessage(NetworkMessageType.ConnectResult, new Object[] { 0xFF });
			return;
		} catch (XMLStreamException e) {
			logger.info(e.getMessage());
			SendMessage(NetworkMessageType.ConnectResult, new Object[] { 0xFF });
			return;
		}
		SendMessage(NetworkMessageType.ConnectResult, new Object[] { 0x00 });
	}

	private void OpenXMLStream() throws XMLStreamException {
		xmlOut.writeStartDocument("UTF-8", "1.0");
		xmlOut.writeStartElement("stream", "stream", "jabber-client");
		xmlOut.writeComment("");
		xmlOut.flush();
	}

	private void CloseSocket() {
		try {
			xmlOut.close();
		} catch (XMLStreamException e) {
			logger.throwing("XMLStreamwriter", "close", e);
		} finally {
			xmlOut = null;
		}
		try {
			cipherOut.close();
		} catch (IOException e) {
			logger.throwing("CipherOutputStream", "close", e);
		} finally {
			cipherOut = null;
		}
		try {
			socket.close();
		} catch (IOException e) {
			logger.throwing("Socket", "close", e);
		} finally {
			socket = null;
		}
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
