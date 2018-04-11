package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * It transform a text in uppercase
 *
 * @author Olivier Liechti
 * @author Adam Zouari
 */
public class UpperCaseFilterWriter extends FilterWriter {

    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    @Override
    public void write(String str) throws IOException {
        String str2 = str.toUpperCase();
        out.write(str2);
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
        out.write(Character.toUpperCase((char)c));
    }
}
