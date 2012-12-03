package Network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class NetworkConnection implements INetwork{
	private Socket socket;
	private XMLOutputFactory factory;
	private XMLStreamWriter writer;
	
	public NetworkConnection() {
		factory = XMLOutputFactory.newInstance();
		try {
			Connect("127.0.0.1", 61337);
			Initialize("Anon", "localhost");
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
				}
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		}
	}
	
	private void OpenStream(String jid, String server) {
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
	
	private void Initialize(String jid, String host) {
		if (socket == null || socket.isClosed()) {
			return;
		}
		try {
			writer = factory.createXMLStreamWriter(socket.getOutputStream(), "UTF8");
			OpenStream("Anon", "localhost");
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void Connect(String host, int port) throws UnknownHostException, IOException {
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
	
	private void Send(String s) throws IOException {
		try {
			Send(s.getBytes("UTF8"));
		} catch (UnsupportedEncodingException e) {
			System.out.print("WTF?");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new NetworkConnection();
	}
}
