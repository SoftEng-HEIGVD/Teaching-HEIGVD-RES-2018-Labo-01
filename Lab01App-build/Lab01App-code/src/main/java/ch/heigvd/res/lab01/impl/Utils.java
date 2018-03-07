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
    if(lines == null || lines.isEmpty() ){
      return null;
    }

    String[] ts = new String[2];

    char [] separator =  {'\r','\n'};

    for(int i = 0 ; i < lines.length();i++){
      //\r
      if(lines.charAt(i) == separator[0]){
        //\r\n
        if(i != (lines.length() -1) && lines.charAt(i+1) == separator[1]){
          ts[0] = lines.substring(0,i+2);
          ts[1] = lines.substring(i+2,lines.length());
          return ts;
        }else {
          ts[0] = lines.substring(0, i + 1);
          ts[1] = lines.substring(i + 1, lines.length());
          return ts;
        }

      //\n
      }else if(lines.charAt(i) == separator[1]){
        ts[0] = lines.substring(0 , i + 1);
        ts[1] = lines.substring(i + 1, lines.length());
        return ts;
      }

    }

    ts[0] = "";
    ts[1] = lines;

    return ts;
  }

}
