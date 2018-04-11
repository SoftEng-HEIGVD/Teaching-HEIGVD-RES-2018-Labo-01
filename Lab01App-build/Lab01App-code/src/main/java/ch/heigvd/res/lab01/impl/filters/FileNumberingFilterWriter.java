package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

import ch.heigvd.res.lab01.impl.Utils;

/**
 * This class transforms the streams of character sent to the decorated writer. When filter encounters a line separator, it sends it to the decorated writer. It then
 * sends the line number and a tab character, before resuming the write process.
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {
	
	private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
	
	private int lineNumber = 1;
	private int previousChar = 0;
	
	public FileNumberingFilterWriter(Writer out) {
		super(out);
	}
	
	@Override
	public void write(String str, int off, int len) throws IOException {
		
		StringBuilder output = new StringBuilder();
		
		// if the line is the first one
		if (lineNumber == 1) {
			output.append(lineNumber++);
			output.append('\t');
		}
		
		String[] currentQuote;
		String restOfTheQuote = str.substring(off, off + len);
		
		// while where is lines in our quotes, we add this line to our output
		while (!((currentQuote = Utils.getNextLine(restOfTheQuote))[0]).isEmpty()) {
			output.append(currentQuote[0]).append(lineNumber++).append('\t');
			restOfTheQuote = currentQuote[1];
		}
		
		output.append(currentQuote[1]); // we add the final line of the quote
		
		this.out.write(output.toString());
	}
	
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		write(String.valueOf(cbuf), off, len);
	}
	
	@Override
	public void write(int c) throws IOException {
		
		StringBuilder output = new StringBuilder();
		
		if (lineNumber == 1) {
			output.append(lineNumber++ + "\t");
		}
		
		if (c == '\n' && previousChar == '\r') { // the line separator for windows is detected
			output.append("\r\n" + lineNumber++ + "\t");
			
		} else if (c == '\n') { // the line separator for linux is detected
			
			output.append("\n" + lineNumber++ + "\t");
		} else { // no windows or Linux separator
			
			if (previousChar == '\r') { // the previous character was the OSX line separator
				
				output.append("\r" + lineNumber++ + "\t");
			}
			if (c != '\r') { // if the current character is the OSX line separator
				output.append((char) c);
			}
		}
		
		previousChar = c;
		
		this.out.write(output.toString());
	}
}
