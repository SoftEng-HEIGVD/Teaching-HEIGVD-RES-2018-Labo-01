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
        
        if(lines != null) {
            
            //ret[0] contiendra la premiere ligne de lines, ret[1] le reste
            String ret[] = new String[2];
            int lastIndex = lines.length();


            for(int i = 0; i < lines.length(); i++) {

                if(lines.charAt(i) == '\r') {
                    //est-ce que l'on a atteint le bour de la chaine lines?
                    //le '\r' est-il suivi d'un '\n' ?
                    if(i != (lines.length() - 1) && lines.charAt(i + 1) == '\n') {                       
                        ret[0] = lines.substring(0, i + 2);
                        ret[1] = lines.substring(i + 2, lastIndex);

                        return ret;

                    } else {
                        ret[0] = lines.substring(0, i + 1);
                        ret[1] = lines.substring(i + 1, lastIndex);

                        return ret;
                    }
                } else if(lines.charAt(i) == '\n') {
                    ret[0] = lines.substring(0, i + 1);
                    ret[1] = lines.substring(i + 1, lastIndex);

                    return ret;
                }
            }

            //si l'on n'a pas rencontre de separateurs, lines ne contient 
            //qu'une seule ligne
            ret[0] = "";
            ret[1] = lines;

            return ret;
            
        } else {
            return null;
        }
    }
}