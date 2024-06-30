package module;

import java.util.Scanner;
import java.util.Stack;

public class Hanoi implements Playable {

    private Stack<Integer>[] towers = new Stack[3];
    private int numDisks;
    private int moveCount = 0;

    @Override
    public void init() {
        playGame();
    }

    @Override
    public void playGame() {
        playHanoi();
    }

    private void playHanoi() {
        Scanner in = new Scanner(System.in);
        System.out.print("원반의 갯수를 입력해주세요: ");
        while (true) {
            try {
                numDisks = Integer.parseInt(in.nextLine());
                if (numDisks > 0) {
                    break;
                } else {
                    System.out.print("양의 정수를 입력해주세요: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("유효한 숫자를 입력해주세요: ");
            }
        }

        towers[0] = new Stack<>();
        towers[1] = new Stack<>();
        towers[2] = new Stack<>();

        for (int i = numDisks; i > 0; i--) {
            towers[0].push(i);
        }

        displayTowers();

        while (true) {
            System.out.print("옮길 원반의 출발 타워와 도착 타워를 입력하세요 (예: A C): ");
            String input = in.nextLine().trim().toUpperCase();

            if (input.length() != 3 || input.charAt(1) != ' ' ||
                    input.charAt(0) < 'A' || input.charAt(0) > 'C' ||
                    input.charAt(2) < 'A' || input.charAt(2) > 'C') {
                System.out.println("잘못된 입력입니다. 'A B'와 같은 형식으로 입력해주세요.");
                continue;
            }

            char from = input.charAt(0);
            char to = input.charAt(2);

            int source = from - 'A';
            int destination = to - 'A';

            if (isValidMove(source, destination)) {
                moveDisk(source, destination);
                moveCount++;
                if (isGameWon()) {
                    System.out.println("축하합니다! 하노이탑을 완성하셨습니다!!");
                    System.out.println("총 " + moveCount + "번 움직였습니다.");
                    break;
                }
            } else {
                System.out.println("잘못된 움직임입니다. 다시 시도하세요.");
            }
        }

    }

    private boolean isValidMove(int source, int destination) {
        if (source < 0 || source > 2 || destination < 0 || destination > 2 || towers[source].isEmpty()) {
            return false;
        }
        if (towers[destination].isEmpty() || towers[source].peek() < towers[destination].peek()) {
            return true;
        }
        return false;
    }

    private void moveDisk(int source, int destination) {
        int disk = towers[source].pop();
        towers[destination].push(disk);
        displayTowers();
        System.out.println("원반 [" + disk + "] 을 " + (char)('A' + source) + " 에서 " + (char)('A' + destination) +"로 이동" + "\n");
    }

    private void displayTowers() {
        int maxWidth = numDisks * 2 + 1;
        int boxWidth = maxWidth + 4;
        String emptySpace = " ".repeat(boxWidth);

        StringBuilder[] display = new StringBuilder[numDisks + 1];
        for (int i = 0; i <= numDisks; i++) {
            display[i] = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                if (i < towers[j].size()) {
                    int diskSize = towers[j].get(i) * 2 - 1;
                    int padding = (boxWidth - diskSize) / 2;
                    display[i].append(" ".repeat(padding))
                            .append("█".repeat(diskSize))
                            .append(" ".repeat(padding));
                } else {
                    int pegPadding = (boxWidth - 1) / 2;
                    display[i].append(" ".repeat(pegPadding))
                            .append("║")
                            .append(" ".repeat(pegPadding));
                }
                display[i].append("   ");
            }
        }

        for (int i = numDisks; i >= 0; i--) {
            System.out.println(display[i].toString());
        }


        String boxLineTop = "┌" + "─".repeat(boxWidth - 2) + "┐";
        String boxLineBottom = "└" + "─".repeat(boxWidth - 2) + "┘";

        System.out.println(boxLineTop + "   " + boxLineTop + "   " + boxLineTop);

        String towerA = "│" + " ".repeat((boxWidth - 3) / 2) + "A" + " ".repeat((boxWidth - 3) / 2) + "│";
        String towerB = "│" + " ".repeat((boxWidth - 3) / 2) + "B" + " ".repeat((boxWidth - 3) / 2) + "│";
        String towerC = "│" + " ".repeat((boxWidth - 3) / 2) + "C" + " ".repeat((boxWidth - 3) / 2) + "│";

        System.out.println(towerA + "   " + towerB + "   " + towerC);

        System.out.println(boxLineBottom + "   " + boxLineBottom + "   " + boxLineBottom);
        System.out.println();
    }

    private boolean isGameWon() {
        return towers[2].size() == numDisks;
    }

}

