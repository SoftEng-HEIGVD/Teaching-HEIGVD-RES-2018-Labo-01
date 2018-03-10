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
        for (int i = 0; i < lines.length(); i++) {

            // '\r' element
            if(lines.charAt(i) == '\r') {

                // '\r\n' element
                if(i+1 < lines.length() && lines.charAt(i+1) == '\n') {
                    // i is incremented because '\r\n' is represented by 2 characters
                    i++;
                }

                return splitStringHalf(lines, i+1);
            }

            // '\n' element
            else if(lines.charAt(i) == '\n') {
                return splitStringHalf(lines, i+1);
            }
        }
        return new String[]{"", lines};
    }

    /**
     * This method cut a String in half depending on the position passed in argument.
     *
     * @param s a string that may contain 0, 1 or more lines
     * @param position the separator position
     * @return an array with the first and the second part of the cut.
     */
    private static String[] splitStringHalf(String s, int position) {
        return new String[]{s.substring(0, position), s.substring(position)};
    }

}
