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
    /*line separators*/
    /* The \r\n most be the first one*/
    String[] delimiters = {"\r\n", "\r", "\n"};
      /*Result expected*/
    String[] nextLine = new String[2];
      /*When there is at least one separator*/
    for (String delim : delimiters) {
        int index = lines.indexOf(delim);
        if (index != -1) {
            nextLine[0] = lines.substring(0, index + delim.length());
            nextLine[1] = lines.substring(index + delim.length());
            return nextLine;
         }
      }
      /*When there is no separator*/
      nextLine[0] = "";
      nextLine[1] = lines;
      return nextLine;   
  }

}
