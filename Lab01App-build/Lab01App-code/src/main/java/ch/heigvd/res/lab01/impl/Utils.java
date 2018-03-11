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
      String[] nextLine = {"", ""};
      boolean thereIsALine = false;
      int i = 0;
      for (; i < lines.length(); ++i) {
          if (lines.charAt(i) == '\r'){
              if(i+1 < lines.length() && lines.charAt(i+1) == '\n') {
                  ++i;
              }
              thereIsALine = true;
              break;
          }else if(lines.charAt(i) == '\n'){
              thereIsALine = true;
              break;
          }
      }
      if(thereIsALine){
          nextLine[0] = lines.substring(0,i+1);
          nextLine[1] = lines.substring(i+1);
      }else{
          nextLine[1] = lines;
      }



      System.out.println("0:");
      System.out.println(nextLine[0]);
      System.out.println("1");
      System.out.println(nextLine[1]);
      return nextLine;
  }

}
