package StatusMoves;

import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {
    public Rest() {
        super(Type.PSYCHIC, 0, 0);
    }

    @Override //пользователь спит на 2 хода, но полностью исцеляется
    protected void applySelfEffects(Pokemon p) {
        Effect.sleep(p);
        Effect.sleep(p);
        p.restore();
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
