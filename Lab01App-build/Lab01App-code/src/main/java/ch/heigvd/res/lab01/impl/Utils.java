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

        // Regex comment : "?<=" zero-width positive lookbehind.
        //                  | simple OR
        // From : https://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
        // From : https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html

        // if there is '\r' and directly '\n', we split this line in two and whitout the '\r'\n'
        if (lines.contains("\r\n"))
            return lines.split("(?<=\r\n)", 2);

            // if there is one of the two separator Character, we split the line.
        else if (lines.contains("\r") || lines.contains("\n"))
            return lines.split("(?<=\n)|(?<=\r)", 2);

        // nothing to split.
        return new String[]{"", lines};
    }

}
