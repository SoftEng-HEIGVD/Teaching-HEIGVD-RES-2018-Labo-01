package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

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
		str = str.substring(off, off + len);
		write(str);
	}
	
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		if (off + len <= cbuf.length) {
			String str = new String(cbuf);
			str = str.substring(off, off + len);
			write(str);
			
		}
	}
	
	@Override
	public void write(int c) throws IOException {
		if (c == '\r') {
			previousCharWasOSX = true;
		} else if (previousCharWasOSX && c == '\n') {
			this.out.write(WINDOWS + lineNumber++ + '\t');
			previousCharWasOSX = false;
		} else {
			write("" + (char) c);
		}
	}
	
	@Override
	public void write(String str) throws IOException {
		String[] tab = Utils.getNextLine(str);
		
		if (isFirstChar) {
			isFirstChar = false;
			this.out.write(lineNumber++ + "\t");
		}
		
		if (tab[0].length() > 0) {
			String separator = tab[0].substring(tab[0].length() - 1);
			if (tab[0].length() > 2 && tab[0].substring(tab[0].length() - 2).equals("\r")) {
				this.out.write(lineNumber++ + "\t" + tab[0]);
			} else if (separator.equals(OSX)) {
				this.out.write(tab[0] + lineNumber++ + "\t");
			} else if (separator.equals(LINUX)) {
				this.out.write(tab[0] + lineNumber++ + "\t");
			}
		}
		if (tab[1].length() > 0) {
			if (tab[0].length() == 0) {
				this.out.write(tab[1]);
			} else {
				write(tab[1]);
			}
		}
	}
}
