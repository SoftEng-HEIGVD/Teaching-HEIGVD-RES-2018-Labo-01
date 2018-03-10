package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 * @author Olivier Liechti
 */
public class Utils {
	
	private static final Logger LOG = Logger.getLogger(Utils.class.getName());
	private static String LINUX = "\n";
	private static String WINDOWS = "\r\n";
	private static String OSX = "\r";
	
	/**
	 * This method looks for the next new line separators (\r, \n, \r\n) to extract the next line in the string passed in arguments.
	 *
	 * @param lines
	 * 		a string that may contain 0, 1 or more lines
	 *
	 * @return an array with 2 elements; the first element is the next line with the line separator, the second element is the remaining text. If the argument does not
	 * contain any line separator, then the first element is an empty string.
	 */
	public static String[] getNextLine(String lines) {
		String tabToReturn[] = new String[2];
		int indexSeparator = lines.indexOf(WINDOWS);
		
		if (indexSeparator == -1) { // true if the caracter '\r\n' doesn't exist
			indexSeparator = lines.indexOf(OSX);
			// Does the caract \r exist
			if (indexSeparator == -1) { // true if the caracters '\r\n' and '\r' don't exist
				indexSeparator = lines.indexOf(LINUX);
				// Does the caract \n exist
				if (indexSeparator == -1) { // true if the caracters '\r\n', '\r' and '\n' doesn't exist
					tabToReturn[0] = ""; // no separator found -> ""
					tabToReturn[1] = lines;  // no separator means we have only one line -> return the whole line
					return tabToReturn;
				}
			}
			tabToReturn[0] = lines.substring(0, indexSeparator + 1); // split the string from first char to the separator (included)
			tabToReturn[1] = lines.substring(indexSeparator + 1, lines.length()); // contains the remaining text
		} else {
			tabToReturn[0] = lines.substring(0, indexSeparator + 2); // split the string from first char to the separator (included) -> +2 because Windows separator takes 2 char
			tabToReturn[1] = lines.substring(indexSeparator + 2, lines.length());// contains the remaining text
		}
		return tabToReturn;
	}
	
}
