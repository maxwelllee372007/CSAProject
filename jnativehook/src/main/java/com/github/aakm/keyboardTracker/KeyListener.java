package com.github.aakm.keyboardTracker;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

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

    public double getNumericalInput(int confirmKey, int deleteKey) {
        double input = 0.0;
        while (!getKeys()[confirmKey]) { // TODO: double check if has correct functionality
            StringBuilder inputString = new StringBuilder(); 
            for (int i = 0; i < getKeys().length; i++) {
                if (getKeys()[i]) {
                    inputString.append(NativeKeyEvent.getKeyText(i));
                }
            }
            try {
                input = Double.parseDouble(inputString.toString());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }
            if (getKeys()[deleteKey]) {
                inputString.deleteCharAt(inputString.length() - 1);
            }
        }
        return input;
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