package MessageLibrary;

import java.util.Dictionary;
import java.util.Hashtable;

public final class NetworkMessageTester implements MessageTester<NetworkMessageType> {

	private static final Dictionary<NetworkMessageType, Class<?>[]> paramDict;

	static {
		paramDict = new Hashtable<>();
	}

	@Override
	public boolean Test(NetworkMessageType type, Object[] args) {
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
	public String[] ConvertToString(NetworkMessageType type, Object[] args) {
		return new String[] {};
	}
}
