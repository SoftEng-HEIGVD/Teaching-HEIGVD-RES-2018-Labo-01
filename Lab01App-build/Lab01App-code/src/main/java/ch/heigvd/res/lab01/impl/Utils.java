package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Yosra Harbaoui
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
        /**
         * the /r/n should be tested first otherwise the result will be : the
         * first element will be next line with \r, the second element will be
         * \n + the remaining text
         */
        String[] separators = {"\r\n", "\r", "\n"};

        /**
         * find out the positions of the separators and return the array
         */
        for (String separator : separators) {
            int separatorPosition = lines.indexOf(separator);
            if (separatorPosition != -1) {
                int separatorLength = separator.length();
                return new String[]{lines.substring(0, separatorPosition + separatorLength),
                    lines.substring(separatorPosition + separatorLength)};
            }
        }

        /**
         * if the argument does not contain any line separator
         */
        return new String[]{"", lines};
    }

}
