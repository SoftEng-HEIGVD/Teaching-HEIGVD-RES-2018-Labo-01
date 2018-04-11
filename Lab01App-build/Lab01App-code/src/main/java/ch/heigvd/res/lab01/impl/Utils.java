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
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");

    int posR = lines.indexOf('\r');
    int posN = lines.indexOf('\n');
    int eol = -1;
    String[] ret = new String[2];
    if (posR != -1 && posN != posR + 1) {
      eol = posR;
    }
    else if (posN != -1) {
      eol = posN;
    }

    ret[0] = lines.substring(0, eol+1);
    ret[1] = lines.substring(eol+1);
    return ret;
  }

}
