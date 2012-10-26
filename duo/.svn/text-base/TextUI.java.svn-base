package duo;

import java.util.Scanner;
import static duo.Color.*;

/** A simple textual implementation of the user interface.
 *  @author Ken Szubzda (cs61b-ek)
 */
class TextUI implements UI {
    /** Scanner to take user input. */
    private Scanner sc = new Scanner(System.in);
    @Override
    public String getMove(Color color, int numMoves, String prevMove) {
        String colorstring;
        if (color == ORANGE) {
            colorstring = "ORANGE";
        } else {
            colorstring = "VIOLET";
        }
        System.out.println("");
        System.out.println("Previous Move: " + prevMove);
        System.out.println("Move number: " + numMoves);
        System.out.println("Player " + colorstring + ", enter your move:");
        return sc.next();
    }

    @Override
    public void reportWinner(int orangeScore, int violetScore) {
        if (orangeScore > violetScore) {
            System.out.println("Orange wins (" + orangeScore
                               + "-" + violetScore + ")");
        } else if (violetScore > orangeScore) {
            System.out.println("Violet wins (" + orangeScore
                               + "-" + violetScore + ")");
        } else {
            System.out.println("Tie game (" + orangeScore
                               + "-" + violetScore + ")");
        }
        System.exit(0);
    }

    @Override
    public void reportBoard(Board board) {
        for (int row = board.SIZE - 1; row >= 0; row -= 1) {
            if (row != board.SIZE - 1) {
                System.out.println("");
            }
            System.out.print("  ");
            for (int col = 0; col < board.SIZE; col += 1) {
                if (board.get(col, row) == EMPTY) {
                    System.out.print("-");
                } else if (board.get(col, row) == ORANGE) {
                    System.out.print("O");
                } else {
                    System.out.print("V");
                }
            }
        }
        System.out.println("");
    }

    @Override
    public void reportBoardStandard(Board board) {
        System.out.println("===");
        for (int row = board.SIZE - 1; row >= 0; row -= 1) {
            if (row != board.SIZE - 1) {
                System.out.println("");
            }
            System.out.print("  ");
            for (int col = 0; col < board.SIZE; col += 1) {
                if (board.get(col, row) == EMPTY) {
                    System.out.print("-");
                } else if (board.get(col, row) == ORANGE) {
                    System.out.print("O");
                } else {
                    System.out.print("V");
                }
            }
        }
        System.out.println("");
        System.out.println("===");
    }

    @Override
    public void reportMove(Color color, int numMoves, String move) {
        System.out.println(move);
    }

    @Override
    public void reportError(String message) {
        System.err.println(message);
    }

    @Override
    public void report(String message) {
        System.out.println(message);
    }

}
