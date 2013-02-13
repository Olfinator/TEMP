package MessageLibrary;

import java.util.Dictionary;
import java.util.Hashtable;

public class nLogicMessageTester implements MessageTester<LogicNetworkMessageType> {

	private static final Dictionary<LogicNetworkMessageType, Class<?>[]> paramDict;
	
	static {
		paramDict = new Hashtable<>();
	}
	
	@Override
	public boolean Test(LogicNetworkMessageType type, Object[] args) {
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
	public String[] ConvertToString(LogicNetworkMessageType type, Object[] args) {
		return new String[] {};
	}

}
