package module.omok_jmr;

import java.util.ArrayList;
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

                // 흑 백 교대
                now = now.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
                break;
            }
        }
    }


}
