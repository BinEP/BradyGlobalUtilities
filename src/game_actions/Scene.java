package game_actions;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import shapes.interfaces.BSShape;

public class Scene {

	private static final List<BSShape> shapesToDraw = new ArrayList<BSShape>();

	public Scene() {
		// TODO Auto-generated constructor stub
	}

	public void drawShapes(Graphics2D g) {
		synchronized (shapesToDraw) {
			for (BSShape shape : shapesToDraw) {
				shape.autoDraw(g);
			}
		}
	}
	
	public static final void addShapeToBeDrawn(BSShape shape) {
		synchronized(shapesToDraw) {
			if (!shapesToDraw.contains(shape)) shapesToDraw.add(shape);
		}
	}
	
//	public static final void addShapeToBeDrawn(BSShape shape, GameState state) {
//		
//	}
	

}
