package module.snakegame;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener {
    public KeyListener() {
        try {
            // JNativeHook 라이브러리 초기화 및 등록
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        // 이벤트 리스너 추가
        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_UP:
                System.out.println("Up arrow key pressed");
                break;
            case NativeKeyEvent.VC_DOWN:
                System.out.println("Down arrow key pressed");
                break;
            case NativeKeyEvent.VC_LEFT:
                System.out.println("Left arrow key pressed");
                break;
            case NativeKeyEvent.VC_RIGHT:
                System.out.println("Right arrow key pressed");
                break;
            case NativeKeyEvent.VC_ESCAPE:
                System.out.println("ESC key pressed. Exiting...");
                try {
                    GlobalScreen.unregisterNativeHook();
                } catch (NativeHookException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        // Do nothing
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        // Do nothing
    }

    public void stop() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }
}
