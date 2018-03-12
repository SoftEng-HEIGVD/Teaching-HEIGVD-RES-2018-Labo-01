package ch.heigvd.res.lab01.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * Modified by : Léo Cortès
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
        String[] output = new String[2];

        // Will store the position of the first apparition of \n \r and \r\n
        ArrayList<Integer> linePosition = new ArrayList();

        int posUnix;
        int posMac;
        int posWin;

        // Finding the first position of \n \r and \r\n
        if ((posWin = lines.indexOf("\r\n")) >= 0) {
            linePosition.add(posWin);
        }
        if ((posUnix = lines.indexOf("\n")) >= 0) {
            linePosition.add(posUnix);
        }
        if ((posMac = lines.indexOf("\r")) >= 0) {
            linePosition.add(posMac);
        }

        // The position of the first apparition of \n or \ or \r\n
        int firstPos;
        if (linePosition.isEmpty()) {    // If there is no newline
            output[0] = "";
            output[1] = lines;
            return output;
        } else // Else, there is at least one new line, we must find wich one is the first
        {
            firstPos = (int) (Collections.min(linePosition));
        }

        // If there is \r\n, the substring will be one char longer 
        if (firstPos == posWin) {
            output[0] = lines.substring(0, firstPos + 2);
            output[1] = lines.substring(firstPos + 2, lines.length());
        } else {
            output[0] = lines.substring(0, firstPos + 1);
            output[1] = lines.substring(firstPos + 1, lines.length());
        }

        return output;
    }

}
