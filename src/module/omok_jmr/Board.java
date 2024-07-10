package module.omok_jmr;

public class Board {
    private final Stone[][] board;

    public Board() {
        board = new Stone[15][15];
    }

    public Stone[][] getBoard() {
        return board;
    }

    public boolean putStone(Stone stone) {
        int x = stone.getX();
        int y = stone.getY();
        // 바둑판을 벗어난 위치에 돌을 두려고 함
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return false;
        // 두려는 위치에 이미 돌이 있는 경우
        if (board[x][y] != null) return false;

        board[x][y] = stone;
        return true;
    }

    public Stone getStone(int x, int y) {
        return board[x][y];
    }
}
