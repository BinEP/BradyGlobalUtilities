package events;

public class GameData implements DataEvent {

	private Object data;
	private String message;
	
	public GameData(Object data, String message) {
		this.data = data;
		this.message = message;
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void triggerEvent() {
		System.out.println(message);
	}
}