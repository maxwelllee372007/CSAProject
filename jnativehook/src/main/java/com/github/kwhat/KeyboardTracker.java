package com.github.kwhat;

public class KeyboardTracker {
    private boolean[] keyBoard = new boolean[1000000];
    public KeyboardTracker() {
        keyBoard = new boolean[1000000];
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
