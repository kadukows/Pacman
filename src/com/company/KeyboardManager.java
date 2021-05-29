package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Object for checking state of a key in a frame.
 */
public class KeyboardManager implements KeyListener {
    private final Map<Integer, Boolean> keyToPressed_;

    public KeyboardManager() {
        keyToPressed_ = new HashMap<Integer, Boolean>();

        keyToPressed_.put(KeyEvent.VK_UP, false);
        keyToPressed_.put(KeyEvent.VK_DOWN, false);
        keyToPressed_.put(KeyEvent.VK_LEFT, false);
        keyToPressed_.put(KeyEvent.VK_RIGHT, false);
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
        if (keyToPressed_.containsKey(e.getKeyCode())) {
            keyToPressed_.put(e.getKeyCode(), true);
        }
    }

    /**
     * Records that specific key is no longer pressed down.
     *
     * @param e key to record
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (keyToPressed_.containsKey(e.getKeyCode())) {
            keyToPressed_.put(e.getKeyCode(), false);
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
