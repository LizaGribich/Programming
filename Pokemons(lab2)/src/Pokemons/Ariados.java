package Pokemons;

import StatusMoves.*;
import ru.ifmo.se.pokemon.*;

public class Ariados extends Spinarak {
    public Ariados(String name, int level) {
        super(name, level);
        setType(Type.BUG, Type.POISON);
        setStats(70, 90, 70, 60, 70, 40);
        addMove(new VenomDrench());
    }

}