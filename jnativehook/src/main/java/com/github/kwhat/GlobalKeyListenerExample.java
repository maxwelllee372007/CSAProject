package com.github.kwhat;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListenerExample implements NativeKeyListener {
    private KeyboardTracker keyBoard;
    public GlobalKeyListenerExample() {
        super();
        keyBoard = new KeyboardTracker();
    }
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        keyBoard.pressKey(e.getKeyCode());
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        keyBoard.removeKey(e.getKeyCode());
    }

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