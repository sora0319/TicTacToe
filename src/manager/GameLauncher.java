package manager;

import module.Playable;

public class GameLauncher {
    private final Playable playable;
    public GameLauncher(Playable playable) {
        this.playable = playable;
    }

    public void init() {
        this.playable.init();
        this.playable.playGame();
    }
}
