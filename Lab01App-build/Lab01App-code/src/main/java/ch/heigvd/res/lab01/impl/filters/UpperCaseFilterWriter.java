package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Olivier Liechti
 * @version 2.0
 * modified by Antonio Cusanelli
 */
public class UpperCaseFilterWriter extends FilterWriter {

	public UpperCaseFilterWriter(Writer wrappedWriter) {
		super(wrappedWriter);
	}

	@Override
	public void write(String str, int off, int len) throws IOException {
		//call the funct with char Array, we don't do the "same" code twice
		write(str.toCharArray(), off, len);
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		for (int i = off; i < off + len; i++) {
			cbuf[i] = Character.toUpperCase(cbuf[i]);
		}
		out.write(cbuf, off, len);
	}

	@Override
	public void write(int c) throws IOException {
		out.write(Character.toUpperCase(c));
	}

}
