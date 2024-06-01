package manager;

import module.TicTacToe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GameManager {
    public GameManager() throws IOException{
        initGameManager();
    }

    private void initGameManager() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int selectNum = -1;

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
                    new TicTacToe();
                    break;
                case 0:
                    System.out.println("시스템을 종료 합니다.");
                    break;
                default:
                    System.out.println("유효하지 않은 명령 입니다.");
                    break;
            }
        }
    }

    private void showMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------ GameLit ------------").append("\n");
        Arrays.stream(GameList.values()).map((x) -> x.getNum() + ". " + x.getGameName()).forEach(result -> sb.append(result).append("\n"));
        System.out.println(sb);
    }
}