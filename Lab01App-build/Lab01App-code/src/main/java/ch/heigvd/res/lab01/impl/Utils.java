package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti, modified by Lionel Nanchen
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

    String[] output = new String[2];
    int index = lines.indexOf("\r\n");
    int size = 2;

    if (index < 0) {
      size = 1;
      index = lines.indexOf('\n');
      if (index < 0) {
        index = lines.indexOf('\r');
      }
    }

    if (index < 0) {
      output[0] = "";
      output[1] = lines;
    } else {
      output[0] = lines.substring(0, index + size);
      output[1] = lines.substring(index + size);
    }

    return output;
  }
}
