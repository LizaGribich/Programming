package StatusMoves;

import ru.ifmo.se.pokemon.*;

public class VenomDrench extends StatusMove {
    public VenomDrench() {
        super(Type.POISON, 0, 100);
    }

    @Override /*если цель отравлена, снижает ее Атаку,
              Специальную Атаку и Скорость на одну ступень каждую */
    protected void applyOppEffects(Pokemon p) {
        if (p.getCondition() == Status.POISON) {
            p.setMod(Stat.ATTACK, -1);
            p.setMod(Stat.SPECIAL_ATTACK, -1);
            p.setMod(Stat.SPEED, -1);
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
