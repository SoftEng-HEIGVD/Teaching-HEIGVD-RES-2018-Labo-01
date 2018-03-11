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
    // Tab containing all separators
    String[] separators = {"\r\n", "\n", "\r"};
    int[] sepFound = new int[separators.length];
    
    String[] result = {"", ""};
    
    String separator = "";
    int indexFirstSeparator = lines.length();
  
    // We look for all three separators and we keep the first found in "separator".
    // We also keep the index where we found it.
    for (int i = 0; i < separators.length; ++i) {
      sepFound[i] = lines.indexOf(separators[i]);
      
      // If we find one and its index is smaller than the previous one, we keep it.
      if (sepFound[i] != -1 && indexFirstSeparator > sepFound[i]) {
        indexFirstSeparator = sepFound[i];
        separator = separators[i];
      }
    }
    
    // If we didn't find any separator, meaning "separator" is empty, we set "lines" in the second part of the result.
    // Otherwise, we construct the result array using the index of the separator found.
    if (separator.isEmpty()) {
      result[1] = lines;
    } else {
      // First part containing the first line and the separator
      result[0] = lines.substring(0, indexFirstSeparator + separator.length());
      
      // Second part containing the remaining text.
      result[1] = lines.substring(indexFirstSeparator + separator.length());
    }
    
    return result;
  }
}
