package com.github.kwhat;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Main {
    private static KeyListener keyListener = new KeyListener();
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(keyListener);
        
        while (!keyListener.getKeys()[1]) { 
            System.out.println("test test est test");
            Thread.sleep(100);
        }
    }
}