package PhysicalMoves;

import ru.ifmo.se.pokemon.*;

public class FlareBlitz extends PhysicalMove {
    public FlareBlitz() {
        super(Type.FIRE, 120, 100);
    }

    boolean flag = false;

    @Override /* имеет 10% шанс поджечь цель, но пользователь получает 1/3
              нанесенного урона в виде отдачи. Другими словами, если атака
              нанесет противнику урон в 90 ОЗ, пользователь потеряет 30 ОЗ */
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1) {
            Effect.burn(p);
        }
    }

    @Override
    protected void applySelfDamage(Pokemon att, double damage) {
        att.setMod(Stat.HP, (int) Math.round(damage / 3.0));
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
