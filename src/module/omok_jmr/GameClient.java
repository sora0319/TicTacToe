package module.omok_jmr;

import java.util.ArrayList;
import java.util.List;

public class GameClient{

    Board board = new Board();
    UI ui = new UI();
    Color first;    //선공 색깔
    List<Stone> history = new ArrayList<>();    //기보

    public void run() {
        first = ui.startMessage();
        board.putStone(new Stone(Color.BLACK, 5, 5));
        ui.showBoard(board);
        while (true) {
            ui.showBoard(board);

        }
    }


}
