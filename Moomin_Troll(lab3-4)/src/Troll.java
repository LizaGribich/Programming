import java.util.Objects;

public class Troll implements Change {
    private String Name;
    private Mood Mood_State;
    private Clothes Clothes_on;
    private int Mood_points;
    private boolean flag = false;

    public Troll(String Name) {

        try {
            if (Name.equals("")) {
                throw new NameError();
            }
        } catch (NameError e) {
            System.out.println("Ошибка. Неверно введено имя.");
            System.out.println("Было задано стандартное имя: Снусмумрик.\n");
            this.Name = "Снусмумрик";
            this.Mood_State = Mood.HAPPY;
            this.Clothes_on = Clothes.NOTHING;
            Mood_points = 0;
            return;
        }

        this.Name = Name;
        this.Mood_State = Mood.HAPPY;
        this.Clothes_on = Clothes.NOTHING;
        Mood_points = 0;
    }

    // Вложенный класс (нестатический)
    public class Eyes {
        public void look() {
            System.out.println("Его глаза устремились в небо...");
        }
    }

    // Вложенный класс (статический)
    public static class Teeth {
        public void smile() {
            System.out.println("Его лицо озарила улыбка...");
        }
    }

    public String get_Name() {
        return Name;
    }

    public Mood get_Mood() {
        return Mood_State;
    }

    public Clothes get_Clothes() {
        return Clothes_on;
    }

    public void set_Clothes(Clothes Clothes_on) {
        this.Clothes_on = Clothes_on;
    }

    public void eat_fruit(Fruit f) {
        System.out.println("\"Попробую съесть фрукт с дерева!\" - подумал " + this.get_Name() + ".");
        if (f.Color_state == Color.RED) {
            System.out.println("Вкусно!");
            add_mood();
            flag = true;
        } else {
            System.out.println("Невкусно! Он еще не дозрел.");
            rem_mood();
        }
    }

    public void change_clothes(Weather w) {
        if (w.get_Wind() || w.get_Rain()) {
            Clothes_on = Clothes.HAT;
            System.out.println("Из-за непогоды " + this.get_Name() + " решил надеть шляпку.");
        } else {
            Clothes_on = Clothes.NOTHING;
            System.out.println("Из-за хорошей погды " + this.get_Name() + " решил не надевать ничего лишнего.");
        }
    }

    public boolean pull() {
        if (Math.random() < 0.5) {
            return true;
        }
        return false;
    }

    public void add_mood() {
        Mood_points++;
        System.out.println("+1 очко настроения");
    }

    public void add_mood_special() {
        Mood_points += 15;
        System.out.println("+15 очков настроения");
    }

    public void rem_mood() {
        Mood_points--;
        System.out.println("-1 очко настроения");
    }

    public int get_points() {
        return Mood_points;
    }

    public void reset_points() {
        Mood_points = 0;
    }

    public void set_Mood() {
        if (get_points() > 0) {
            Mood_State = Mood.HAPPY;
            System.out.println("День прошёл хорошо!");
        } else {
            Mood_State = Mood.SAD;
            System.out.println("День прошёл плохо...");
        }
    }

    @Override
    public void ChangeRandomly(Troll t) {
        if (Math.random() < 0.05 && flag) {
            Mood_State = Mood.RELAXED;
            System.out.println("Съев этот фрукт, он почувствовал истинное наслаждение и расслабление...");
            t.add_mood_special();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Troll)) {
            return false;
        }
        Troll p = (Troll) obj;
        return this.Name.equals(p.Name);
    }

}
