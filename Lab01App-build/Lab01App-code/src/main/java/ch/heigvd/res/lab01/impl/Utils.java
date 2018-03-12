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
    final char CARRIAGE_RETURN = '\r',
               LINE_FEED       = '\n';

    String[] nextLine = {"", lines};

    for(int i = 0; i < lines.length(); i++){
      if(lines.charAt(i) == LINE_FEED)
        return separateLines(lines, i);

      if(lines.charAt(i) == CARRIAGE_RETURN){
        if(i < lines.length() - 1 && lines.charAt(i + 1) == LINE_FEED)
          return separateLines(lines, i + 1);
        else
          return separateLines(lines, i);
      }
    }
    
    return nextLine;
  }

  private static String[] separateLines(String lines, int index){
    String[] nextLine = new String[2];

    nextLine[0] = lines.substring(0, index + 1);
    nextLine[1] = lines.substring(index + 1);

    return nextLine;
  }
}