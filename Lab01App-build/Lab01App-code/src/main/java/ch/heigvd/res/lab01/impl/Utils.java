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
      String[] stringArray = {"", ""};
      //for windows
      if (lines.contains("\r\n")) {
         stringArray = lines.split("\r\n", 2);
         stringArray[0] += "\r\n";
      } //for MACOSX
      else if (lines.contains("\r")) {
         stringArray = lines.split("\r", 2);
         stringArray[0] += "\r";
      } //for UNIX
      else if (lines.contains("\n")) {
         stringArray = lines.split("\n", 2);
         stringArray[0] += "\n";
      } else {
         stringArray[1] = lines;
      }
      return stringArray;
   }

}
