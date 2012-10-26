package duo;

import static duo.Color.*;

/** Abstract class of all players.
 * @author Ken Szubzda (cs61b-ek)
 */
class Human extends Player {

    /** A Human player named NAME, playing the COLOR pieces, and using UI
     *  for input and messages.  This kind of player prompts for moves
     *  from the user. */
    Human(String name, UI ui) {
        super(name, ui);
    }

    @Override
    void move() {
        Color playercolor = getColor();
        int numMoves = _game.getNumMoves();
        String previousmove = _game.getPreviousmove();
        if (_game.movesLeft()) {
            String move = _ui.getMove(playercolor, numMoves, previousmove);
            _game.move(move);
        } else {
            int[] finalScore = _game.score();
            _ui.reportWinner(finalScore[0], finalScore[1]);
        }
    }
}
