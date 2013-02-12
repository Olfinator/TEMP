package GUI;
import Logic.ILogic;
import Logic.LogicGUIMessageType;

public abstract class IGUI {
	private ILogic logic;

	private final void SendMessage(GUIMessageType messageType, Object[] args) {
		logic.ReceiveMessage(messageType, args);
	}

	public final void SetLogic(ILogic l) {
		if (logic != null)
			throw new RuntimeException("May only be executed once!");
		logic = l;
	}

	public abstract void ReceiceMessage(LogicGUIMessageType message, Object[] args);

	public abstract void OnClose();
}
