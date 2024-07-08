package module;

import module.snakegame.KeyListener;
import module.snakegame.SnakeGameProcess;


public class SnakeGame implements Playable{
    KeyListener keyListener;
    public SnakeGame(KeyListener keyListener){
        this.keyListener = keyListener;
    }

    @Override
    public void init() {
        playGame();
        System.out.println("end snake game");
    }

    @Override
    public void playGame() {
        SnakeGameProcess process = new SnakeGameProcess(keyListener);
        process.start();

    }
}
