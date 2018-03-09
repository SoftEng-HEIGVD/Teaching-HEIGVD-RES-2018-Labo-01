package ch.heigvd.res.lab01.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
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
    List<String> splittedLines = new ArrayList<>();
    String line = "";
    for(int i = 0; i < lines.length(); ++i) {
      char c = lines.charAt(i);
      line += c;
      if ('\r' == c || '\n' == c || "\r\n".equals(c)) {
        splittedLines.add(line);
        line = "";
      }
    }
    if (1 == splittedLines.size())
      splittedLines.add("");
    return splittedLines.toArray(new String[0]);
  }

}
