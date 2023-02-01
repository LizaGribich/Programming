package Pokemons;

import SpecialMoves.*;
import ru.ifmo.se.pokemon.*;
import StatusMoves.*;
import PhysicalMoves.*;
public class Dialga extends Pokemon {

    public Dialga(String name, int level) {
        super(name, level);
        setType(Type.STEEL, Type.DRAGON);
        setStats(100.0, 120.0, 120.0, 150.0, 100.0, 90.0);
        setMove(new Confide(), new Slash(), new ScaryFace(), new Flamethrower());
    }
}


