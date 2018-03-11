package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

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
	private static String LINUX = "\n";
	private static String WINDOWS = "\r\n";
	private static String OSX = "\r";
	
	private boolean isFirstChar = true;
	private int lineNumber = 1;
	private boolean previousCharWasOSX = false;
	
	public FileNumberingFilterWriter(Writer out) {
		super(out);
	}
	
	@Override
	public void write(String str, int off, int len) throws IOException {
		write(str.toCharArray(), off, len);
	}
	
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		for (int i = off; i < len + off && i < cbuf.length; i++) {
			write(cbuf[i]);
		}
	}
	
	@Override
	public void write(int c) throws IOException {
		if (isFirstChar) { // It's the first caracter we write -> first write the lineNumber with the '\t', then write the caracter.
			isFirstChar = false;
			this.out.write("" + lineNumber++ + "\t");
		}
		
		if (previousCharWasOSX && c == LINUX.charAt(0)) { // We detect the WINDOWS separator sequence -> we are on Windows -> we write the WINDOWS separator then the lineNumber with the '\t'.
			this.out.write(WINDOWS + lineNumber++ + "\t");
			previousCharWasOSX = false;
		} else if (c == OSX.charAt(0)) { // we detect OSX separator -> we have to wait for the next one to see if we are on Windows or MacOSX.
			previousCharWasOSX = true;
		} else if (c == LINUX.charAt(0)) { // we detect LINUX separator -> we are on Linux -> we write LINUX separator then the lineNumber with the '\t'.
			this.out.write(LINUX+ lineNumber++ + "\t");
		} else { // c is not OSX neither LINUX separator -> no need to write lineNumber except if previous char war OSX separator.
			if (previousCharWasOSX) { // previous char was OSX separator and actual one is not LINUX separator -> It's not the WINDOWS separator sequence -> we are on MacOSX.
				previousCharWasOSX = false;
				this.out.write(OSX + lineNumber++ + "\t"); // we write the previous OSX separator first. Then we write the lineNumber with the '\t'.
			}
			this.out.write(c);
		}
	}
}
