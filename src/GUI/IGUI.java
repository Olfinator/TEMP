package GUI;
import Logic.ILogic;
import Logic.LogicGUIMessageType;

public abstract class IGUI {
	private ILogic logic;

	private void SendMessage(GUIMessageType messageType, Object[] args) {
		logic.ReceiveMessage(messageType, args);
	}

	public void SetLogic(ILogic l) {
		if (logic != null)
			throw new RuntimeException("May only be executed once!");
		logic = l;
	}

	public abstract void ReceiceMessage(LogicGUIMessageType message, Object[] args);
}
