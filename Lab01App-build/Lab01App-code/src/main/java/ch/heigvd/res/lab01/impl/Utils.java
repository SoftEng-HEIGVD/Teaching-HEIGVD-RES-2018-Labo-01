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
    
    String[] str = new String[2];
    int index = 0;
    
    /* after the for loop, index will either be at the line separator or at the
     * end of the String lines */
    for (; index < lines.length(); index++){
      
      char c = lines.charAt(index);
      
      if(c == '\n'){
        break;
      }
      else if(c == '\r'){
        if(lines.length() > index + 1 && lines.charAt(index+1) == '\n'){
          index++;  
        }
        break;
      }
    }
    
    // if there is no line separator
    if(index == lines.length()){
      str[0] = "";
      str[1] = lines;
    } else {
      str[0] = lines.substring(0, index + 1);
      str[1] = lines.substring(index + 1);
    }

    return str;
  }
}

