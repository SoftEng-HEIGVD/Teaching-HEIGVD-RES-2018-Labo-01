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
    for (int i = 0; i < lines.length(); i++) {

      // sep is \n
      if (lines.charAt(i) == '\n') {
        return new String[]{lines.substring(0, i+1), lines.substring(i+1)};
      }

      // sep is \r or \r\n
      if (lines.charAt(i) == '\r') {
        if (i + 1 != lines.length()) { // there is a next char
          if (lines.charAt(i + 1) == '\n') { //next char is \n
            return new String[]{lines.substring(0, i+2), lines.substring(i+2)};
          } else { // only \r
            return new String[]{lines.substring(0, i+1), lines.substring(i+1)};
          }
        } else { // \r is end of string
          return new String[]{lines, ""};
        }
      }
    }
    return new String[]{"", lines};
  }

}
