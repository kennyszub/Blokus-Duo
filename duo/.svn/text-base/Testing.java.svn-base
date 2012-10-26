package duo;

import org.junit.Test;
import ucb.junit.textui;
import static org.junit.Assert.*;
import static duo.Color.*;

/** Unit tests for the duo package. */
public class Testing {

    /** Run all JUnit tests in the duo package. */
    public static void main(String[] ignored) {
        textui.runClasses(duo.Testing.class);
    }

    @Test
    public void emptyBoard() {
        Board b = new Board();
        for (int c = 0; c < b.SIZE; c += 1) {
            for (int r = 0; r < b.SIZE; r += 1) {
                assertEquals(EMPTY, b.get(c, r));
            }
        }
    }

    @Test
    public void makeAMove() {
        MutableBoard answerBoard = new MutableBoard();
        Color[][] answergameBoard = answerBoard.getBoard();
        answergameBoard[0][answerBoard.SIZE - 1] = ORANGE;
        answergameBoard[1][answerBoard.SIZE - 1] = ORANGE;
        MutableBoard modBoard = new MutableBoard();
        modBoard.makeMove("2001");
        Color[][] modgameBoard = modBoard.getBoard();
        assertEquals(answergameBoard[0][answerBoard.SIZE - 1],
                     modgameBoard[0][answerBoard.SIZE - 1]);
        assertEquals(answergameBoard[1][answerBoard.SIZE - 1],
                     modgameBoard[0][answerBoard.SIZE - 1]);
    }

    @Test
    public void rotatePiece() {
        Piece W = new Piece(new int[][] {{0, 0, 1}, {0, 1, 1}, {1, 1, 0}});
        Piece rotW = W.rotate(1);
        Piece answerW = new Piece(new int[][] {{1, 1, 0},
                                               {0, 1, 1}, {0, 0, 1}});
        int[][] rotWconfig = rotW.getConfiguration();
        int[][] answerWconfig = answerW.getConfiguration();
        assertEquals(rotWconfig, answerWconfig);
    }

    @Test
    public void flipPiece() {
        Piece L = new Piece(new int[][] {{1, 1, 1}, {0, 0, 1}});
        Piece flipL = L.rotate(4);
        Piece answerL = new Piece(new int[][] {{0, 0, 1}, {1, 1, 1}});
        int[][] flipLconfig = flipL.getConfiguration();
        int[][] answerLconfig = answerL.getConfiguration();
        assertEquals(flipLconfig, answerLconfig);
    }

    @Test
    public void mutableBoardCopying() {
        MutableBoard original = new MutableBoard();
        original.makeMove("L000");
        original.incrementMoveNum();
        original.makeMove("L0a0");
        original.setMoveNum(10);
        MutableBoard copy = new MutableBoard(original);
        assertEquals(original.getBoard(), copy.getBoard());
        assertEquals(original.getMoveNum(), copy.getMoveNum());
        assertEquals(original.getUsedOrange(), copy.getUsedOrange());
        assertEquals(original.getUsedViolet(), copy.getUsedViolet());
    }

    @Test
    public void pieceUsed() {
        MutableBoard board = new MutableBoard();
        board.makeMove("L000");
        board.incrementMoveNum();
        board.makeMove("L0a0");
        assertEquals(board.orangePieceUsed("L"), true);
    }

    @Test
    public void aValidFirstMove() {
        MutableBoard board = new MutableBoard();
        boolean valid = board.validFirstMove("L000");
        assertEquals(valid, true);
    }

    @Test
    public void anInValidFirstMove() {
        MutableBoard board = new MutableBoard();
        boolean invalid = board.validFirstMove("L330");
        assertEquals(invalid, false);
    }

    @Test
    public void withinBoundsMove() {
        MutableBoard board = new MutableBoard();
        boolean inBounds = board.withinBounds("W556");
        assertEquals(inBounds, true);
    }

    @Test
    public void outOfBoundsMove() {
        MutableBoard board = new MutableBoard();
        boolean outBounds = board.withinBounds("Wdd0");
        assertEquals(outBounds, false);
    }

    @Test
    public void touchingEdge() {
        MutableBoard board = new MutableBoard();
        board.makeMove("L000");
        board.incrementMoveNum();
        board.makeMove("L0a0");
        board.incrementMoveNum();
        boolean touching = board.touchingEdge("W110");
        assertEquals(touching, true);
    }

    @Test
    public void touchingCorner() {
        MutableBoard board = new MutableBoard();
        board.makeMove("L000");
        board.incrementMoveNum();
        board.makeMove("L0a0");
        board.incrementMoveNum();
        boolean touchingCorner = board.touchingCorner("W140");
        assertEquals(touchingCorner, true);
    }

    @Test
    public void legalMove() {
        MutableBoard board = new MutableBoard();
        board.makeMove("L000");
        board.incrementMoveNum();
        board.makeMove("L0a0");
        board.incrementMoveNum();
        boolean legal = board.isLegal("W140");
        assertEquals(legal, true);
    }

    @Test
    public void illegalMove() {
        MutableBoard board = new MutableBoard();
        board.makeMove("L000");
        board.incrementMoveNum();
        board.makeMove("L0a0");
        board.incrementMoveNum();
        boolean legal = board.isLegal("W150");
        assertEquals(legal, false);
    }

    @Test
    public void wellFormedMove() {
        MutableBoard board = new MutableBoard();
        boolean wellformed = board.isWellFormed("ba32d");
        assertEquals(wellformed, false);
    }

    @Test
    public void notWellFormedMove() {
        MutableBoard board = new MutableBoard();
        boolean wellformed = board.isWellFormed("Y9d7");
        assertEquals(wellformed, true);
    }

    @Test
    public void movesLeft() {
        UI ui = new TextUI();
        Player orange = new Human("Sam", ui);
        Player violet = new Human("Bob", ui);
        Game game = new Game(orange, violet, ui, 0);
        MutableBoard board = game.getMutableBoard();
        Color[][] config = board.getBoard();
        for (int x = 0; x < board.SIZE; x += 1) {
            for (int y = 0; y < board.SIZE; y += 1) {
                config[x][y] = ORANGE;
            }
        }
        boolean movesRemaining = game.movesLeft();
        assertEquals(movesRemaining, false);
    }

    @Test
    public void testScore() {
        UI ui = new TextUI();
        Player orange = new Human("Sam", ui);
        Player violet = new Human("Bob", ui);
        Game game = new Game(orange, violet, ui, 0);
        MutableBoard board = game.getMutableBoard();
        Color[][] config = board.getBoard();
        for (int x = 0; x < board.SIZE; x += 1) {
            for (int y = 0; y < board.SIZE; y += 1) {
                config[x][y] = ORANGE;
            }
        }
        int[] score = game.score();
        int orangeScore = score[0];
        assertEquals(orangeScore, 196);
    }
}
