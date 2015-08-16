package game_state;

import java.util.ArrayList;
import java.util.List;

import game_actions.Scene;

public class SceneManager {

	
	private static final List<Scene> scenes = new ArrayList<Scene>();
	private static int sceneIndex = 0;
	
	public SceneManager() {
		scenes.add(new Scene());
	}

}
