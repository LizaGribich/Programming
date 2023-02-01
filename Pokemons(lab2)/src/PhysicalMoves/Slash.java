package PhysicalMoves;

import ru.ifmo.se.pokemon.*;

public class Slash extends PhysicalMove {
    public Slash() {
        super(Type.NORMAL, 70, 100);
    }

    @Override // имеет повышенный коэффициент критического удара
    protected double calcCriticalHit(Pokemon att, Pokemon def) {
        if (att.getStat(Stat.SPEED) * 3 / 512.0 > Math.random()) {
            return 2.0;
        } else {
            return 1.0;
        }
    }

    @Override
    protected boolean checkAccuracy(Pokemon att, Pokemon def) {
        if (this.accuracy * att.getStat(Stat.ACCURACY) / def.getStat(Stat.EVASION) > Math.random()) {
        } else {
            System.out.println("Ай, я промахнулся!!!");
        }
        return this.accuracy * att.getStat(Stat.ACCURACY) / def.getStat(Stat.EVASION) > Math.random();
    }

}
