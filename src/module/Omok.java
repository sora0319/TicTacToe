package module;

/*
 * 오목
 *
 * 오목판 크기    15X15
 *
 * 룰
 *
 * 장목 : 6칸 이상 이어져 있는 상태.
 *  1. 렌주룰에서 흑은 착수 불가, 백은 만들 수 있고 만들면 즉시 승리.
 *
 * 5목 : 5칸 연속으로 같은 색깔을 돌이 있는 상태.
 *  1. 오목에서 이 수를 두면 이김. 렌주룰에서는 흑이 금수(3-3, 4-4, 장목)가 되더라도 동시에 5목이 만들어지면 승리.
 *
 * 4목 : 한 수 첨가하여 4목을 만들 수 있는 상태.
 *  1. 열린 4
 *  2. 닫힌 4
 *  3. 띈 4
 *  4. 4-4(사사) : 일반룰에서는 모두 허용. 렌주룰에서는 백에게만 허용.
 *
 * 3목 : 한 수 첨가하여 열린 4를 만들 수 있는 상태.
 *  1. 3-3(삼삼) : 일반룰에선 흑백 모두 금지. 렌주룰에서는 백에게만 허용.
 *  2. 4-3(사삼) : 4목과 3목을 동시에 만드는 것. 렌주룰에서는 흑이 이기기 위한 사실상 유일한 방법
 *
 * 일반룰 : 흑백 모두 3-3만을 금수로 하는 룰.
 * 렌주룰 : 흑백 공평성을 맞추기 위해 고안된 룰.
 *   - 흑의 금수 : 3-3, 4-4, 장목
 *   - 백의 금수 : 없음.
 *   - 금수와 함께 5도 만들어지는 경우에는 금수를 무시하고 그대로 흑 승리.
 *
 * 클래스
 * - 바둑돌 클래스
 *   1. 돌의 색, 흑돌, 백돌 관리
 *
 * - 오목판 클래스
 *   1. 오목판 관리, 15X15의 크기의 이차원 배열, 놓인 돌의 정보 저장
 *
 * - 플레이어 클래스
 *   1. 플레이어 정보 저장
 *   2. 
 * - 게임 관리 클래스
 * - UI 클래스
 * - 규칙 검증 클래스
 * - 승리 조건 클래스? 메소드?
 * - 금수 처리 클래스
 * -------추가로 확장해본다면 시도해볼만한 것------------
 * - 게임 기록 클래스
 * -
 *
 *
 * */

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
                    board[i][j] = String.valueOf(i);
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

