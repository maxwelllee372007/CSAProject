package com.github.kwhat.keyboardTracker;

public class Keyboard {
    private boolean[] keyBoard = new boolean[100000];
    public Keyboard() {
        keyBoard = new boolean[100000];
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
