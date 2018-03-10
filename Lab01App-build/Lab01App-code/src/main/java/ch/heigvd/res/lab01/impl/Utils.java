package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Dejvid Muaremi
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());
  private static final String MAC = "\r";
  private static final String UNIX = "\n";
  private static final String WINDOWS = "\r\n";
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
    String[] getNextLine = new String[2];
    getNextLine[0] = "";
    // Find the carriage return and split the lines in two depending on the system.
    // Has to start by the Windows carriage return or there will be errors.
    if(lines.contains(WINDOWS)){
      getNextLine = lines.split(WINDOWS,2);
      getNextLine[0] += WINDOWS;
    }
    else if(lines.contains(UNIX)){
      getNextLine = lines.split(UNIX,2);
      getNextLine[0] += UNIX;
    }
    else if(lines.contains(MAC)){
      getNextLine = lines.split(MAC,2);
      getNextLine[0] += MAC;
    }
    else {
      getNextLine[1] = lines;
    }
    return getNextLine;
  }
}
