package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
	
	public UpperCaseFilterWriter(Writer wrappedWriter) {
		super(wrappedWriter);
	}
	
	@Override
	public void write(String str, int off, int len) throws IOException {
		write(str.toCharArray(), off, len); // call write(char[] cbuf, int off, int len) to convert in uppercase and write the desired substring of str
	}
	
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		for (int i = off; i < off + len; ++i) {
			write(cbuf[i]); // call write(int c) to convert in uppercase and write each character of the desired substring.
		}
	}
	
	@Override
	public void write(int c) throws IOException {
		super.write(Character.toUpperCase(c)); // convert the character to uppercase and write it.
	}
	
}
