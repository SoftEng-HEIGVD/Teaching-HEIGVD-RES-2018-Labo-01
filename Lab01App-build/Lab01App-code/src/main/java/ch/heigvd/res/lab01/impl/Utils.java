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
    String[] separators= {"\r\n","\r","\n"}; //creation of separators
    String[] emptyElement= new String[] {"",lines};
    
    //browse the line to search separators
    for(String separator:separators){
        if(lines.indexOf(separator) != -1){
            String[] element=new String[] {lines.substring(0,lines.indexOf(separator) + separator.length()),
                lines.substring(lines.indexOf(separator) + separator.length())};
            return element;
        }
    }
       return emptyElement;  //when no separators have been found   
  }

}
