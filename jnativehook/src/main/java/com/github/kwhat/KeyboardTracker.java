package com.github.kwhat;

public class KeyboardTracker {
    private boolean[] keyBoard = new boolean[Integer.MAX_VALUE];
    public KeyboardTracker() {
        keyBoard = new boolean[Integer.MAX_VALUE];
    }

    public void pressKey(int keyCode) {
        keyBoard[keyCode] = true;
    }

    public void removeKey(int keyCode) {
        keyBoard[keyCode] = false;
    }

    public boolean[] getKeys() {
        return keyBoard;
    }
}
