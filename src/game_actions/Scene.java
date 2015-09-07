package game_actions;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import shapes.interfaces.BSShape;

public class Scene {

	private final List<BSShape> shapesToDraw = new ArrayList<BSShape>();
	private String sceneName = ""; 

	public Scene() {
	}
	
	public Scene(String name) {
		sceneName = name;
	}

	public void drawShapes(Graphics2D g2) {
		for (BSShape shape : shapesToDraw) {
			shape.autoDraw((Graphics2D) g2.create());
		}
	}
	
	public final void addShapeToBeDrawn(BSShape shape) {
		synchronized(shapesToDraw) {
			if (!shapesToDraw.contains(shape) && shape != null) shapesToDraw.add(shape);
		}
	}
	
	public final void clearShapes() {
		shapesToDraw.clear();
	}
	
	public String getSceneName() 				{return sceneName;		}
	
	public BSShape getShape(BSShape shape) 		{return shapesToDraw.get(shapesToDraw.indexOf(shape));		}
}
