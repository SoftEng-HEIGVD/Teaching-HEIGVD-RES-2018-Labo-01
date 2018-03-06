package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.StringWriter;
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
        String str2 = str.toUpperCase();
        out.append(str2);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String str2 = str.substring(off, off + len);
        write(str2);
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        for(char c : cbuf)
            write(c);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for(int i = off; i < off + len; i++)
            write(cbuf[i]);
    }   

    @Override
    public void write(int c) throws IOException {
        out.append(Character.toUpperCase((char)c));
    }
}
