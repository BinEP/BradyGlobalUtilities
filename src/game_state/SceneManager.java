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
	
	public Scene getCurrentScene() {
		return scenes.get(sceneIndex);
	}
	
	public Scene getScene(String name) {
		for (Scene scene : scenes) {
			if (scene.getSceneName().equals(name)) return scene;
		}
		return null;
	}
	
	public void nextScene() {
		sceneIndex++;
		if (scenes.size() == sceneIndex) sceneIndex = 0;
	}
	
	public void setScene(int index) {
		sceneIndex = index;
	}
	
	public void addScene(Scene scene) {
		scenes.add(scene);
	}

}
