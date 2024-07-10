package module.omok_jmr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameClient {

    Board board = new Board();
    UI ui = new UI();
    Color first;    //선공 색깔
    List<Stone> history = new ArrayList<>();    //기보

    public void run() {
        first = ui.startMessage();
        // 게임 진행
        Color now = first;
        while (true) {
            ui.showBoard(board);
            // 돌 착수할때 잘못된 위치에 둘 수도 있어서 무한반복
            while (true) {
                int[] locate = ui.putStoneMessage(now);
                Stone nowStone = new Stone(now, locate[0], locate[1]);
                if (!board.putStone(nowStone)) {
                    System.out.println("이미 돌이 있는 자리입니다.");
                    continue;
                }
                history.add(nowStone);
                // 여기에 오목 성공 판단하는 기능 추가
                System.out.println(Arrays.toString(countNeighbor(nowStone)));
                // 흑 백 교대
                now = now.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
                break;
            }
        }
    }

    public int[] countNeighbor(Stone stone) {
        int[][] directions = {{1,0}, {-1, 1}, {1,1}, {0,1}}; //이동할 방향 →, ↖, ↗, ↑
        int[] result = new int[4];

        for (int i = 0; i < directions.length; i ++) {
            int[] direction = directions[i];
            int count = 1;

            int newX = stone.getX();
            int newY = stone.getY();
            // 디렉션 정방향으로 우선 끝까지 이동(자기 돌인 경우에만)
            while (true) {
                newX += direction[0];
                newY += direction[1];
                if (newX < 0 || newX >= board.getBoard().length || newY < 0 || newY >= board.getBoard()[0].length) {
                    break;
                }
                Stone next = board.getStone(newX, newY);
                if (next == null || !next.getColor().equals(stone.getColor())) {    // 다음 탐색 칸에 톨이 없거나 다른색 일경우 break
                    break;
                }
                count++;

            }

            newX = stone.getX();
            newY = stone.getY();
            // 디렉션 역방향으로 끝까지 이동
            while (true) {
                newX -= direction[0];
                newY -= direction[1];
                if (newX < 0 || newX >= board.getBoard().length || newY < 0 || newY >= board.getBoard()[0].length) {
                    break;
                }
                Stone next = board.getStone(newX, newY);
                if (next == null || !next.getColor().equals(stone.getColor())) {    // 다음 탐색 칸에 톨이 없거나 다른색 일경우 break
                    break;
                }
                count++;
            }
            result[i] = count;
        }


        return result;
    }


}
