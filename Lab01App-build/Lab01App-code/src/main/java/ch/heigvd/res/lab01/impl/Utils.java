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

        String[] separators = {"\r\n", "\n", "\r"};     //Possible separators
        int positionSeparator = -1;                     //Position of the separator found
        String separator = "";                          //Separator found
        int indexFrom = 0;                              //Index after which the separator search is made

        //Find the first separator in the text
        for (String s : separators) {

            positionSeparator = lines.indexOf(s, indexFrom);

            if(positionSeparator != -1){
                separator = s;
                break;
            }
        }

        //Get the two parts of the text
        String firstPart = "";
        String secondPart = "";
        indexFrom = positionSeparator + separator.length();

        if(indexFrom != -1) {
            firstPart = lines.substring(0, indexFrom);
            secondPart = lines.substring(indexFrom, lines.length());
        }
        else{
            secondPart = lines;
        }

        return new String[]{firstPart, secondPart};
    }
}