package module.snakegame;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

//import java.util.Observable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class KeyListener implements NativeKeyListener {
    private static KeyListener instance;
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    // private 생성자로 외부에서의 인스턴스 생성 방지
    private KeyListener() {
        try {
            // JNativeHook 라이브러리 초기화 및 등록
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    // 싱글톤 인스턴스를 반환하는 메서드
    public static synchronized KeyListener getInstance() {
        if (instance == null) {
            instance = new KeyListener();
        }
        return instance;
    }

    // 옵저버 추가
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    //옵저버 제거
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // 옵저버에게 알림 전달
    private void notifyObservers(Object arg) {
        for (Observer observer : observers) {
            observer.update(arg);
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        notifyObservers(e.getKeyCode());
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        // Do nothing
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        // Do nothing
    }

    public void registerHook(){
        // 이벤트 리스너 등록
        GlobalScreen.addNativeKeyListener(this);
    }

    public void removeHook() {
        GlobalScreen.removeNativeKeyListener(this);
    }

    public void unregisterHook() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }
}
