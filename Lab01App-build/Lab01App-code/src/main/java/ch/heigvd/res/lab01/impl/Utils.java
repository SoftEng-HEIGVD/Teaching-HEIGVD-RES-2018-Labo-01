package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName());
    private static final String WIN_SEPARATOR = "\r\n",
                                MACOS9_SEPARATOR = "\r",
                                UNIX_SEPARATOR = "\n";

    /**
     * This method looks for the next new line separators (\r, \n, \r\n) to
     * extract the next line in the string passed in arguments.
     *
     * @param lines a string that may contain 0, 1 or more lines
     * @return an array with 2 elements; the first element is the next line with
     * the line separator, the second element is the remaining text. If the
     * argument does not contain any line separator, then the first element is
     * an empty string.
     */
    public static String[] getNextLine(String lines) {

        String[] nextLine;

        // the doesn't contain any line separator
        if (!lines.contains(WIN_SEPARATOR) && !lines.contains(MACOS9_SEPARATOR) && !lines.contains(UNIX_SEPARATOR)) {
            return new String[]{"", lines};
            
        } // the argument contain WINDOWS line separator
        else if (lines.contains(WIN_SEPARATOR)) {
            nextLine = lines.split(WIN_SEPARATOR, 2);
            nextLine[0] = nextLine[0] + WIN_SEPARATOR;

        } // the argument contain MACOS9 line separator
        else if (lines.contains(MACOS9_SEPARATOR)) {
            nextLine = lines.split(MACOS9_SEPARATOR, 2);
            nextLine[0] = nextLine[0] + MACOS9_SEPARATOR;

        } // the argument contain UNIX line separator
        else {
            nextLine = lines.split(UNIX_SEPARATOR, 2);
            nextLine[0] = nextLine[0] + UNIX_SEPARATOR;
        }
        return nextLine;
    }

}
