package Pokemons;

import StatusMoves.*;
import ru.ifmo.se.pokemon.*;

public class Chimchar extends Pokemon {
    public Chimchar(String name, int level) {
        super(name, level);
        setType(Type.FIRE);
        setStats(44, 58, 44, 58, 44, 61);
        setMove(new Rest(), new WillOWisp());
    }
}

