import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameManager {

    public GameManager() throws IOException {
        initGameManager();
    }

    private void initGameManager() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int selectNum = -1;

        while(selectNum != 0) {
            System.out.print("번호를 입력해 주세요. : ");
            selectNum = Integer.parseInt(br.readLine());

            switch (selectNum) {
                case 1 : new TicTacToe(); break;
                default: break;
            }
        }
    }
}