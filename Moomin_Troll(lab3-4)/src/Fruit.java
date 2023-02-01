public class Fruit extends Nature {

    public Fruit(Color Color_state) {
        super(Color_state);
    }

    @Override
    public void try_get_done(Weather w, Troll t) {
        Color[] Colors = Color.values();
        if (w.get_Warm() && (Color_state.ordinal() != Colors.length - 1)) {
            Color_state = Colors[Color_state.ordinal() + 1];
            System.out.println("На улице так тепло, что фрукты созревают на глазах!");
            System.out.println("Тот фрукт поменял цвет! Теперь он " + Color_state + " цвета.");
            t.add_mood();
        } else if (Color_state == Color.RED) {
            System.out.println("Фрукт уже полностью созрел! Он такой красный!");
            t.add_mood();
        } else {
            System.out.println("На улице недостаточно тепло, чтобы фрукты быстро зрели...");
            t.rem_mood();
        }
    }

}
