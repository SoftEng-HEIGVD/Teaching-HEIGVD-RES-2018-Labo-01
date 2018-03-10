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

        String[] separators = {"\r\n", "\n", "\r"};         //Possible separators
        int[] positionSeparators = new int[]{-1, -1, -1};   //Position of each separator
        int indexFrom = 0;                                  //Index after which the separator search is made
        int positionSeparator = lines.length();             //Position of the separator found (worst case for initialisation)
        String separator = "";                              //Separator found

        //Find the first separator in the text
        for (int i = 0; i < separators.length; i++) {
            positionSeparators[i] = lines.indexOf(separators[i], indexFrom);

            if(positionSeparators[i] < positionSeparator && positionSeparators[i] != -1){
                positionSeparator = positionSeparators[i];
                separator = separators[i];
            }
        }

        //Get the two parts of the text
        String firstPart = "";
        String secondPart = "";

        //Separator has been found
        if(positionSeparator != lines.length()) {
            indexFrom = positionSeparator + separator.length();

            firstPart = lines.substring(0, indexFrom);
            secondPart = lines.substring(indexFrom, lines.length());
        }
        //No separator found
        else{
            secondPart = lines;
        }

        return new String[]{firstPart, secondPart};
    }
}