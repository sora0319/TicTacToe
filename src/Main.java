public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.draw_OX(1,1);
        System.out.println();
        board.draw_OX(2,1);
        System.out.println();
        board.draw_OX(0,0);
        System.out.println();
        board.draw_OX(2,2);
        System.out.println();
        board.draw_OX(2,0);
        System.out.println();
        board.draw_OX(1,0);
        System.out.println();
        board.draw_OX(0,1);
        System.out.println();
        board.showBoard();

    }
}