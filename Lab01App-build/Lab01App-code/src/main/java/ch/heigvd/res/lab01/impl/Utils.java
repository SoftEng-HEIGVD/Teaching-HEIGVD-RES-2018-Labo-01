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
      
    /* source: https://www.regular-expressions.info/lookaround.html
    Retain the spilt regex in the resulting part and the split regex end up in the left hand side
    Using the positive lookbehind by prefixing "?<=" group on the pattern
    */
    String[] separators = {"(?<=\r\n)", "(?<=\n)", "(?<=\r)"};
    
    String[] tokens;
     
    for (String separator: separators){
        
        //Split 2 parts
        tokens = lines.split(separator,2);
        
        //according to the specification , the tokens must be 2 parts
        if(tokens.length == 2)
            return tokens;
    }
    
    tokens = new String[]{"", lines};
    return tokens;
    }

}
