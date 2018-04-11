package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    String[] extractedTokens = {"", ""};

      Pattern pattern = Pattern.compile("^(.*[\r\n]+)((?s).*)$");
      Matcher matcher = pattern.matcher(lines);
      if (matcher.find()) {
          extractedTokens[0] = matcher.group(1);
          extractedTokens[1] = matcher.group(2);
      } else {
          extractedTokens[1] = lines;
      }

      return extractedTokens;
  }


}
