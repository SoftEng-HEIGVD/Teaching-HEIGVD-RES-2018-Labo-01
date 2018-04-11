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
        // Initializing table
        String[] nextLine = {"", ""};
        boolean thereIsALine = false;
        int i = 0;

        for (; i < lines.length(); ++i) {
            // if we fing the '\r' sparator
            if (lines.charAt(i) == '\r') {
                // we check if the next one is a '\n'
                if (i + 1 < lines.length() && lines.charAt(i + 1) == '\n') {
                    // if so, we update the iterator
                    ++i;
                }
                // we signal that there's a new line
                thereIsALine = true;
                break;
            } else if (lines.charAt(i) == '\n') { // if we find the '\n' separator
                // we signal that there's a new line
                thereIsALine = true;
                break;
            }
        }
        if (thereIsALine) {
            // getting the line with separator
            nextLine[0] = lines.substring(0, i + 1);
            // getting the entire text
            nextLine[1] = lines.substring(i + 1);
        } else {
            // getting the whole text
            nextLine[1] = lines;
        }
        return nextLine;
    }

}
