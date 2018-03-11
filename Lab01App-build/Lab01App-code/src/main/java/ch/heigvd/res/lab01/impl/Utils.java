package ch.heigvd.res.lab01.impl;

import java.util.Scanner;
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

        String[] linesSplited;
        if (lines.contains("\r\n")) {
            linesSplited = lines.split("(?<=\r\n)", 2);//|(?<=\r)|(?<=\n)
        } else if (lines.contains("\r") || lines.contains("\n")) {
            linesSplited = lines.split("(?<=\\r)|(?<=\\n)", 2);//|(?<=\r)|(?<=\n)
        } else {
            linesSplited = new String[]{"", lines};
        }
        if (linesSplited.length == 1) {
            linesSplited = new String[]{linesSplited[0], ""};
        }
        return linesSplited;
    }

}
