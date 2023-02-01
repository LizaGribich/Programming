package SpecialMoves;

import ru.ifmo.se.pokemon.*;



public class Flamethrower extends SpecialMove {
    public Flamethrower() {
        super(Type.FIRE, 90, 100);
    }

    @Override // имеет 10% шанс поджечь цель
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1) {
            Effect.burn(p);
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
