package manager;

public enum GameList {

    TIC_TAC_TOE(1, "TicTacToe"),
    HANOI(2, "Hanoi"),
    BLACKJACK(3, "Blackjack"),
    NUMBER_BASEBALL(4, "NumberBaseball"),
    SNAKE_GAME(5, "SnakeGame"),
    OMOK(6, "Omok"),
    OMOK_JMR(7, "Omok_JMR");


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