package MessageLibrary;

import java.util.Dictionary;
import java.util.Hashtable;

public class GUIMessageTester implements MessageTester<GUIMessageType> {
	
	private static final Dictionary<GUIMessageType, Class<?>[]> paramDict;
	
	static {
		paramDict = new Hashtable<>();
		GUIMessageType.paramDefs(paramDict);
	}

	@Override
	public boolean Test(GUIMessageType type, Object[] args) {
		try {
			Class<?>[] params = paramDict.get(type);
			if (args.length < params.length) {
				return false;
			}
			for (int i = 0; i < params.length; i++) {
				if (args[i] != null && !args[i].getClass().equals(params[i])) {
					return false;
				}
			}
			return true;
		} catch (NullPointerException e) {
			return true;
		}
	}

	@Override
	public String[] ConvertToString(GUIMessageType type, Object[] args) {
		return new String[] { } ;
	}

}
