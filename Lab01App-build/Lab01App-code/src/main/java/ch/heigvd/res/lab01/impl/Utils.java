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
    String[] result = {"", lines};
    // If there is no line separator
    if (!lines.contains("\r") && !lines.contains("\n") && !lines.contains("\r\n")) {
        return result;
    }

    boolean maybeAnewLine = false; // If we detect a '\r' => maybeANewLine = true

    for (int i = 0; i < lines.length(); ++i) { // for each characters of lines

        // New lines on MACOS9 (1)
        if (lines.charAt(i) == '\r') {
            // if we are at the end of the buffer
            if (i == lines.length() - 1){
                result[0] = lines.substring(0, i + 1);
                result[1] = "";
            }
            // we are not at the end of the buffer
            else {
                maybeAnewLine = true;
            }
        }
        // New lines on Windows and Unix
        else if (lines.charAt(i) == '\n') {
            result[0] = lines.substring(0, i + 1);
            result[1] = lines.substring(i + 1);
            break;
        }
        // New lines on MACOS9 (2)
        else if (maybeAnewLine) {
            result[0] = lines.substring(0, i);
            result[1] = lines.substring(i);
            break;
        }

    }
    return result;
  }

}
