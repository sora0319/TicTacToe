package module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import static manager.GameUtil.waitSecond;

public class Omok implements Playable {
    private final int w = 16;
    private final int h = 16;
    private int countTimes;
    private boolean flag = true;
    private final String[][] board = new String[w][h];

    public Omok() {
        this.countTimes = 225;
    }

    private final int[] dx = {0, 1, 1, 1};
    private final int[] dy = {1, 1, 0, -1};

    @Override
    public void init() {
        initializeBoard();
        playGame();
    }

    @Override
    public void playGame() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int selectNum = 0;

            while (true) {
                System.out.println("게임 모드를 선택하세요.");
                System.out.println("1. 오목(룰X)");
                System.out.println("2. 오목(일반룰)_미구현 <- 구현예정..ㅠㅠ");
                System.out.println("3. 오목(렌주룰)_미구현 <- 구현예정..ㅠㅠ");

                try {
                    selectNum = Integer.parseInt(br.readLine());
                    if (selectNum == 1) {
                        System.out.println("오목을 시작합니다.");
                        break;
                    } else {
                        System.out.println("미구현된 모드입니다. 다시 선택해 주세요.");
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            ready();
            showBoard();
            playOmok();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showBoard() {
        for (int i = 0; i < h; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0) {
                    board[i][j] = String.valueOf(j);
                    continue;
                }
                if (j == 0) {
                    board[i][j] = String.valueOf(" "+i);
                    continue;
                }

                board[i][j] = " ";
            }
        }
    }


    private void playOmok() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int x;
        int y;
        while (true) {
            try {
                System.out.println("좌표를 입력해 주세요.");
                System.out.print(": ");
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());

                if (isAlreadyDraw(x, y)) {
                    throw new IllegalArgumentException("유효 하지 않은 입력입니다. 다시 입력 해주세요.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                showBoard();
                continue;
            } catch (NoSuchElementException | ArrayIndexOutOfBoundsException e) {
                System.out.println("유효 하지 않은 입력입니다. 다시 입력해주세요.");
                showBoard();
                continue;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            draw_OX(x, y);
            showBoard();

            if (isWin(x, y)) {
                showWinner();
                break;
            }

            if (--countTimes == 0) {
                System.out.println("Draw!!");
                break;
            }
        }
    }


    private void draw_OX(int x, int y) {
        this.board[x][y] = this.flag ? "O" : "X";
        this.flag = !flag;
    }

    private boolean isWin(int x, int y) {

        String player = board[x][y];
        for (int i = 0; i < 4; i++) {
            int count = 1;
            int moveCount = 1;

            while (true) {
                int nx = x + dx[i] * moveCount;
                int ny = y + dy[i] * moveCount;
                if (!board[nx][ny].equals(player)) {
                    break;
                }
                count++;
                moveCount++;
            }

            moveCount = 1;
            while (true) {
                int nx = x - dx[i] * moveCount;
                int ny = y - dy[i] * moveCount;
                if (!board[nx][ny].equals(player)) {
                    break;
                }
                count++;
                moveCount++;
            }

            if (count >= 5) {
                return true;
            }
        }
        return false;
    }

    private void showWinner() {
        String winner = !this.flag ? "< O >" : "< X >";
        System.out.println("----------------------");
        System.out.println(winner + " is Winner!!");
        System.out.println("----------------------");
    }


    private boolean isAlreadyDraw(int x, int y) {
        return board[x][y].equals("O") || board[x][y].equals("X");
    }

    private void ready() {
        int count = 3;
        waitSecond();
        for (int i = count; i > 0; i--) {
            System.out.println(i + "!");
            waitSecond();
        }
        System.out.println("Battle!");
        waitSecond();
    }
}

