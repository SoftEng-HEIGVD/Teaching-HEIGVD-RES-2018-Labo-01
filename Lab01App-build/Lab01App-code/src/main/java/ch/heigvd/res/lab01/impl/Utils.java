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
     String line[] = {"",""};
     int index = 0;
     
     //for Windows and MacOS9
     if(lines.contains("\r"))
     {
        index += lines.indexOf("\r") + 1;
        
        //for Windows
        if(lines.contains("\r\n")) index += 1;
        
        line[0] = lines.substring(0, index);
     }
     
     //for Linux
     else if(lines.contains("\n"))
     {
        index += lines.indexOf("\n") + 1;
        line[0] = lines.substring(0, index);
     }
     
     //for the three and undefined
     line[1] = lines.substring(index);
     return line;
  }
}