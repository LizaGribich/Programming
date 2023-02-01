public class Hill implements Say {
    private int Height;
    private boolean Doors;
    private boolean Windows;

    public Hill(int Height, boolean Doors, boolean Windows) {
        this.Height = Height;
        this.Doors = Doors;
        this.Windows = Windows;
    }

    public int get_Height() {
        return Height;
    }

    public void look_at_Hill(Troll t) {
        if (this.Height > 5) {
            System.out.println("\"Ого! Такой высокий холм впереди!\" - подумал он.");
            t.add_mood();
        }
        System.out.println("\"Кажется, его высота около " + this.get_Height() + " метров.\" - оценил " + t.get_Name() + ".");

        if (this.Doors) {
            System.out.println("Удивительно, но у холма впереди есть двери!");
            t.add_mood();
        } else {
            System.out.println("У холма впереди нет дверей.");
            t.rem_mood();
        }

        if (this.Windows) {
            System.out.println("Удивительно, но у холма впереди есть окна!");
            t.add_mood();
        } else {
            System.out.println("У холма впереди нет окон.");
            t.rem_mood();
        }
    }

    @Override
    public void Say(Troll t) {
        if (Math.random() < 0.05) {
            System.out.println("\"БУ!!!\" - сказал кто-то из-под холма...");
            System.out.println("От испуга " + t.get_Name() + " немного расстроился");
            t.rem_mood();
        }
    }
}
