package manager;

import module.*;
import module.snakegame.KeyListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GameManager {
    private static final GameManager gameManager = new GameManager();

    private GameManager() {
        try {
            initGameManager();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static GameManager getInstance() {
        return gameManager;
    }

    private void initGameManager() throws IOException {
        Playable playable = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int selectNum = -1;
        KeyListener keyListener = KeyListener.getInstance();

        while (selectNum != 0) {
            showMenu();
            System.out.print("번호를 입력해 주세요. 종료 하려면 0번을 입력 하세요. : ");

            try {
                selectNum = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                selectNum = -1;
            }

            switch (selectNum) {
                case 1:
                    playable = new TicTacToe();
                    break;
                case 2:
                    playable = new Hanoi();
                    break;
                case 3:
                    playable = new Blackjack();
                    break;
                case 4:
                    playable = new NumberBaseball();
                    break;
                case 5:
                    playable = new SnakeGame(keyListener);
                    break;
                case 6:
                    playable = new OmokJMR();
                    break;
                case 0:
                    System.out.println("시스템을 종료 합니다.");
                    keyListener.unregisterHook();
                    break;
                default:
                    System.out.println("유효하지 않은 명령 입니다.");
                    break;
            }

            if(playable != null) {
                new GameLauncher(playable).init();
            }

            playable = null;
        }
    }

    private void showMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------ GameLit ------------").append("\n");
        Arrays.stream(GameList.values()).map((x) -> x.getNum() + ". " + x.getGameName()).forEach(result -> sb.append(result).append("\n"));
        System.out.println(sb);
    }

}