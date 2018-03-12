package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Patrick Neto
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());
  private static final String[] END_LINE = {"\r\n", "\r", "\n"};

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
      String[] nextLines = new String[2];

      //default output
      nextLines[0] = "";
      nextLines[1] = lines;

      //For every match of endline characters, split the text
      for(String returnCarriage : END_LINE){
          int endlineIndex = lines.indexOf(returnCarriage);
          if(endlineIndex > 0){
              nextLines[0] = lines.substring(0, endlineIndex + returnCarriage.length());
              nextLines[1] = lines.substring(endlineIndex + returnCarriage.length());
              return nextLines;
          }
      }

      return nextLines;
  }

}
