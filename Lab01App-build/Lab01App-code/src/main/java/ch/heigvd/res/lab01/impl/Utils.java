package ch.heigvd.res.lab01.impl;

import java.io.IOException;
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

      String ret[] = new String[2];

      String sep = Utils.getSeparator(lines, 0, lines.length());
      int idSep = lines.indexOf(sep);

      if(sep != "\0"){
          ret =  new String[] {lines.substring(0, idSep+ sep.length()),
                  lines.substring(idSep+ sep.length(), lines.length() ) };
        return ret;
      }
      else{
        return new String[]{"",lines};
      }
  }

  public static String getSeparator(String str, int off, int len) {

    if(str.contains("\r\n")){
      return "\r\n";
    }else if (str.indexOf("\n", off) != -1){
      return "\n";
    }else if (str.indexOf("\r", off) != -1){
      return "\r";
    }else{
      return "\0";
    }
  }
}
