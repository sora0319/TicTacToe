package module;

import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.ptr.IntByReference;

import module.snakegame.Kernel32;
import module.snakegame.KeyListener;
import module.snakegame.SnakeGameProcess;

import java.util.ArrayList;
import java.util.List;

public class SnakeGame implements Playable{

    private static int originalConsoleMode;

    // console
    private static void setupWindowsConsole() {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        IntByReference consoleMode = new IntByReference();
        int hStdin = kernel32.GetStdHandle(-10); // STD_INPUT_HANDLE
        if (kernel32.GetConsoleMode(hStdin, consoleMode)) {
            originalConsoleMode = consoleMode.getValue();
            int newConsoleMode = originalConsoleMode & ~0x4; // 비에코 모드 비활성화
            kernel32.SetConsoleMode(hStdin, newConsoleMode);
        }
    }

    private static void restoreWindowsConsole() {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        int hStdin = kernel32.GetStdHandle(-10); // STD_INPUT_HANDLE
        kernel32.SetConsoleMode(hStdin, originalConsoleMode);
    }

    @Override
    public void init() {
        setupWindowsConsole();
        playGame();
        restoreWindowsConsole();
        System.out.println("end snake game");
    }

    @Override
    public void playGame() {

        SnakeGameProcess process = new SnakeGameProcess();
        process.start();


    }
}
