package ch.heigvd.res.lab01.impl;

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
        
        // declaration and initialization of the returned array
        String res[] = {"", lines};
        int length = lines.length();
        // find separator's position
        int beginningPosSecondLine = -1;
        for (int i = 0; i < length; i++) {

            // see if current char is a separator
            if (lines.charAt(i) == '\r') {
                beginningPosSecondLine = i + 1;
                if ((i + 1) < length && lines.charAt(i + 1) == '\n') {
                    beginningPosSecondLine++;
                }
                break;
                
            } else if (lines.charAt(i) == '\n') {
                beginningPosSecondLine = i + 1;
                break;
            }
        }

        // fill result array
        if (!(beginningPosSecondLine == -1)) {
            res[0] = lines.substring(0, beginningPosSecondLine);
            res[1] = lines.substring(beginningPosSecondLine);
        }

        return res;

        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

}
