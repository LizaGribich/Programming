import Pokemons.*;
import ru.ifmo.se.pokemon.*;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Dialga p1 = new Dialga("Вася", 1);
        Spinarak p2 = new Spinarak("Шарик", 1);
        Ariados p3 = new Ariados("Иван Иванович", 22);
        Chimchar p4 = new Chimchar("Тузик", 1);
        Monferno p5 = new Monferno("Грелка", 14);
        Infernape p6 = new Infernape("Орех", 36);

        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);

        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);

        b.go();


    }


}
