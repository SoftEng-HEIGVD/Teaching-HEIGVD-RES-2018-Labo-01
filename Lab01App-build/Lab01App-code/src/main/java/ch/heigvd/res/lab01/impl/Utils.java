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

        final char CARRIAGE = '\r';
        final char LINEFEED = '\n';
        boolean canReturn = false;
        String[] result = new String[2];

        //we go through the given string, until we met a break line
        for(int i = 0; i < lines.length(); i++) {

            //MACOS and Windows break line
            if(lines.charAt(i) == CARRIAGE){
                //windows second part of break line
                if(i != lines.length() - 1 && lines.charAt(i + 1) == LINEFEED){
                    i++;
                }
                canReturn = true;
            } else if(lines.charAt(i) == LINEFEED){ // Linux break line
                canReturn = true;
            }

            //if we encounter a break line of the third type, we split the string
            if(canReturn){
                result[0] = lines.substring(0, i + 1);
                result[1] = lines.substring(i + 1);

                return result;
            }
        }
        //if no break line were encountered :
        result[0] = "";
        result[1] = lines;

        return result;
    }
}
