public abstract class Nature {
    Color Color_state;

    public Nature(Color Color_state) {
        this.Color_state = Color_state;
    }

    public abstract void try_get_done(Weather w, Troll t);

    public Color get_Color() {
        return Color_state;
    }

}
