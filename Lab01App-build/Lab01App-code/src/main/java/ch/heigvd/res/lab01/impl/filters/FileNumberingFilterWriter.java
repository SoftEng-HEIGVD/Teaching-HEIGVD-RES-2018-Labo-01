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
	
	public FileNumberingFilterWriter(Writer out) {
		super(out);
	}
	
	@Override
	public void write(String str, int off, int len) throws IOException {
		
		if (str.length() < off + len) {
			return;
		}
		
		StringBuilder output = new StringBuilder();
		
		// if the line is the first one
		if (lineNumber == 1) {
			output.append(lineNumber++);
			output.append('\t');
		}
		
		String[] result;
		String nextLine = str.substring(off, off + len);
		
		while (!((result = Utils.getNextLine(nextLine))[0]).isEmpty()) {
			
			output.append(result[0]).append(lineNumber++).append('\t');
			nextLine = result[1];
		}
		
		output.append(result[1]);
		
		this.out.write(output.toString());
	}
	
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		write(String.valueOf(cbuf), off, len);
	}
	
	@Override
	public void write(int c) throws IOException {
		
		
	
	}
}
