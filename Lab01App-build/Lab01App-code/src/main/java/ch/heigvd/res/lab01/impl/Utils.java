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
    // Ref for .split() https://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
    String[] ret = new String[2];


     if(lines.contains("\r\n")){
       ret = lines.split("\r\n", 2);
       ret[0] += "\r\n";
     }  else if(lines.contains("\n")){
       ret = lines.split("\n", 2);
       ret[0] += "\n";
     } else if(lines.contains("\r")){
       ret = lines.split("\r", 2);
       ret[0] += "\r";
     } else {
       ret[0] = "";
       ret[1] = lines;
     }

     return ret;
  }

}
