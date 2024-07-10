package module.omok_jmr;

public class Stone {
    private Color color;
    private int x;
    private int y;

    public Stone(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return color.toString();
    }
}


