package manager;

public class GameUtil {

    public static void waitSecond(int Time) {
        try {
            for (int i = Time; i > 0; i--) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
