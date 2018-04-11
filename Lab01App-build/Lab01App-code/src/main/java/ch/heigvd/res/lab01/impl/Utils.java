package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName());

    private static final String WINDOWS  = "\r\n";
    private static final String OSX      = "\r";
    private static final String LINUX    = "\n";

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

        if(lines.contains(WINDOWS))
            return format(lines, WINDOWS);

        if(lines.contains(OSX))
            return format(lines, OSX);

        if(lines.contains(LINUX))
            return format(lines, LINUX);

        return new String[]{"", lines};
    }

    /**
     * Avoids code duplication, normalize string split for global return format
     * @param lines     text to split
     * @param divider   detected OS divider
     * @return          Standard return formatted string array
     */
    private static String[] format(String lines, String divider) {

        String[] result = lines.split(divider, 2);
        result[0] += divider;
        return result;
    }

}
