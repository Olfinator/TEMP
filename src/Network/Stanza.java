package Network;

import java.util.Dictionary;
import java.util.Hashtable;

public class Stanza {
	public String name;
	public Dictionary<String, String> attributes;
	public Object body;
	public Dictionary<String, String> namespaces;

	public Stanza(String n) {
		name = n;
		attributes = new Hashtable<>();
		namespaces = new Hashtable<>();
	}
}
