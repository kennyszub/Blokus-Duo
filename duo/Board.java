package duo;

import static duo.Color.*;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.ArrayList;

/** Represents a Blokus Duo(tm) game board.
 * @author Ken Szubzda (cs61b-ek)
 */
class Board {

    /** Number of rows and columns in a board. */
    public static final int SIZE = 14;
    /** A board represented by an array of an array of colors. */
    protected Color[][] gameBoard;
    /** HashMap of pieces. */
    private final HashMap<String, Piece> piecesHash
        = new HashMap<String, Piece>();
    /** Arraylist of strings of Orange pieces used. */
    private ArrayList<String> usedOrange = new ArrayList<String>();
    /** Arraylist of strings of Violet pieces used. */
    private ArrayList<String> usedViolet = new ArrayList<String>();
    /** Keeps track of number of moves. */
    private int moveNum;
    /** Constructor BOARD which creates a new board of size 14.
     */
    Board() {
        gameBoard = new Color[SIZE][SIZE];
        for (int x = 0; x < SIZE; x += 1) {
            for (int y = 0; y < SIZE; y += 1) {
                gameBoard[x][y] = EMPTY;
            }
        }
        moveNum = 1;
        Piece W = new Piece(new int[][] {{0, 0, 1}, {0, 1, 1}, {1, 1, 0}});
        piecesHash.put("W", W);
        Piece Z = new Piece(new int[][] {{1, 0, 0}, {1, 1, 1}, {0, 0, 1}});
        piecesHash.put("Z", Z);
        Piece I = new Piece(new int[][] {{1, 1, 1, 1, 1}});
        piecesHash.put("I", I);
        Piece L = new Piece(new int[][] {{1, 1, 1, 1}, {0, 0, 0, 1}});
        piecesHash.put("L", L);
        Piece U = new Piece(new int[][] {{1, 1, 1}, {1, 0, 1}});
        piecesHash.put("U", U);
        Piece T = new Piece(new int[][] {{0, 0, 1}, {1, 1, 1}, {0, 0, 1}});
        piecesHash.put("T", T);
        Piece X = new Piece(new int[][] {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}});
        piecesHash.put("X", X);
        Piece V = new Piece(new int[][] {{0, 0, 1}, {0, 0, 1}, {1, 1, 1}});
        piecesHash.put("V", V);
        Piece F = new Piece(new int[][] {{0, 1, 0}, {1, 1, 1}, {1, 0, 0}});
        piecesHash.put("F", F);
        Piece P = new Piece(new int[][] {{1, 1, 1}, {1, 1, 0}});
        piecesHash.put("P", P);
        Piece Y = new Piece(new int[][] {{1, 1, 1, 1}, {0, 1, 0, 0}});
        piecesHash.put("Y", Y);
        Piece N = new Piece(new int[][] {{1, 1, 1, 0}, {0, 0, 1, 1}});
        piecesHash.put("N", N);
        Piece z = new Piece(new int[][] {{1, 0}, {1, 1}, {0, 1}});
        piecesHash.put("z", z);
        Piece i = new Piece(new int[][] {{1, 1, 1, 1}});
        piecesHash.put("i", i);
        Piece d = new Piece(new int[][] {{0, 0, 1}, {1, 1, 1}});
        piecesHash.put("d", d);
        Piece s = new Piece(new int[][] {{1, 1}, {1, 1}});
        piecesHash.put("s", s);
        Piece t = new Piece(new int[][] {{0, 1}, {1, 1}, {0, 1}});
        piecesHash.put("t", t);
        Piece three = new Piece(new int[][] {{1, 1, 1}});
        piecesHash.put("3", three);
        Piece v = new Piece(new int[][] {{1, 1}, {0, 1}});
        piecesHash.put("v", v);
        Piece two = new Piece(new int[][] {{1, 1}});
        piecesHash.put("2", two);
        Piece one = new Piece(new int[][] {{1}});
        piecesHash.put("1", one);
    }

    /** Return the current contents of the square in column COL and row ROW. */
    Color get(int col, int row) {
        row = SIZE - 1 - row;
        return gameBoard[col][row];
    }

    /** Return the color of player whose turn it is. */
    Color playerOnMove() {
        if ((moveNum % 2) == 0) {
            return VIOLET;
        } else {
            return ORANGE;
        }
    }

    /** Returns true iff MOVE is a syntactically correct move. */
    static boolean isWellFormed(String move) {
        return Pattern.matches("[WZILUTXVFPYNzidst3v21]"
                               + "[0-9a-d]" + "[0-9a-d]"
                               + "[0-7]",
                               move);

    }
    /** Returns true iff MOVE is currently legal. */
    boolean isLegal(String move) {
        Color playersMove = playerOnMove();
        String pieceName = move.substring(0, 1);
        if (!withinBounds(move)) {
            return false;
        }
        if (playersMove == ORANGE) {
            if (moveNum == 1) {
                if (!validFirstMove(move)) {
                    return false;
                }
            }
            if (orangePieceUsed(pieceName)) {
                return false;
            } else if (touchingEdge(move)) {
                return false;
            } else if (!touchingCorner(move)) {
                if (moveNum != 1) {
                    return false;
                }
            }
            return true;
        } else {
            if (moveNum == 2) {
                if (!validFirstMove(move)) {
                    return false;
                }
            }
            if (violetPieceUsed(pieceName)) {
                return false;
            } else if (touchingEdge(move)) {
                return false;
            } else if (!touchingCorner(move)) {
                if (moveNum != 2) {
                    return false;
                }
            }
            return true;
        }
    }
    /** return an INT of number of total moves made. */
    int getMoveNum() {
        return moveNum;
    }
    /** increment the number of total moves made. */
    void incrementMoveNum() {
        moveNum = moveNum + 1;
    }
    /** Set the number of total moves made with N. */
    void setMoveNum(int n) {
        moveNum = n;
    }
    /** Add a PIECE name to the UsedOrange list. */
    void addUsedOrange(String piece) {
        usedOrange.add(piece);
    }
    /** Add a PIECE name to the UsedViolet list. */
    void addUsedViolet(String piece) {
        usedViolet.add(piece);
    }
    /** Checks if an Orange piece has been used, return boolean.
     * Check for PIECE. */
    boolean orangePieceUsed(String piece) {
        return usedOrange.contains(piece);
    }
    /** Checks if an Violet piece has been used, return boolean.
     * Check for PIECE. */
    boolean violetPieceUsed(String piece) {
        return usedViolet.contains(piece);
    }
    /** Returns UsedViolet. */
    ArrayList<String> getUsedViolet() {
        return usedViolet;
    }
    /** Returns UsedOrange. */
    ArrayList<String> getUsedOrange() {
        return usedOrange;
    }
    /** Returns the hashmap of pieces. */
    HashMap getPIECES() {
        return piecesHash;
    }
    /** Returns the board. */
    Color[][] getBoard() {
        return gameBoard;
    }
    /** Hexadecimal value. */
    private final int hexaVal = 16;
    /** Checks if the move made is a valid first move (corner move).
     * Return boolean, takes in MOVE. */
    boolean validFirstMove(String move) {
        String pieceName = move.substring(0, 1);
        int col = Integer.parseInt(move.substring(1, 2), hexaVal);
        int row = Integer.parseInt(move.substring(2, 3), hexaVal);
        int orientation = Integer.parseInt(move.substring(3, 4));
        Piece testPiece = piecesHash.get(pieceName);
        Piece rotPiece = testPiece.rotate(orientation);
        int[][] configuration = rotPiece.getConfiguration();
        int pieceRowNum = configuration[0].length;
        int pieceColNum = configuration.length;
        if ((col == 0) && (row == 0)) {
            if (configuration[0][pieceRowNum - 1] == 1) {
                return true;
            }
        }
        if ((col + pieceColNum - 1 == SIZE - 1)
            && (row + pieceRowNum - 1 == SIZE - 1)) {
            if (configuration[pieceColNum - 1][0] == 1) {
                return true;
            }
        }
        if ((col + pieceColNum - 1 == SIZE - 1)
            && (row == 0)) {
            if (configuration[pieceColNum - 1][pieceRowNum - 1] == 1) {
                return true;
            }
        }
        if ((col == 0)
            && (row + pieceRowNum - 1 == SIZE - 1)) {
            if (configuration[0][0] == 1) {
                return true;
            }
        }
        return false;
    }
    /** Checks if the move made is within the bounds of the board.
     * Return boolean. Takes in MOVE. */
    boolean withinBounds(String move) {
        String pieceName = move.substring(0, 1);
        int col = Integer.parseInt(move.substring(1, 2), hexaVal);
        int row = Integer.parseInt(move.substring(2, 3), hexaVal);
        int orientation = Integer.parseInt(move.substring(3, 4));
        Piece testPiece = piecesHash.get(pieceName);
        Piece rotPiece = testPiece.rotate(orientation);
        int[][] configuration = rotPiece.getConfiguration();
        int pieceRowNum = configuration[0].length;
        int pieceColNum = configuration.length;
        if (row + pieceRowNum - 1 > SIZE - 1) {
            return false;
        }
        if (col + pieceColNum - 1 > SIZE - 1) {
            return false;
        }
        return true;
    }
    /** Checks if the move made touches other same-color pieces.
     * Return true if it does. Takes in MOVE. */
    boolean touchingEdge(String move) {
        Color playersMove = playerOnMove();
        String pieceName = move.substring(0, 1);
        int col = Integer.parseInt(move.substring(1, 2), hexaVal);
        int row = Integer.parseInt(move.substring(2, 3), hexaVal);
        int orientation = Integer.parseInt(move.substring(3, 4));
        Piece testPiece = piecesHash.get(pieceName);
        Piece rotPiece = testPiece.rotate(orientation);
        int[][] configuration = rotPiece.getConfiguration();
        int pieceRowNum = configuration[0].length;
        int pieceColNum = configuration.length;
        for (int[] x : configuration) {
            int rowindex = SIZE - row - pieceRowNum;
            for (int y : x) {
                if (y == 1) {
                    if (gameBoard[col][rowindex] != EMPTY) {
                        return true;
                    }
                    if (rowindex > 0) {
                        if (gameBoard[col][rowindex - 1] == playersMove) {
                            return true;
                        }
                    }
                    if (rowindex < SIZE - 1) {
                        if (gameBoard[col][rowindex + 1] == playersMove) {
                            return true;
                        }
                    }
                    if (col < SIZE - 1) {
                        if (gameBoard[col + 1][rowindex] == playersMove) {
                            return true;
                        }
                    }
                    if (col > 0) {
                        if (gameBoard[col - 1][rowindex] == playersMove) {
                            return true;
                        }
                    }
                }
                rowindex += 1;
            }
            col += 1;
        }
        return false;
    }
    /** Checks if one of the piece's corners is touching another same-color.
     * Return true if its corner is touching another same-color corner.
     * Takes in MOVE.
     */
    boolean touchingCorner(String move) {
        Color playersMove = playerOnMove();
        String pieceName = move.substring(0, 1);
        int col = Integer.parseInt(move.substring(1, 2), hexaVal);
        int row = Integer.parseInt(move.substring(2, 3), hexaVal);
        int orientation = Integer.parseInt(move.substring(3, 4));
        Piece testPiece = piecesHash.get(pieceName);
        Piece rotPiece = testPiece.rotate(orientation);
        int[][] configuration = rotPiece.getConfiguration();
        int pieceRowNum = configuration[0].length;
        int pieceColNum = configuration.length;
        for (int[] x : configuration) {
            int rowindex = SIZE - row - pieceRowNum;
            for (int y : x) {
                if (y == 1) {
                    if ((rowindex > 0) && (col < SIZE - 1)) {
                        if (gameBoard[col + 1][rowindex - 1] == playersMove) {
                            return true;
                        }
                    }
                    if ((rowindex > 0) && (col > 0)) {
                        if (gameBoard[col - 1][rowindex - 1] == playersMove) {
                            return true;
                        }
                    }
                    if ((rowindex < SIZE - 1) && (col < SIZE - 1)) {
                        if (gameBoard[col + 1][rowindex + 1] == playersMove) {
                            return true;
                        }
                    }
                    if ((rowindex < SIZE - 1) && (col > 0)) {
                        if (gameBoard[col - 1][rowindex + 1] == playersMove) {
                            return true;
                        }
                    }
                }
                rowindex += 1;
            }
            col += 1;
        }
        return false;
    }
}
