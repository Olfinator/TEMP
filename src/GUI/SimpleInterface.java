package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.Event;

import Logic.LogicGUIMessageType;

public class SimpleInterface extends JFrame implements IGUI {

	private JPanel panel;
	private JButton senden;
	private JTextField text;
	private JTextArea anzeigen;
	Dimension test;

	public SimpleInterface() {
		this.setSize(1024, 768);
		test = new Dimension(800, 600);
		panel = new JPanel();
		panel.setSize(1024, 768);
		senden = new JButton();
		senden.setSize(150, 75);
		senden.setText("Click Me");
		text = new JTextField();
		text.setLocation(400, 300);
		text.setMinimumSize(test);
		text.setText("HALLO...........");
		anzeigen = new JTextArea();
		anzeigen.setText("Hier ist ein langer Beispieltext, damit das feld groﬂ genung ist");
		panel.add(senden);
		panel.add(text);
		panel.add(anzeigen);
		getContentPane().add(panel);
		//pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		SimpleInterface g = new SimpleInterface();
	}

	@Override
	public void ReceiceMessage(LogicGUIMessageType message, Object[] args) {
		// TODO Auto-generated method stub

	}
}
