package MessageLibrary;

import java.util.Dictionary;
import java.util.Hashtable;

public class gLogicMessageTester implements MessageTester<LogicGUIMessageType> {
	
	private static final Dictionary<LogicGUIMessageType, Class<?>[]> paramDict;
		
	static {
		paramDict = new Hashtable<>();
	}

	@Override
	public boolean Test(LogicGUIMessageType type, Object[] args) {
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
	public String[] ConvertToString(LogicGUIMessageType type, Object[] args) {
		return new String[] {};
	}

}
