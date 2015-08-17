package game_actions;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import shapes.interfaces.BSShape;

public class Scene {

	private final List<BSShape> shapesToDraw = new ArrayList<BSShape>();
	private String sceneName = ""; 

	public Scene() {
		// TODO Auto-generated constructor stub
	}
	
	public Scene(String name) {
		sceneName = name;
	}

	public void drawShapes(Graphics2D g2) {
		Graphics2D g;
		synchronized (shapesToDraw) {
			for (BSShape shape : shapesToDraw) {
				g = (Graphics2D) g2.create();
				shape.autoDraw(g);
			}
		}
	}
	
	public final void addShapeToBeDrawn(BSShape shape) {
		synchronized(shapesToDraw) {
			if (!shapesToDraw.contains(shape) && shape != null) shapesToDraw.add(shape);
		}
	}
	
	public String getSceneName() {
		return sceneName;
	}
	
	public BSShape getShape(BSShape shape) {
		return shapesToDraw.get(shapesToDraw.indexOf(shape));
	}
	
//	public static final void addShapeToBeDrawn(BSShape shape, GameState state) {
//		
//	}
	

}
