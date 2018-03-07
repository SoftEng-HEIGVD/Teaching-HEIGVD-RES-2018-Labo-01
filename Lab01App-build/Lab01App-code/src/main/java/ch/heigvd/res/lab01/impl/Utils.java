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
    String arr[] = {"",lines};
    int index = lines.indexOf("\r\n");
    // Does the caract \r\n exist
    if(index == -1){
      index = lines.indexOf("\r");
      // Does the caract \r exist
      if(index == -1) {
        index = lines.indexOf("\n");
        // Does the caract \n exist
        if(index == -1){
          return arr;
        }
      }
      // split of the string to the index of the line seperator
      arr[0] = lines.substring(0,index + 1);
      arr[1] = lines.substring(index+1,lines.length());
    } else {
      // split of the string to the index of the line seperator but separator of 2 car so +2
      arr[0] = lines.substring(0,index + 2);
      arr[1] = lines.substring(index+2,lines.length());
    }

    return arr;
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
