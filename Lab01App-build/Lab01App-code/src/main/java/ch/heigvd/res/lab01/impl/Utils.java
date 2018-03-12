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
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    int separatorIndex = -1;
    String[] splitted = new String[2];
    int separatorLength = 0;
    if((separatorIndex = lines.indexOf("\r\n")) != -1) {
      separatorLength = 2;
    } else if ((separatorIndex = lines.indexOf("\n")) != -1
            || (separatorIndex = lines.indexOf("\r")) != -1) {
      separatorLength = 1;
    }

    if (separatorIndex == -1) {
      splitted[0] = "";
      splitted[1] = lines;
    } else {
      splitted[0] = lines.substring(0, separatorIndex + separatorLength);
      splitted[1] = lines.substring(separatorIndex + separatorLength, lines.length());
    }

    return splitted;
  }

}
