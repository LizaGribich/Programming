import java.util.concurrent.ThreadLocalRandom;

public class Life {
    private static Troll troll;
    private static int days;

    public Life(Troll troll, int days) {

        try {
            if (days <= 0) {
                throw new NumberError();
            }
        } catch (NumberError e) {
            System.out.println("Ошибка. Неверно введено кол-во дней.");
            System.out.println("Было задано стандартное кол-во дней: 1.\n");
            this.days = 1;
            this.troll = troll;
            return;
        }

        this.troll = troll;
        this.days = days;
    }

    public static void go() {

        // Анонимный вложенный класс (нестатический)
        Ferns ferns = new Ferns() {
            @Override
            public void grow_into() {
                System.out.println("Заросли папоротников проросли в ванну.");
            }
        };

        // Локальный вложенный класс (нестатический)
        class Ondatr {
            public void say() {
                System.out.println("\"Лианы проросли сквозь печную трубу, оплели крышу и окутали весь Муми-дом пышным зеленым ковром\" - Ондатр.");
            }
        }
        Ondatr ondatr = new Ondatr();

        // Вложенный статический класс
        Troll.Teeth teeth = new Troll.Teeth();

        for (int i = 1; i <= days; i++) {
            System.out.println("Взошло солнце! Настал день: " + i + "\n");
            troll.reset_points();


            ferns.grow_into();
            ondatr.say();

            Weather weather = new Weather(random_boolean(), random_boolean(), random_boolean(), random_boolean());
            Hill hill = new Hill(ThreadLocalRandom.current().nextInt(1, 11), random_boolean(), random_boolean());
            Fruit fruit = new Fruit(random_color());
            Flower flower = new Flower(random_color());
            Stalk stalk = new Stalk(Color.GREEN);

            System.out.println(troll.get_Name() + " вышел на улицу.");
            teeth.smile();


            // Вложенный нестатический класс
            Troll.Eyes eyes = troll.new Eyes();
            eyes.look();

            weather.say_Weather();
            troll.change_clothes(weather);


            // Фрукты
            System.out.println("На деревьях висели фрукты.\nВ глаза бросился один из них, он был " + fruit.get_Color() + " цвета.");
            fruit.try_get_done(weather, troll);
            troll.eat_fruit(fruit);
            troll.ChangeRandomly(troll);

            // Цветы
            System.out.println("На клумбе в саду ростут цветы.");
            flower.try_get_done(weather, troll);
            flower.ChangeRandomly(troll);

            // Холм
            hill.look_at_Hill(troll);
            hill.Say(troll);

            // Стебель
            stalk.try_get_done(weather, troll);

            if (troll.get_Clothes() == Clothes.HAT) {
                System.out.println(troll.get_Name() + " снял шляпку, зайдя в дом.");
            } else {
                System.out.println(troll.get_Name() + " зашёл в дом.");
            }


            System.out.println("Очки настроения за день: " + troll.get_points());
            troll.set_Mood();
            System.out.println("\"Чувствую " + troll.get_Mood() + "!\" - сказал " + troll.get_Name() + ".");

            System.out.println("Солнце село. " + troll.get_Name() + " пошёл спать...\n");

        }
    }

    private static boolean random_boolean() {
        if (Math.random() < 0.5) {
            return true;
        }
        return false;
    }

    private static Color random_color() {
        Color[] Colors = Color.values();
        int random_int = ThreadLocalRandom.current().nextInt(0, Colors.length);
        Color random_col = Colors[random_int];
        return random_col;


    }
}
