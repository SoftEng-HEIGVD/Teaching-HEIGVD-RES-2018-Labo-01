package ch.heigvd.res.lab01.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
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
    String[] result = {"", ""};
    int index;

    // We are looking for patterns in lines, placing \r\n before \r because this pattern is longer and otherwise
    // we would never find \r\n but always \r
    if (0 < (index = lines.indexOf("\r\n"))) {
      result[0] = lines.substring(0, index+2); // Increasing the index of 2: because \r\n is 2 chars
      result[1] = lines.substring(index+2);
    }
    // We do the same if \r or \n is found, taking the character is account
    else if (0 < (index = lines.indexOf('\r')) || 0 < (index = lines.indexOf('\n'))) {
      result[0] = lines.substring(0, index + 1);
      result[1] = lines.substring(index + 1);
    }
    // Otherwise we store all in result[1]
    else {
      result[1] = lines;
    }

    return result;
  }

}
