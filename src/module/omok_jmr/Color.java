package module.omok_jmr;

public enum Color{
    WHITE(0, "백돌", "○"), BLACK(1, "흑돌", "●");

    private final int number;
    private final String name;
    private final String string;

    Color(int number, String text, String string) {
        this.number = number;
        this.name = text;
        this.string = string;
    }

    public int getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }
    public String toString() {
        return this.string;
    }
}