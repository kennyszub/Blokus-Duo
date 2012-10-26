package duo;

import static duo.Color.*;
import java.util.ArrayList;

/** Represents a Blokus Duo(tm) game board that may be changed.  This
 *  is a subtype of Board so that Board itself can represent a
 *  non-modifiable game board.
 * @author Ken Szubzda (cs61b-ek)
 */
class MutableBoard extends Board {

    /** A new, empty MutableBoard. */
    MutableBoard() {
        super();
    }

    /** A new MutableBoard whose initial contents are copied from
     *  BOARD. */
    MutableBoard(Board board) {
        super();
        for (int x = 0; x < SIZE; x += 1) {
            for (int y = 0; y < SIZE; y += 1) {
                this.gameBoard[x][y] = board.gameBoard[x][y];
            }
        }
        ArrayList<String> tempOrange = board.getUsedOrange();
        ArrayList<String> tempViolet = board.getUsedViolet();
        ArrayList<String> modifiedOrange = this.getUsedOrange();
        ArrayList<String> modifiedViolet = this.getUsedViolet();
        for (int index = 0; index < tempOrange.size(); index += 1) {
            modifiedOrange.add(tempOrange.get(index));
        }
        for (int index = 0; index < tempViolet.size(); index += 1) {
            modifiedViolet.add(tempViolet.get(index));
        }
        this.setMoveNum(board.getMoveNum());
    }
    /** Hexadecimal value. */
    private final int hexaVal = 16;

    /** Make the indicated MOVE on the current board for the player
     *  that is on move. */
    void makeMove(String move) {
        Color playersMove = playerOnMove();
        String pieceName = move.substring(0, 1);
        int columnRef = Integer.parseInt(move.substring(1, 2), hexaVal);
        int rowRef = Integer.parseInt(move.substring(2, 3), hexaVal);
        int orientation = Integer.parseInt(move.substring(3, 4));
        Piece currentPiece = (Piece) getPIECES().get(pieceName);
        Piece rotatedPiece = currentPiece.rotate(orientation);
        int[][] configuration = rotatedPiece.getConfiguration();
        if (playersMove == ORANGE) {
            addUsedOrange(pieceName);
        } else {
            addUsedViolet(pieceName);
        }
        for (int[] x : configuration) {
            int rowindex = Board.SIZE - rowRef - configuration[0].length;
            for (int y : x) {
                if (y == 1) {
                    Color[][] boardConfig = getBoard();
                    boardConfig[columnRef][rowindex] = playersMove;
                }
                rowindex += 1;
            }
            columnRef += 1;
        }
        incrementMoveNum();
    }
}
