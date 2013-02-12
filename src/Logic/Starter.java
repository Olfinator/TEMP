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
		//Create Logic
		logic = new DummyLogic(debug);
		//Create GUI
		gui = new GUIDummy(debug);
		//Create Network
		network = new NetworkDummy(debug);

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
