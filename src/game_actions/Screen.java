package game_actions;

import custom_listeners.BSActionListener;
import custom_listeners.BSDirectionKeyListener;
import custom_listeners.BSKeyListener;

public interface Screen extends BSKeyListener, BSActionListener, BSDirectionKeyListener {
}