public enum GameList {

    TIC_TAC_TOE(1, "TicTacToe");

    private final int num;
    private final String gameName;

    public int getNum() {
        return this.num;
    }

    public String getGameName() {
        return this.gameName;
    }

    GameList(int num, String gameName) {
        this.num = num;
        this.gameName=gameName;
    }
}
