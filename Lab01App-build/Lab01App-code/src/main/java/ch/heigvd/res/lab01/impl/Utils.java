package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @contributor Nair Alic
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
     String [] result = new String[2];
     result[0] = result[1] = ""; // initialization in case if there is no new line 
                                // character at the end we will send an empty string
     
     int index = lines.indexOf("\n"); // check for new line character \n (WIN and Linux tests)
     
     if(index == -1) { //if there is no \n, we check for \r (MAC test)
        index = lines.indexOf("\r");
     }
     
     if(index == -1) { // in this case there is no new line character
        result[1] = lines;
        return result;
     } else { // here we create the result
        result[0] = lines.substring(0, index + 1); // the next line with the line separator
        result[1] = lines.substring(index + 1); // remaining text
     }
     
     return result;
  }

}
