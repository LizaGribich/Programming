public enum Mood {
    HAPPY("счастье"),
    SAD("грусть"),
    RELAXED("расслабление");
    private String translation;

    Mood(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return translation;
    }

}
