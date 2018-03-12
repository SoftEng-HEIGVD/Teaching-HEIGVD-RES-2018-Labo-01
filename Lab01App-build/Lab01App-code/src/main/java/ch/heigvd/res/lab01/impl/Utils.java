package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName());

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
        // Implementation according to the specification just above,
        // with the help of substring()
        String[] nextLine = new String[2];
        int separator1 = lines.indexOf('\r');
        int separator2 = lines.indexOf('\n');
        if (separator1 != -1 || separator2 != -1) {
            if (separator2 != -1) {
                nextLine[0] = lines.substring(0, separator2 + 1);
                nextLine[1] = lines.substring(separator2 + 1);
            } else{
                nextLine[0] = lines.substring(0, separator1 + 1);
                nextLine[1] = lines.substring(separator1 + 1);
            }
        } else {
            nextLine[0] = "";
            nextLine[1] = lines;
        }

        return nextLine;
    }

}
