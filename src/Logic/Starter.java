package Logic;

import GUI.GUIDummy;
import GUI.IGUI;
import Network.INetwork;
import Network.NetworkDummy;

public class Starter {
	static ILogic logic;
	static IGUI gui;
	static INetwork network;
	static boolean debug;
	
	public static void main(String[] args) {
		debug = true;
		Layer.setLogging(debug);
		//Create Logic
		logic = new DummyLogic();
		//Create GUI
		gui = new GUIDummy();
		//Create Network
		network = new NetworkDummy();

		logic.SetThread(Thread.currentThread());
		logic.SetGui(gui);
		logic.SetNetwork(network);

		//Sleep till Logic Interupts
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) { }

		network.OnClose();
		gui.OnClose();
		logic.OnClose();

		System.exit(0);
	}
}
