package Pokemons;

import StatusMoves.*;
import ru.ifmo.se.pokemon.*;

public class Monferno extends Chimchar {
    public Monferno(String name, int level) {
        super(name, level);
        addType(Type.FIGHTING);
        setStats(64, 78, 52, 78, 52, 81);
        addMove(new SlackOff());
    }
}
