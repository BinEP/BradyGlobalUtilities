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
	
	public static Scene getCurrentScene() {
		return scenes.get(sceneIndex);
	}
	
	public static Scene getScene(String name) {
		for (Scene scene : scenes) {
			if (scene.getSceneName().equals(name)) return scene;
		}
		return null;
	}
	
	public static void setScene(String name) {
		int i = 0;
		for (Scene scene : scenes) {
			if (scene.getSceneName().equals(name)) sceneIndex = i;
			i++;
		}
	}
	
	public static void nextScene() {
		sceneIndex++;
		if (scenes.size() == sceneIndex) sceneIndex = 0;
	}
	
	public static void setScene(int index) {
		sceneIndex = index;
	}
	
	public static void addScene(Scene scene) {
		scenes.add(2, scene);
	}
	
	public static int getSceneIndex() {
		return sceneIndex;
	}
	
	public static int getNumberOfScenes() {
		return scenes.size();
	}
	
	public void addSceneCustom(Scene scene) {
		scenes.add(scene);
	}
}
