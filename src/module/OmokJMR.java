package module;

import module.omok_jmr.GameClient;

public class OmokJMR implements Playable{
    @Override
    public void init() {
        playGame();
    }

    @Override
    public void playGame() {
        new GameClient().run();
    }
}
