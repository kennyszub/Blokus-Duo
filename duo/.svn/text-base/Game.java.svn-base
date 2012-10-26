package duo;

import java.util.Random;
import static duo.Color.*;
import java.util.ArrayList;

/** Supervisor for a game of Duo.
 *  @author Ken Szubzda (cs61b-ek)
 */
class Game {

    /** A new Game representing one game between ORANGEPLAYER and
     *  VIOLETPLAYER.  Initially, the board is empty. Uses UI for input
     *  and output.  If SEED is non-zero, uses it to seed a random
     *  number generator. */
    Game(Player orangePlayer, Player violetPlayer, UI ui, long seed) {
        _player[0] = orangePlayer;
        _player[1] = violetPlayer;
        _ui = ui;
        if (seed == 0) {
            _rand = new Random();
        } else {
            _rand = new Random(seed);
        }
        _board = new MutableBoard();
    }

    /** Returns the current board (immutably). */
    Board getBoard() {
        return _board;
    }

    /** Returns the number of moves that have been made. */
    int getNumMoves() {
        return _board.getMoveNum();
    }

    /** Perform MOVE on the current board (the color of the piece
     *  placed depends on whose move it is). */
    void move(String move) {
        if (move.substring(0, 1).equals("b")) {
            _ui.reportBoardStandard(_board);
        } else if (move.substring(0, 1).equals("q")) {
            System.exit(0);
        } else if (!_board.isWellFormed(move)) {
            _ui.reportError("Error: Move is incorrectly formated.");
        } else if (!_board.isLegal(move)) {
            _ui.reportError("Error: Move is not legal.");
        } else {
            previousmove = move;
            _board.makeMove(move);
        }
    }


    /** Starting from the current board, complete a game between the
     *  two players, reporting all results on my Reporter. */
    void play() {
        _player[0].startGame(this, ORANGE);
        _player[1].startGame(this, VIOLET);
        while (true) {
            Color playersMove = _board.playerOnMove();
            if (playersMove == ORANGE) {
                _player[0].move();
            } else {
                _player[1].move();
            }
        }
    }
    /** Check for any possible moves remaining. Return true if moves remain. */
    boolean movesLeft() {
        String[] pieces = {"1", "2", "v", "3", "t", "s", "d", "i", "z", "N",
                           "Y", "P", "F", "V", "X", "T",
                           "U", "L", "I", "Z", "W"};
        String[] cols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                         "a", "b", "c", "d"};
        String[] rows = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                         "a", "b", "c", "d"};
        String[] orientations = {"0", "1", "2", "3", "4", "5", "6", "7"};
        Color playersMove = _board.playerOnMove();
        for (String piece : pieces) {
            if (((playersMove == ORANGE) && (!_board.orangePieceUsed(piece)))
                || ((playersMove == VIOLET)
                    && (!_board.violetPieceUsed(piece)))) {
                for (String col : cols) {
                    for (String row : rows) {
                        for (String orientation : orientations) {
                            String possibleMove = piece + col
                                + row + orientation;
                            if (_board.isLegal(possibleMove)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    /** Size check for moves. */
    private final int checkSize = 21;
    /** Index check for moves. */
    private final int checkIndex = 20;
    /** Computes the final score of the game. Returns it as an array with
     * oranges score then violet's score */
    int[] score() {
        Color[][] gameBoard = _board.getBoard();
        int orangeScore = 0;
        int violetScore = 0;
        ArrayList<String> orangeMoves = _board.getUsedOrange();
        ArrayList<String> violetMoves = _board.getUsedViolet();
        for (int x = 0; x < _board.SIZE; x += 1) {
            for (int y = 0; y < _board.SIZE; y += 1) {
                if (gameBoard[x][y] == ORANGE) {
                    orangeScore += 1;
                }
                if (gameBoard[x][y] == VIOLET) {
                    violetScore += 1;
                }
            }
        }
        if (orangeMoves.size() == checkSize
            && orangeMoves.get(checkIndex).equals("1")) {
            orangeScore += 5;
        }
        if (violetMoves.size() == checkSize
            && violetMoves.get(checkIndex).equals("1")) {
            violetScore += 5;
        }
        return (new int[]{orangeScore, violetScore});
    }




    /** Return the previousmove. */
    String getPreviousmove() {
        return previousmove;
    }

    /** Returns a uniformly distributed pseudo-random integer between
     *  0 and N-1 (inclusive).  Assumes N > 0. */
    int nextRand(int n) {
        return _rand.nextInt(n);
    }

    /** Returns a uniformly distributed pseudo-random int. */
    int nextRand() {
        return _rand.nextInt();
    }
    /** Returns the mutable board. */
    MutableBoard getMutableBoard() {
        return _board;
    }

    /** The players in this game: orange is _player[0] and violet
     *  is _player[1]. */
    private final Player[] _player = new Player[2];
    /** The current board. */
    private MutableBoard _board;

    /** The UI I and my players use for input and output. */
    private final UI _ui;

    /** A random number generator for use by my players. */
    private Random _rand;
    /** previous move variable. */
    private String previousmove = "No previous move";
}
