package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti modify by : Olivier Kopp
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
    * argument does not contain any line separator, then the first element is an
    * empty string.
    */
   public static String[] getNextLine(String lines) {
      String firstElement = "";
      String secondElement = lines;
      for (int i = 0; i < lines.length(); i++) {
         firstElement += lines.charAt(i);
         if (lines.charAt(i) == '\r') {
            if (i + 1 < lines.length() && lines.charAt(i + 1) == '\n') {
               firstElement += lines.charAt(i + 1);
               secondElement = lines.substring(i + 2);
            } else {
               secondElement = lines.substring(i + 1);
            }
            break;
         } else if (lines.charAt(i) == '\n') {
            secondElement = lines.substring(i + 1);
            break;
         }
      }
      if (secondElement.equals(lines)) {
         firstElement = "";
      }
      String[] stringArray = {firstElement, secondElement};
      return stringArray;
   }

}
