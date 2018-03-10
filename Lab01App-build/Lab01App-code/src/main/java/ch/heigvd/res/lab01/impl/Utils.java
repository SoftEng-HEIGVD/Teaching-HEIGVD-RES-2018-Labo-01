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
    String[] separators = {"\r\n", "\n", "\r"};
    int[] sepFound = new int[separators.length];
    
    String[] splitted;
    String[] result = {"", ""};
    
    String separator = "";
    int indexFirstSeparator = lines.length();
  
    // We look for all three separators and we keep the first found in "separator".
    for (int i = 0; i < separators.length; ++i) {
      sepFound[i] = lines.indexOf(separators[i]);
      
      if (indexFirstSeparator > sepFound[i] && sepFound[i] != -1) {
        indexFirstSeparator = sepFound[i];
        separator = separators[i];
      }
    }
    
    if (separator == "") {
      result[1] = lines;
  
      return result;
    }
    
    
    
    // If there was a separator, we split the String with it.
    splitted = lines.split(separator);
    
    // We put the first result in the first place.
    result[0] = splitted[0] + separator;
    
    // And for all other splitted Strings, we concatenate them into the second result.
    for(int i = 1; i < splitted.length; ++i) {
      result[1] += splitted[i] + separator;
    }
    
    return result;
  }
}
