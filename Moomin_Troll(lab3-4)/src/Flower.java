import java.util.concurrent.ThreadLocalRandom;

public class Flower extends Nature implements Change {

    public Flower(Color Color_state) {
        super(Color_state);
    }

    @Override
    public void try_get_done(Weather w, Troll t) {
        if (w.get_Sun()) {
            System.out.println("Благодаря солнечным лучам цветок расцвёл!");
            System.out.println("Теперь видно, какого он цвета - " + this.get_Color() + '!');
            t.add_mood();
        } else {
            System.out.println("Из-за облаков бутоны не могут распуститься. Сегодня не получится увидеть их цвет...");
            t.rem_mood();
        }
    }

    @Override
    public void ChangeRandomly(Troll t) {
        if (Math.random() < 0.05) {
            Color[] colors = Color.values();
            int random_int = ThreadLocalRandom.current().nextInt(0, colors.length);
            Color randomColor = colors[random_int];
            this.Color_state = randomColor;
            System.out.println("ОГО! Произошло чудо! Цветок неожиданно поменял цвет!");
            System.out.println("Теперь он " + get_Color() + " цвета!");
            t.add_mood_special();
        }

    }
}
