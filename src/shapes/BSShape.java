package shapes;

import java.awt.AWTEvent;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;

import utility_classes.BSHashMap;
import custom_listeners.BSAllListeners;
import listener_control.ListenerManager;
import events.GameEvent;
import events.ShapeListenerManager.Movement.Direction;
import events.ShapeListenerManager.Trigger;

public interface BSShape extends Shape, Trigger {

	public void setMovement(int dx, int dy);
	public void setDirection(Direction d);
}
