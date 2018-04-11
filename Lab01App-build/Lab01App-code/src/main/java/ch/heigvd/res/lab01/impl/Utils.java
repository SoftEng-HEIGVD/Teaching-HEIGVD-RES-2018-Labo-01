package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Cedric Lankeu
 */
public class Utils 
{

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
  public static String[] getNextLine(String lines) 
  {
        //array of returned result
        String[] nextLineToCome = new String[2];
        
        //next new line separators to extract
	String[] separators = {"\n", "\r", "\r\n"};

	for (int i = 0; i < separators.length; ++i) 
	{
	    String line_separator = separators[i];
            
            // searching for index of the separator in  lines
	    int index = lines.indexOf(line_separator);  
	    if (index != -1) 
	    {
		nextLineToCome[0] = lines.substring(0, index + line_separator.length()); 
		nextLineToCome[1] = lines.substring(index + line_separator.length());
		return nextLineToCome;
	    }
	}
	// separator not founded 
	nextLineToCome[0] = "";
	nextLineToCome[1] = lines;
	return nextLineToCome;
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
