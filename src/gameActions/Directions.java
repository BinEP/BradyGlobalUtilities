package gameActions;

public abstract class Directions extends Game {

	private static final long serialVersionUID = 1018029021558999847L;
	
	public abstract void up();
	public abstract void down();
	public abstract void left();
	public abstract void right();
	
	public abstract void upReleased();
	public abstract void downReleased();
	public abstract void leftReleased();
	public abstract void rightReleased();
}
