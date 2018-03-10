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
        // split into multiple lines with regex found on :
        // https://stackoverflow.com/questions/454908/split-java-string-by-new-line

        String lineSeparator = (lines.indexOf("\r\n") > -1 ) ? "\r\n" : lines.indexOf("\r") > -1 ? "\r" :  lines.indexOf("\n") > 1 ? "\n" : "";

        // first check if no separator found simply return lines in second element
        if(lineSeparator == ""){
            return new String[] { "" , lines };
        }

        // otherwise let the party begin !
        String[] separateLines = lines.split("[\\r\\n]+");

        String first = "";      // gonna contain the first line of lines
        String next = "";       // gonna contain the rest

        // check if there are multiple lines or just one
        if(separateLines.length > 1){

            first = separateLines[0] + lineSeparator;

            //System.out.println("fist: " + first);

            // merge all other lines than the first
            for(int i = 1; i < separateLines.length; i++){
                next = next + separateLines[i] + lineSeparator;
            }
            //System.out.println("next: " + next);
        }
        // if there was only one line  but it ended with a separator we throw back an empty second string
        else {
            //System.out.println("no char !");
            first = lines;
        }
        return new String[] {first, next };
    }

}
