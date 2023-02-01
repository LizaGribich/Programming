public enum Color {
    GREEN("зелёного"),
    YELLOW("жёлтого"),
    ORANGE("оранжевого"),
    RED("красного");

    private String translation;

    Color(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return translation;
    }
}
