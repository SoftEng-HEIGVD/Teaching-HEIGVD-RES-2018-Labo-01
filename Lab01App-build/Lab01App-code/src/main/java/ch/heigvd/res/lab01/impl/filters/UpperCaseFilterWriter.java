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

    /*
    of the following methods, only the last one is really implemented; the other two are just calling it to do the filter
     */

    // change the argument from string to char[] and call the write method taking a char[] in argument of this class
    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
    }

    // change the argument from char[] to int and call the write (int c) method of this class
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = 0; i < len; ++i) {
            write(cbuf[off + i]);
        }
    }

    // method used by the other two. Put the int received as argument to uppercase and call FilterWriter.write(int c) on it.
    @Override
    public void write(int c) throws IOException {
        super.write(Character.toUpperCase(c));
    }

}
