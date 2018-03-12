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

        // if substring is part of entire string
        if ((off + len) <= str.length()) {
            for (int i = off; i < off + len; ++i) {
                // writing wanted substring in Writer
                super.write(Character.toUpperCase(str.charAt(i)));
            }
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // if substring is part of entire string
        if ((off + len) <= cbuf.length) {
            // writing wanted substring in Writer
            for (int i = off; i < off + len; ++i) {
                super.write(Character.toUpperCase(cbuf[i]));
            }
        }
    }

    @Override
    public void write(int c) throws IOException {
        super.write(Character.toUpperCase(c));
    }

}
