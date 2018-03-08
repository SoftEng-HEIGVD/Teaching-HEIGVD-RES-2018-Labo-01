package ch.heigvd.res.lab01.impl;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author Olivier Liechti
 * @version 2.0
 * modified by Antonio Cusanelli
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
		String[] out = new String[2];
		boolean lineSeparator = false;
		String tmp = "";

		//put characters in tmp till detection of an end of line char and put the remaining of lines in out[1]
		for (int i = 0; i < lines.length(); i++) {
			tmp += lines.charAt(i);

			//condition : \n or \r or \r\n
			if (lines.charAt(i) == '\n' || (lines.charAt(i) == '\r' && i < lines.length() - 1
					&& lines.charAt(i + 1) != '\n') || lines.charAt(i) == '\r' && i == lines.length() - 1) {
				out[0] = tmp;
				out[1] = lines.substring(i + 1, lines.length());
				lineSeparator = true;
				break;
			}
		}

		//in case of one line only
		if (!lineSeparator) {
			out[0] = "";
			out[1] = lines;
		}
		return out;
	}

}
