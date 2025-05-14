package com.github.aakm.keyboardTracker;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.github.aakm.Main;

public class KeyListener implements NativeKeyListener {
    private Keyboard keyBoard;
    public KeyListener() {
        super();
        keyBoard = new Keyboard();
    }
    
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + (e.getKeyCode()));
        keyBoard.pressKey(e.getKeyCode());
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + (e.getKeyCode()));
        keyBoard.removeKey(e.getKeyCode());
    }
    
    public boolean[] getKeys() {
        return keyBoard.getKeys();
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    // public static void main(String[] args) {
    //     try {
    //         GlobalScreen.registerNativeHook();
    //     } catch (NativeHookException ex) {
    //         System.err.println("There was a problem registering the native hook.");
    //         System.err.println(ex.getMessage());
    //         System.exit(1);
    //     }

    //     GlobalScreen.addNativeKeyListener(new GlobalKeyListenerExample());
    // }
}