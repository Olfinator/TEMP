package GUI;
import Logic.LogicGUIMessageType;

public interface IGUI {
	public void ReceiceMessage(LogicGUIMessageType message, Object[] args);
}