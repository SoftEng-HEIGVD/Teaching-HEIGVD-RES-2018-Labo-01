package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {

    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    @Override
    public void write(String str) throws IOException {
        out.write(str.toUpperCase());
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        str.substring(off, len);
        out.write(str);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String str = "";
        for (int i = 0; i < cbuf.length; ++i) {
            str = str + cbuf[i];
        }
        write(str, off, len);
    }

    public void write(char[] cbuf) throws IOException {
        String str = "";
        for (int i = 0; i < cbuf.length; ++i) {
            str = str + cbuf[i];
        }
        write(str);
    }

    @Override
    public void write(int c) throws IOException {
        String str = "" + (char) c;
        out.write(str);
    }

}
