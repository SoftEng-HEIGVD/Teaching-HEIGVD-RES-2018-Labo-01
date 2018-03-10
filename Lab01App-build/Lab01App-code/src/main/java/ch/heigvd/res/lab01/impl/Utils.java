package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Christophe Joyet
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
  //  throw new UnsupportedOperationException("The student has not implemented this method yet.");
    int size        = 2;
    String[] output = new String[size];
    int index       = lines.indexOf("\r\n");

    if (index < 0) {
      size = 1;
      index = lines.indexOf('\n');
      if (index < 0) {
        index = lines.indexOf('\r');
      }
    }
    //put the 2 element in the array
    if (index >= 0) {
      output[0] = lines.substring(0, index + size); //put the next line with separator
      output[1] = lines.substring(index + size);
    } else if(index < 0){
      output[0] = ""; //empty string if the argument doesn't contain line separator
      output[1] = lines;
    }

    return output;
  }

}
