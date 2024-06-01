import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Board {
    private final int w = 3;
    private final int h = 3;
    private boolean flag = true;
    private final String[][] board = new String[w][h];

    public Board() throws IOException {
        initBoard();
        showBoard();
        playTicTacToe();
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

    void playTicTacToe() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int i;
        int j;
        while(!isWin()) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());

            draw_OX(i, j);
        }
    }

    void draw_OX(int i, int j) {
        this.board[i][j] = this.flag ? "O" : "X";
        this.flag = !flag;
    }

    boolean isWin() {
        if (board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2])) return true;
        if (board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2])) return true;
        if (board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2])) return true;
        if (board[0][0].equals(board[1][0]) && board[1][0].equals(board[1][2])) return true;
        if (board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1])) return true;
        if (board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2])) return true;
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) return true;
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) return true;

        return false;
    }
}

