package duo;

/** Piece class that represents a piece.
 * @author Ken Szubzda (cs61b-ek)
 */

class Piece {
    /** Instance variable for the CONFIGURATION. */
    private int[][] configuration;
    /** Piece constructor takes in an array of int arrays CONFIG.
     * Representing the piece configuration. */
    Piece(int[][] config) {
        configuration = config;
    }
    /** Rotate takes in an ORIENTATION number.
     * Calls the rotate90 method a number of times
     * based on the number and return new Piece
     * with the correct new configuration. */
    Piece rotate(int orientation) {
        boolean needsflip = false;
        int[][] newConfig = configuration;
        if (orientation > 3) {
            needsflip = true;
            orientation -= 4;
        }
        while (orientation != 0) {
            newConfig = rotate90(newConfig);
            orientation -= 1;
        }
        if (needsflip) {
            newConfig = flipConfiguration(newConfig);
        }
        return new Piece(newConfig);
    }



    /** Rotate90 changes the configuration to rotate 90
     * degrees each time it's called.
     * @return int[][] of CONFIG */
    int[][] rotate90(int[][] config) {
        int numColumns = config.length;
        int numRows = config[0].length;
        int[][] newConfiguration = new int[numRows][numColumns];
        int rowindex = 0;
        for (int[] x : config) {
            int columnindex = numRows - 1;
            for (int y : x) {
                newConfiguration[columnindex][rowindex] = y;
                columnindex -= 1;
            }
            rowindex += 1;
        }
        return newConfiguration;
    }

    /** flipConfiguration flips the configuration.
     * @return int[][] of CONFIG rotated. */
    int[][] flipConfiguration(int[][] config) {
        int numColumns = config.length;
        int numRows = config[0].length;
        int[][] newConfiguration = new int[numColumns][numRows];
        int columnindex = numColumns - 1;
        for (int[] x : config) {
            newConfiguration[columnindex] = x;
            columnindex -= 1;
        }
        return newConfiguration;
    }
    /** return the CONFIGURATION. */
    int[][] getConfiguration() {
        return configuration;
    }
}
