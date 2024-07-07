package module.snakegame;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.Wincon;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public interface Kernel32 extends StdCallLibrary {
    Kernel32 INSTANCE = Native.load("kernel32", Kernel32.class, W32APIOptions.DEFAULT_OPTIONS);

    int GetStdHandle(int nStdHandle);
    boolean GetConsoleMode(int hConsoleHandle, IntByReference lpMode);
    boolean SetConsoleMode(int hConsoleHandle, int dwMode);

}
