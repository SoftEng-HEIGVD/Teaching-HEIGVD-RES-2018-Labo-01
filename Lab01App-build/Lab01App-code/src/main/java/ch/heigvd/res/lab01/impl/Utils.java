package ch.heigvd.res.lab01.impl;

import java.io.Writer;
import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName());

    private static final String WINDOWS = "\r\n";
    private static final String MAC_OS = "\r";
    private static final String LINUX = "\n";

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

        //store the system used
        String sys = whichSystem(lines);
        String[]arrayStr = new String[2];

        if (sys != "") {

            //split the String in two part
            String[] s = lines.split(sys, 2);
            arrayStr[0] = s[0] + sys;
            arrayStr[1] = s[1];
        }
        else{
            arrayStr[0] = "";
            arrayStr[1] = lines;
        }

        return arrayStr;
    }

    /**
     * Return the name of the use system
     *
     * @param s the system used
     * @return the name of the use system
     */
    private static String whichSystem(String s){

        if(s.contains(WINDOWS)){
            return WINDOWS;
        }
        else if(s.contains(MAC_OS)){
            return MAC_OS;
        }
        else if(s.contains(LINUX)){
            return LINUX;
        }
        return "";
    }
}
