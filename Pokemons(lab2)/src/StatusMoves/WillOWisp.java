package StatusMoves;

import ru.ifmo.se.pokemon.*;

public class WillOWisp extends StatusMove {
    public WillOWisp() {
        super(Type.FIRE, 0, 85);
    }

    @Override /*поджигает цель,
    сгоревшие покемоны теряют 1/8 своего максимального
    здоровья, а их атака снижается на 50% */
    protected void applyOppEffects(Pokemon p) {
        Effect.burn(p);
        p.setMod(Stat.HP, (int) p.getStat(Stat.HP)/8);
        p.setMod(Stat.ATTACK, -2);
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
