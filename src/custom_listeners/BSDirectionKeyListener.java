package custom_listeners;

public interface BSDirectionKeyListener {

	/**
	 * What to set variables to when upKey is pressed. Called by keyPressed
	 */
	public void up();

	/**
	 * What to set variables to when upKey is pressed Called by keyPressed
	 */
	public void down();

	/**
	 * What to set variables to when upKey is pressed Called by keyPressed
	 */
	public void left();

	/**
	 * What to set variables to when upKey is pressed Called by keyPressed
	 */
	public void right();

	/**
	 * What to set variables to when upKey is released Called by keyReleased
	 */
	public void upReleased();

	/**
	 * What to set variables to when downKey is released Called by keyReleased
	 */
	public void downReleased();

	/**
	 * What to set variables to when leftKey is released Called by keyReleased
	 */
	public void leftReleased();

	/**
	 * What to set variables to when rightKey is released Called by keyReleased
	 */
	public void rightReleased();
}
