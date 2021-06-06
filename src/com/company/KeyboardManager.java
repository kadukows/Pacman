package com.company;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Object for checking state of a key in a frame.
 */
public class KeyboardManager implements KeyListener {
    private final ConcurrentMap<Integer, Boolean> keyToPressed_;

    public KeyboardManager() {
        keyToPressed_ = new ConcurrentHashMap<Integer, Boolean>();

        keyToPressed_.put(KeyEvent.VK_UP, false);
        keyToPressed_.put(KeyEvent.VK_DOWN, false);
        keyToPressed_.put(KeyEvent.VK_LEFT, false);
        keyToPressed_.put(KeyEvent.VK_RIGHT, false);
    }

    /**
     * Attaches KeyStroke interface to a component
     *
     * @param component component to attach to
     */
    public void attachKeyStrokeToComponent(JComponent component) {
        Map<String, Integer> keyStrokeToKeyCode = new HashMap<>();
        keyStrokeToKeyCode.put("UP", KeyEvent.VK_UP);
        keyStrokeToKeyCode.put("DOWN", KeyEvent.VK_DOWN);
        keyStrokeToKeyCode.put("LEFT", KeyEvent.VK_LEFT);
        keyStrokeToKeyCode.put("RIGHT", KeyEvent.VK_RIGHT);

        keyStrokeToKeyCode.forEach((keyStrokeCode, keyCode) -> {
            String pressedEventName = keyStrokeCode + " pressed";

            component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                    KeyStroke.getKeyStroke(keyStrokeCode), pressedEventName
            );

            component.getActionMap().put(pressedEventName, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    keyPressed(keyCode);
                }
            });

            ////////////////////////////////////////

            String releasedEventName = keyStrokeCode + " released";

            component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                    KeyStroke.getKeyStroke("released " + keyStrokeCode), releasedEventName
            );

            component.getActionMap().put(releasedEventName, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    keyReleased(keyCode);
                }
            });
        });
    }

    /**
     * Implementation of KeyListener interface. Does nothing.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Records that specific key is pressed down in a map.
     *
     * @param e key to record
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed(e.getKeyCode());
    }

    /**
     * Records that specific key is pressed down in a map.
     *
     * @param keyCode keycode of key to record
     */
    public void keyPressed(int keyCode) {
        if (keyToPressed_.containsKey(keyCode)) {
            keyToPressed_.put(keyCode, true);
        }
    }

    /**
     * Records that specific key is no longer pressed down.
     *
     * @param e key to record
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keyReleased(e.getKeyCode());
    }

    /**
     * Records that specific key is no longer pressed down.
     *
     * @param keyCode keyCode of key to record
     */
    public void keyReleased(int keyCode) {
        if (keyToPressed_.containsKey(keyCode)) {
            keyToPressed_.put(keyCode, false);
        }
    }

    /**
     * Observer for a map, checks if specific key is currently pressed down.
     *
     * @param key_code key
     * @return true if pressed down, false otherwise
     */
    public boolean isDown(int key_code) {
        return keyToPressed_.getOrDefault(key_code, false);
    }
}
