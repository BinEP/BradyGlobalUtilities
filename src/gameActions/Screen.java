package gameActions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.peer.MouseInfoPeer;

import custom_listeners.BSActionListener;
import custom_listeners.BSGameListener;
import custom_listeners.BSKeyListener;

//public interface Screen extends KeyListener, ActionListener, MouseListener, FocusListener, BSGameListener {
public interface Screen extends BSKeyListener, BSActionListener {

}
