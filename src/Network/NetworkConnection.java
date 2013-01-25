package Network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Queue;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import Logic.LogicNetworkMessageType;

public class NetworkConnection extends INetwork{
	private Socket socket;
	private XMLOutputFactory wfactory;
	private XMLInputFactory rfactory;
	private XMLStreamWriter writer;
	private XMLStreamReader reader;

	private Queue<Stanza> stanzaQueue;

	private Thread readThread;
	private Thread writeThread;
	private boolean streamInitialized = false;

	public NetworkConnection() {
		wfactory = XMLOutputFactory.newInstance();
		rfactory = XMLInputFactory.newInstance();
		stanzaQueue = new LinkedList<>();
		//Create Read-Thread
		readThread = new Thread(new Runnable() {
			@Override
			public void run() {
				write();
			}
		}, "Network-Read-Thread");
		readThread.setDaemon(true);
		readThread.start();
		//Create Write-Thread
		writeThread = new Thread(new Runnable() {
			@Override
			public void run() {
				read();
			}
		}, "Network-Write-Thread");
		writeThread.setDaemon(true);
		writeThread.start();
	}

	@Override
	protected void finalize() throws Throwable {
		CloseStream();
		socket.close();
	}

	protected void read() {
		while (true) {
			if (reader == null) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			} else {
				try {
					if (!streamInitialized) {
						if (reader.hasNext()) {
							reader.next();
							System.out.println(reader.getLocalName());
						}
					} else {

					}
				} catch (XMLStreamException e) {
				}
			}
		}
	}

	protected void write() {
		while (true) {
			if (!streamInitialized) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			} else {
				if (stanzaQueue.isEmpty()) {
					try {
						Thread.sleep(75);
					} catch (InterruptedException e) {
					}
				} else {
					// TODO!!
					WriteStanza(stanzaQueue.poll());
				}
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
			writer.writeEndElement();
			writer.flush();
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
			writer = wfactory.createXMLStreamWriter(socket.getOutputStream(),
					"UTF8");
			WriteStreamHeader(jid, host);
			reader = rfactory.createXMLStreamReader(socket.getInputStream(),
					"UTF-8");
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			NetworkError();
		}
	}

	private void CloseStream() {
		try {
			streamInitialized = false;
			stanzaQueue.clear();
			try { Thread.sleep(100); }
			catch (InterruptedException e)
			{ }
			writer.writeEndDocument();
			writer = null;
		} catch (XMLStreamException e) {
			NetworkError();
		}
	}

	private void CloseSocket() {
		try {
			socket.close();
		} catch (IOException e) {
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
				NetworkError();
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
				NetworkError();
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

	private void NetworkError() {

	}

	@Override
	public void ReceiveMessage(LogicNetworkMessageType messageType,
			Object[] args) {
		switch (messageType) {
			case Connect: {
				if (false /*connected*/)
					; //Error
				
			}	break;
			default:
				break;
		}
	}

	public static void main(String[] args) {
		NetworkConnection n = new NetworkConnection();
		try {
			n.Connect("127.0.0.1", 61337);
			n.Initialize("Anon", "localhost");
			Stanza s = new Stanza("message");
			s.attributes.put("to", "me");
			n.stanzaQueue.add(s);
			System.in.read();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
