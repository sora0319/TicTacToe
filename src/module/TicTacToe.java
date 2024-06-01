package module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TicTacToe {
    private final int w = 4;
    private final int h = 4;
    private int countTimes;
    private boolean flag = true;
    private final String[][] board = new String[w][h];

    public TicTacToe() throws IOException {
        this.countTimes = 9;
        init();
        showBoard();
        playTicTacToe();
    }

    private void init() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(i == 0) {
                    board[i][j] = String.valueOf(j);
                    continue;
                }
                if(j == 0) {
                    board[i][j] = String.valueOf(i);
                    continue;
                }

                board[i][j] = " ";
            }
        }
    }

    private void showBoard() {
        for (int i = 0; i < h; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        showCurrentTurn();
    }

    private void playTicTacToe() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int i;
        int j;
        do {
            System.out.println("좌표를 입력해 주세요.");
            System.out.print(": ");
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());

            try {
                if (isAlreadyDraw(i, j)) {
                    throw new IllegalArgumentException("유효 하지 않은 입력 입니다. 다시 입력 해주세요.");
                }
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
                showBoard();
                continue;
            }

            draw_OX(i, j);

            showBoard();
            if(--countTimes == 0) break;
            System.out.println(countTimes);
        } while (!isWin());

        if(!isDraw()) {
            showWinner();
        } else {
            System.out.println("Draw!!");
        }
    }


    private void draw_OX(int i, int j) {
        this.board[i][j] = this.flag ? "O" : "X";
        this.flag = !flag;
    }

    private boolean isWin() {
        String o = "O";
        String x = "X";
        if (o.equals(board[1][1]) || x.equals(board[1][1])) {
            if (board[1][1].equals(board[1][2]) && board[1][2].equals(board[1][3])) return true;
            if (board[1][1].equals(board[2][1]) && board[2][1].equals(board[3][1])) return true;
            if (board[1][1].equals(board[2][2]) && board[2][2].equals(board[3][3])) return true;
        }
        if (o.equals(board[2][1]) || x.equals(board[2][1])) {
            if (board[2][1].equals(board[2][2]) && board[2][2].equals(board[2][3])) return true;
        }
        if (o.equals(board[3][1]) || x.equals(board[3][1])) {
            if (board[3][1].equals(board[3][2]) && board[3][2].equals(board[3][3])) return true;
        }
        if (o.equals(board[1][2]) || x.equals(board[1][2])) {
            if (board[1][2].equals(board[2][2]) && board[2][2].equals(board[3][2])) return true;
        }
        if (o.equals(board[1][3]) || x.equals(board[1][3])) {
            if (board[1][3].equals(board[2][3]) && board[2][3].equals(board[3][3])) return true;
            if (board[1][3].equals(board[2][2]) && board[2][2].equals(board[3][1])) return true;
        }

        return false;
    }

    public boolean isDraw() {
        if(this.countTimes == 0) {
            return true;
        }
        return false;
    }
    private void showWinner() {
        String winner = !this.flag ? "< O >" : "< X >";
        System.out.println("----------------------");
        System.out.println(winner + " is Winner!!");
        System.out.println("----------------------");
    }

    private void showCurrentTurn() {
        if(!isWin()) {
            String nowPlayer = this.flag ? "< O >" : "< X >";
            System.out.println(nowPlayer + "'s turn.");
        } else {
            System.out.println("Game Set!!");
        }
    }

    private boolean isAlreadyDraw(int i, int j) {
        if(board[i][j].equals("O") || board[i][j].equals("X")) return true;
        return false;
    }
}

