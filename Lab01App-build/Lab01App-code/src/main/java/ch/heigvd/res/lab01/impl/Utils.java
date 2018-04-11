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

    //The resulting array
    String[] res;

    //We split with a regex at the first occurence of a line separator
    res = lines.split("(?<=\\R)", 2);

    //If res contains only one element (no line separator),
    //then the first element is an empty string
    //and the second is the remaining text
    if(res.length == 1){
        String[] resEmptyLine = {"", res[0]};
        return resEmptyLine;
    }

    return res;
  }

}
