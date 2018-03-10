package ch.heigvd.res.lab01.impl;

import java.io.BufferedReader;
import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Labinot Rashiti
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
     String[] output = {"",""}; // Important to declare like this because of null references
     

     // We need to check every separators in each case
     if (lines.contains("\n")) {          // First separator
        output = lines.split("\n", 2);    // Split by 2
        output[0] = output[0] + "\n";
        
     } else if (lines.contains("\r")) {   // Second separator
        output = lines.split("\r", 2);    // Split by 2
        output[0] = output[0] + "\r";
     
     } else if (lines.contains("\r\n")) { // Third Separator
        output = lines.split("\r\n", 2);  // Split by 2
        output[0] = output[0] + "\r\n";
     
     } else
         output[1] = lines;               // add remaing text
     
     return output;
  }

}
