package Network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NullCipher;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import MessageLibrary.LogicNetworkMessageType;
import MessageLibrary.NetworkMessageType;

public class NetworkConnection extends INetwork {
	private Socket socket;

	private CipherOutputStream cipherOut;
	private XMLStreamWriter xmlOut;
	private CipherInputStream cipherIn;
	private XMLStreamReader xmlIn;

	private ServerInformation server;

	private Thread readThread;
	private Thread writeThread;

	public NetworkConnection() {
		server = new ServerInformation();
		readThread = new Thread(new Runnable() {
			@Override
			public void run() {
				Read();
			}
		}, "NetworkConnection Read-Thread");
		readThread.setDaemon(true);
		writeThread = new Thread(new Runnable() {
			@Override
			public void run() {
				Write();
			}
		}, "NetworkConnection Write-Thread");
		logger.fine("Initialized");
	}

	protected void Write() {
		try {
			XMLOutputFactory outFactory = XMLOutputFactory.newFactory();
			xmlOut = outFactory.createXMLStreamWriter(cipherOut, "UTF-8");
			OpenAnonXMLStream();
		} catch(FactoryConfigurationError | XMLStreamException e) {
			logger.severe(e.getMessage());
		}
	}

	protected void Read() {
		try {
			XMLInputFactory inFactory = XMLInputFactory.newFactory();
			xmlIn = inFactory.createXMLStreamReader(cipherIn);
			xmlIn.next();
			CheckHeader();
		} catch (FactoryConfigurationError | XMLStreamException e) {
			logger.severe(e.getMessage());
		}
		
	}

	private void CheckHeader() throws XMLStreamException {
		int c = xmlIn.getAttributeCount();
		for (int i = 0; i < c; i++) {
			String aName = xmlIn.getAttributeLocalName(i);
			String aValue = xmlIn.getAttributeValue(i);
			logger.info(aName + " " + aValue);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		CloseSocket();
	}

	private void Connect(String host, int port) {
		server.name = host;
		server.port = port;
		try {
			socket = new Socket(host, port);
			cipherOut = new CipherOutputStream(socket.getOutputStream(), new NullCipher());
			cipherIn = new CipherInputStream(socket.getInputStream(), new NullCipher());
		} catch (UnknownHostException e) {
			logger.info(e.getMessage());
			SendMessage(NetworkMessageType.ConnectResult, new Object[] { 0xF1, e.getMessage() });
			return;
		} catch (IOException e) {
			logger.info(e.getMessage());
			SendMessage(NetworkMessageType.ConnectResult, new Object[] { 0xFF, e.getMessage() });
			return;
		}
		readThread.start();
		writeThread.start();
	}

	private void OpenAnonXMLStream() throws XMLStreamException {
		xmlOut.writeStartDocument("UTF-8", "1.0");
		xmlOut.writeStartElement("stream", "stream", "http://etherx.jabber.org/streams");
		xmlOut.writeAttribute("to", server.name);
		xmlOut.writeAttribute("version", "0.1");
		xmlOut.writeAttribute("xml", "xml", "lang", "en");
		xmlOut.writeDefaultNamespace("jabber:client");
		xmlOut.writeNamespace("stream", "http://etherx.jabber.org/streams");
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
