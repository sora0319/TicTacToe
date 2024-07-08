package module.snakegame;

import com.sun.jna.ptr.IntByReference;


public class ConsoleMode {
    private static int originalConsoleMode;

    // console
    public static void setupWindowsConsole() {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        IntByReference consoleMode = new IntByReference();
        int hStdin = kernel32.GetStdHandle(-10); // STD_INPUT_HANDLE
        if (kernel32.GetConsoleMode(hStdin, consoleMode)) {
            originalConsoleMode = consoleMode.getValue();
            int newConsoleMode = originalConsoleMode & ~0x4; // 비에코 모드 비활성화
            kernel32.SetConsoleMode(hStdin, newConsoleMode);
        }
    }

    public static void restoreWindowsConsole() {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        int hStdin = kernel32.GetStdHandle(-10); // STD_INPUT_HANDLE
        kernel32.SetConsoleMode(hStdin, originalConsoleMode);
    }
}
