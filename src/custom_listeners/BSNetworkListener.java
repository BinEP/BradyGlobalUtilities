package custom_listeners;

public interface BSNetworkListener extends SuperListener {
	public void receivedMessage(String property, Object data);
}
