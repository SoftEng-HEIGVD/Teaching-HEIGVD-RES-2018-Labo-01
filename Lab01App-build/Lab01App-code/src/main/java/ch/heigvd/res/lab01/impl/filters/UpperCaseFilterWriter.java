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

    public String charTabToString(char[] cbuf) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < cbuf.length; ++i) {
            str.append(cbuf[i]);
        }
        return str.toString();
    }

    @Override
    public void write(String str) throws IOException {
        out.write(str.toUpperCase());
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String interString = str.substring(off, off + len);
        write(interString);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        write(charTabToString(cbuf), off, len);
    }

    public void write(char[] cbuf) throws IOException {
        write(charTabToString(cbuf));
    }

    @Override
    public void write(int c) throws IOException {
        String str = "" + (char) c;
        write(str);
    }

}
