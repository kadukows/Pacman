package com.company;

import javax.swing.text.StyledEditorKit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KeyboardManager implements KeyListener {
    private Map<Integer, Boolean> keyToPressed_;

    public KeyboardManager() {
        keyToPressed_ = new HashMap<Integer, Boolean>();

        keyToPressed_.put(KeyEvent.VK_UP, false);
        keyToPressed_.put(KeyEvent.VK_DOWN, false);
        keyToPressed_.put(KeyEvent.VK_LEFT, false);
        keyToPressed_.put(KeyEvent.VK_RIGHT, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (keyToPressed_.containsKey(e.getKeyCode())) {
            keyToPressed_.put(e.getKeyCode(), true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keyToPressed_.containsKey(e.getKeyCode())) {
            keyToPressed_.put(e.getKeyCode(), false);
        }
    }

    public boolean isDown(int key_code) {
        return keyToPressed_.getOrDefault(key_code, false);
    }
}
