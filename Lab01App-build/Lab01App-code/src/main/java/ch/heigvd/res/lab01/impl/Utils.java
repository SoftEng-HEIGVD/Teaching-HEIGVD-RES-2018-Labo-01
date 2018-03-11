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

        //Detection of a splitter in the lines
        //windows version
        if (lines.indexOf("\r\n") > -1) {
            spliter = "\r\n";
            //macOS version
        } else if (lines.indexOf("\r") > -1) {
            spliter = "\r";
            //linux version
        } else if (lines.indexOf("\n") > -1) {
            spliter = "\n";
            //no splitter found
        } else {
            spliter = "";
        }


        String[] separatedLines;
        //if there was no splitter, then the method returns a String[], with a "" at index 0 and the only line at index 1
        if (spliter.equals("")) {
            separatedLines = new String[]{"", lines};
        } else {
            //the line is splitted in two strings (the first line at index 0, and all the others at index 1)
            separatedLines = lines.split(spliter, 2);
            //the splitter (removed by the split operation, is re-added at the end of the first line)
            separatedLines[0] += spliter;
            //if there was only one line, the split operation returns a String[] with length == 1. In this case, we
            // add the second element "" at the index 1 of separatedLines
            if (separatedLines.length == 1) {
                separatedLines[1] = "";
            }
        }
        return separatedLines;
    }

}
