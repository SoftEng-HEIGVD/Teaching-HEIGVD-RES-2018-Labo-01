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

        String spliter;

        if (lines.indexOf("\r\n") > -1) {
            spliter = "\r\n";
        } else if (lines.indexOf("\r") > -1) {
            spliter = "\r";
        } else if (lines.indexOf("\n") > -1) {
            spliter = "\n";
        } else {
            spliter = "";
        }

        String[] separatedLines;
        if(spliter.equals("")){
            separatedLines = new String[]{"", lines};
        } else {
            separatedLines = lines.split(spliter, 2);
            separatedLines[0] += spliter;
            if(separatedLines.length == 1){
                separatedLines[1] = "";
            }
        }
        return separatedLines;
    }

}
