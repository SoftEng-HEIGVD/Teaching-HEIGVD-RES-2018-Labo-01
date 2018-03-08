package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 * @version 2.0
 * modified by Antonio Cusanelli
 */
public class FileNumberingFilterWriter extends FilterWriter {

	private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
	private int line = 1;
	private boolean rWasDetected = false; // used for the func write(int)

	public FileNumberingFilterWriter(Writer out) {
		super(out);
	}

	@Override
	public void write(String str, int off, int len) throws IOException {
		write(str.toCharArray(), off, len);
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		/*
			if first line we add the first line's number and tab and then visit char by
			char to find the specific end of line character in different OS, considering
			off and len
		 */

		if (line == 1)
			out.write(line++ + "\t");

		for (int i = off; i < off + len; i++) {
			out.write(cbuf[i]);

			//condition : only \n or \r or the two together \n\r
			if (cbuf[i] == '\n' || (cbuf[i] == '\r' && i < cbuf.length - 1 && cbuf[i + 1] != '\n'))
				out.write(line++ + "\t");
		}
	}


	@Override
	public void write(int c) throws IOException {
		//end of line : \n or \r or \r\n

		//in case of first line -> number + tab
		if (line == 1)
			out.write(line++ + "\t");


		if (!rWasDetected)
			out.write(c);
		else if (c != '\n') { //handling of the char next to '\r'
			out.write(line++ + "\t");
			out.write(c);
			rWasDetected = false;
		}

		if (c == '\n') {
			if (rWasDetected) {
				rWasDetected = false;
				out.write(c);
			}
			out.write(line++ + "\t");
		}

		if (c == '\r') {
			rWasDetected = true;
		}

	}
}
