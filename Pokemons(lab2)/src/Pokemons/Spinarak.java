package Pokemons;

import PhysicalMoves.*;
import SpecialMoves.*;
import ru.ifmo.se.pokemon.*;

public class Spinarak extends Pokemon {
    public Spinarak(String name, int level) {
        super(name, level);
        setType(Type.POISON, Type.BUG);
        setStats(40, 60, 40, 40, 40, 30);
        setMove(new LeechLife(), new Absorb(), new PoisonSting());
    }
}
