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
        String[] tabString = new String[2];

        int pos = 0;
        for(; pos < lines.length(); ++pos){
            // we look for the first new line separator
            if(lines.charAt(pos) == '\n' || lines.charAt(pos) == '\r') {
                // if the line separator encountered id '\r' we have to check if the next char is '\n'
                // if it is, we simply include the char in the line by incrementing the position.
                if(pos+1 < lines.length() && lines.charAt(pos) == '\r' && lines.charAt(pos+1) == '\n') {
                    pos++;
                }

                //we found the position of the line separator, so we leave the loop
                break;
            }
        }

        // filling the array with the first line in the first cell and the rest of the text in the second
        if(pos != lines.length()){
            tabString[0] = lines.substring(0, pos+1);
            tabString[1] = lines.substring(pos+1);
        } else{
            tabString[0] = "";
            tabString[1] = lines;
        }

        return tabString;
    }

}
