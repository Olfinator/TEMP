package Network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import Logic.LogicNetworkMessageType;

public class NetworkConnection implements INetwork, Runnable {
	private Socket socket;
	private XMLOutputFactory factory;
	private XMLStreamWriter writer;

	private Thread readThread;
	private Thread writeThread;

	public NetworkConnection() {
		factory = XMLOutputFactory.newInstance();
		readThread = new Thread(new Runnable() {
			@Override
			public void run() {
				write();
			}
		}, "Network-Read-Thread");
		readThread.setDaemon(true);
		readThread.start();
		writeThread = new Thread(new Runnable() {
			@Override
			public void run() {
				read();
			}
		}, "Network-Write-Thread");
		writeThread.setDaemon(true);
		writeThread.start();
	}

	protected void read() {
		while (true) {
			if (socket == null || socket.isClosed()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
			}
		}
	}

	protected void write() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}

	private void WriteStreamHeader(String jid, String server) {
		try {
			writer.writeStartDocument("1.0");
			writer.writeStartElement("stream:stream");
			writer.writeAttribute("from", jid);
			writer.writeAttribute("to", server);
			writer.writeAttribute("version", "1.0");
			writer.writeAttribute("xml:lang", "en");
			writer.writeDefaultNamespace("jabber:client");
			writer.writeNamespace("stream", "http://etherx.jabber.org/streams");
			writer.writeComment("");
			writer.flush();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void WriteStanza(Stanza s) {
		try {
			writer.writeStartElement(s.name);
			Enumeration<String> keys = s.attributes.keys();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = s.attributes.get(key);
				writer.writeAttribute(key, value);
			}
			keys = s.namespaces.keys();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = s.attributes.get(key);
				writer.writeNamespace(key, value);
			}
			if (s.body != null)
				WriteStanza(s.body);
			writer.writeComment("");
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void Initialize(String jid, String host) {
		if (socket == null || socket.isClosed()) {
			return;
		}
		try {
			writer = factory.createXMLStreamWriter(socket.getOutputStream(),
					"UTF8");
			WriteStreamHeader("Anon", "localhost");
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void Connect(String host, int port) throws UnknownHostException,
			IOException {
		if (socket != null && !socket.isClosed())
			try {
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				}
				socket.close();
			} catch (IOException e) {
			}
		socket = new Socket(host, port);
	}

	private void Disconnect() {
		if (socket != null && !socket.isClosed())
			try {
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				}
				socket.close();
			} catch (IOException e) {
			}
		socket = null;
	}

	private void Send(byte[] rawData) throws IOException {
		if (socket != null && !socket.isClosed()) {
			socket.getOutputStream().write(rawData);
		} else {
			throw new IOException("No Connection");
		}
	}

	public static void main(String[] args) {
		new NetworkConnection();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

	@Override
	public void ReceiveMessage(LogicNetworkMessageType messageType,
			Object[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
