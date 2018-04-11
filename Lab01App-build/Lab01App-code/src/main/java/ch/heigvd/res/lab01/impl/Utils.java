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
		
		if (indexSeparator == -1) { // true if there is no WINDOWS separator sequence
			indexSeparator = lines.indexOf(OSX);
			if (indexSeparator == -1) { // true if there is no WINDOWS separator nor OSX separator sequence.
				indexSeparator = lines.indexOf(LINUX);
				if (indexSeparator == -1) { // true if there is any separator sequence.
					tabToReturn[0] = ""; // no separator found -> ""
					tabToReturn[1] = lines;  // no separator means we have only one line -> return the whole line
					return tabToReturn;
				}
			}
			// Done if there is OSX or LINUX separator only
			tabToReturn[0] = lines.substring(0, indexSeparator + 1); // split the string from first char to the OSX or LINUX separator (included)
			tabToReturn[1] = lines.substring(indexSeparator + 1, lines.length()); // contains the remaining text
		} else { // true only if there is a WINDOWS separator sequence
			tabToReturn[0] = lines.substring(0, indexSeparator + 2); // split the string from first char to the WINDOWS separator (included) -> +2 because Windows separator takes 2 char
			tabToReturn[1] = lines.substring(indexSeparator + 2, lines.length()); // contains the remaining text
		}
		return tabToReturn;
	}
	
}
