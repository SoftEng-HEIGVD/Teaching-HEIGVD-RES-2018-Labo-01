package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Olivier Nicole (Student)
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

      /*
       * Updated by Olivier Nicole
       */

      String[] output = {"",lines};                 //the value returned
      int separatorIndex;                           //keep the index of the first separator
      String separators[] = {"\n", "\r", "\r\n"};   //set all separators

      for (String separator : separators) {
          separatorIndex = lines.indexOf(separator);

          //if the index of the separator is greather than 0, we can return a substring of lines
          if(separatorIndex > 0) {
              output[0] = lines.substring(0, separatorIndex + separator.length());
              output[1] = lines.substring(separatorIndex + separator.length(), lines.length());  //remaining text
              return output;
          }
      }

      //return an empty string if there is no separators
      return output;
  }
}
