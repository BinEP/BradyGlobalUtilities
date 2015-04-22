package game_state;

import java.lang.reflect.AnnotatedType;

import custom_listeners.BSActionListener;
import custom_listeners.BSDirectionKeyListener;
import custom_listeners.BSFocusListener;
import custom_listeners.BSGameListener;
import custom_listeners.BSKeyListener;
import custom_listeners.BSMouseListener;
import custom_listeners.SuperListener;
import listener_control.ListenerManager;

public class ListenerAutoAdd {

	public static void addListeners(SuperListener game) {

		AnnotatedType[] at = game.getClass().getAnnotatedInterfaces();
		for (AnnotatedType t : at) {

			String typeName = t.getType().getTypeName().substring(17);
			switch (typeName) {

			case "BSActionListener":
				ListenerManager.addActionListener((BSActionListener) game);
				break;
			case "BSDirectionKeyListener":
				ListenerManager.addDirectionKeyListener((BSDirectionKeyListener) game);
				break;
			case "BSFocusListener":
				ListenerManager.addFocusListener((BSFocusListener) game);
				break;
			case "BSGameListener":
				ListenerManager.addGameListener((BSGameListener) game);
				break;
			case "BSKeyListener":
				ListenerManager.addKeyListener((BSKeyListener) game);
				break;
			case "BSMouseListener":
				ListenerManager.addMouseListener((BSMouseListener) game);
				break;
			}
		}
	}
}