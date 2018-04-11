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
        String[] output = {"", ""};
        boolean firstLine = true;
        char[] cbuf = lines.toCharArray();
        if (lines.contains("\n") || lines.contains("\r") || lines.contains("\r\n")) {

            for (int i = 0; i < cbuf.length; i++) {
                if (firstLine) {
                    output[0] += cbuf[i];
                    if ((cbuf[i] == '\n') || (cbuf[i] == '\r' && (i == cbuf.length - 1 || cbuf[i + 1] != '\n'))) {
                        firstLine = false;
                    }
                } else {
                    output[1] += cbuf[i];
                }
            }
        } else {
            output[1] = lines;
        }

        return output;
    }

}
