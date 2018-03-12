package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
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
        if (lines.equals("")) return new String[2];
        String[] strings = new String[2];
        if (lines.substring(lines.length()-1).contains("\n")) {
            strings[0] = lines.substring(0, lines.indexOf("\n", 0)+1);
            strings[1] = lines.substring(strings[0].length(), lines.length());
        } else if (lines.substring(lines.length()-1).contains("\r")) {
            strings[0] = lines.substring(0, lines.indexOf("\r", 0)+1);
            strings[1] = lines.substring(strings[0].length(), lines.length());
        } else if (lines.substring(lines.length()-1).contains("\r\n")) {
            strings[0] = lines.substring(0, lines.indexOf("\r\n", 0)+1);
            strings[1] = lines.substring(strings[0].length(), lines.length());
        }

        //Check if the given line ends with an endline character
        if (!lines.endsWith("\n") && !lines.endsWith("\r") && !lines.endsWith("\r\n")) {
            strings[0] = "";
            strings[1] = lines;
        }

        return strings;
    }

}
