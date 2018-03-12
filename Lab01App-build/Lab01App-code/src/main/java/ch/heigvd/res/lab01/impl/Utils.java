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

      String[] tabReturn = {"", ""};

      int lineSeparator;
      
      
      if((lineSeparator = lines.indexOf("\r\n")) == -1){ // true if no windows separator
         if((lineSeparator = lines.indexOf("\r")) == -1) { // true if no OSX separator
            if((lineSeparator = lines.indexOf("\n")) == -1) { // true if no Linux separator
                tabReturn[1] = lines;
                return tabReturn; // if no separator
            }
         }
         lineSeparator++; // if Linux or OSX separator found
      } else {
          lineSeparator = lineSeparator + 2; // if windows separator found
      }
      
      tabReturn[0] = lines.substring(0, lineSeparator);
      tabReturn[1] = lines.substring(lineSeparator, lines.length());
      
      return tabReturn;
  }

}
