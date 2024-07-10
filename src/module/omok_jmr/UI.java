package module.omok_jmr;

import java.util.Arrays;
import java.util.Scanner;

public class UI {
    private final Scanner sc = new Scanner(System.in);

    // 선공 할 색 고르기
    public Color startMessage() {
        System.out.println("┌───────────────────────────┐");
        System.out.println("│     omok - jmr version    │");
        System.out.println("└───────────────────────────┘");
        Color choose;
        while (true) {
            System.out.print("백돌(0), 흑돌(1) 중 선공을 골라주세요 : ");
            String input = sc.nextLine();
            if (input.equals("백돌") || input.equals("0")) {
                choose = Color.WHITE;
                break;
            } else if (input.equals("흑돌") || input.equals("1")) {
                choose = Color.BLACK;
                break;
            } else {
                System.out.println("잘못된 입력입니다. 백돌, 흑돌, 0, 1 중에 하나만 입력해주세요");
            }
        }
        System.out.println();
        System.out.println(choose.getName() + "이 선공입니다.");
        return choose;
    }

    // 바둑판 출력
    public void showBoard(Board board) {
        // 바둑판 화면 만들기
        String[][] boardView = new String[board.getBoard().length][board.getBoard().length];
        for (int i = 0; i < boardView.length; i++) {
            for (int j = 0; j < boardView.length; j++) {
                boardView[i][j] = "┼";
            }
        }
        for (int i = 0; i < boardView.length; i++) {
            boardView[i][0] = "├";
            boardView[i][boardView[0].length-1] = "┤";
        }
        for (int j = 0; j < boardView[0].length; j++) {
            boardView[0][j] = "┬";
            boardView[boardView.length-1][j] = "┴";
        }
        boardView[0][0] = "┌";
        boardView[0][boardView[0].length-1] = "┐";
        boardView[boardView.length-1][0] = "└";
        boardView[boardView.length-1][boardView[0].length-1] = "┘";

        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[0].length; j++) {
                Stone stone = board.getStone(i, j);
                if (stone != null) {
                    boardView[i][j] = stone.toString();
                }
            }
        }

        for (String[] row : boardView) {
            for (int j = 0; j < row.length; j++) {
                System.out.print(row[j]);
                if(j == row.length-1) continue;
                System.out.print("--");
            }
            System.out.println();
        }
    }

    public int[] putStoneMessage(Color color) {
        int[] locate = new int[2];
        while (true) {
            System.out.print(color.getName() + "이 둘 차례입니다. 착수할 위치를 입력해주세요 : ");
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            if(inputArr.length!=2){
                System.out.println("올바른 입력이 아닙니다. [ ex) 11 12 ] 와 같은 형태로 입력해야 합니다.");
                continue;
            }

        }
    }


}
