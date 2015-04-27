package events;

public interface DataEvent {
	public abstract Object getData();
	public abstract String getMessage();
	public abstract void triggerEvent();
}
