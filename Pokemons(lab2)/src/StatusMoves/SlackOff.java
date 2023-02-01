package StatusMoves;

import ru.ifmo.se.pokemon.*;

public class SlackOff extends StatusMove {
    public SlackOff() {
        super(Type.NORMAL, 0, 0);
    }

    @Override //восстанавливает до 50% максимального HP пользователя
    protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.HP, (int) -p.getStat(Stat.HP) / 2);
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
