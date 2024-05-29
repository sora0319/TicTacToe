import java.util.Arrays;

public class Board {
    private int w = 3;
    private int h = 3;
    private String[][] board = new String[w][h];

    public Board() {
        initBoard();
    }

    void initBoard() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                board[i][j] = " ";
            }
        }
    }

    void showBoard() {
        for (int i = 0; i < h; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}
