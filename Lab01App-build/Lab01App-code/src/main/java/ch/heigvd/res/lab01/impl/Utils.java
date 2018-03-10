package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 * @author Olivier Liechti
 */
public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName());

    /**
     * This method looks for the next new line separators (\r, \n, \r\n) to extract
     * the next line in the string passed in arguments.
     *
     * @param lines a string that may contain 0, 1 or more lines
     * @return an array with 2 elements; the first element is the next line with
     * the line separator, the second element is the remaining text. If the argument does not
     * contain any line separator, then the first element is an empty string.
     */
    public static String[] getNextLine(String lines) {
        String[] results = new String[2];

        // first, we store the values in our result string[] so that if the argument does not contain any line separator
        // we just have to return the results tab
        results[0] = "";
        results[1] = lines;

        // we iterate over the string until a line separator is found. If we find one, we treat it, stop the loop and
        // return the results tab.
        for (int i = 0; i < lines.length(); ++i) {
            if (lines.charAt(i) == '\r' || lines.charAt(i) == '\n') {
                // check if there is \r\n. If it's the case, store the line and both line separators in the first
                // slot of the tab and the remaining string in the second.
                if (i + 1 < lines.length() && lines.charAt(i + 1) == '\n') {
                    results[0] = lines.substring(0, i + 2);
                    results[1] = lines.substring(i + 2, lines.length());
                } else {
                    results[0] = lines.substring(0, i + 1);
                    results[1] = lines.substring(i + 1, lines.length());
                }
                break;
            }
        }
        return results;
    }

}
