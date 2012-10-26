package duo;

import static duo.Color.*;

/** Abstract class of all players.
 * @author Ken Szubzda (cs61b-ek)
 */
class RandomAI extends Player {

    /** A RandomAI named NAME, playing the COLOR pieces, and using UI
     *  for messages.  This kind of player computes its own moves. */
    RandomAI(String name, UI ui) {
        super(name, ui);
    }
    /** magic 21 indice. */
    private final int twenty1 = 21;
    /** magic 14 indice. */
    private final int fourteen = 14;

    @Override
    void move() {
        Color playercolor = getColor();
        int numMoves = _game.getNumMoves();
        String previousmove = _game.getPreviousmove();
        Board board = _game.getBoard();
        String[] pieces = {"1", "2", "v", "3", "t", "s", "d", "i", "z", "N",
                           "Y", "P", "F", "V", "X", "T",
                           "U", "L", "I", "Z", "W"};
        String[] cols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                         "a", "b", "c", "d"};
        String[] rows = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                         "a", "b", "c", "d"};
        if (_game.movesLeft()) {
            while (true) {
                int pIndex = _game.nextRand(twenty1);
                int cIndex = _game.nextRand(fourteen);
                int rIndex = _game.nextRand(fourteen);
                int orientation = _game.nextRand(8);
                String piece = pieces[pIndex];
                String col = cols[cIndex];
                String row = rows[rIndex];
                String move = piece + col + row
                    + Integer.toString(orientation);
                if (board.isLegal(move)) {
                    _game.move(move);
                    _ui.reportMove(playercolor, numMoves, move);
                    break;
                }
            }
        } else {
            int[] finalScore = _game.score();
            _ui.reportWinner(finalScore[0], finalScore[1]);
        }
    }
}
