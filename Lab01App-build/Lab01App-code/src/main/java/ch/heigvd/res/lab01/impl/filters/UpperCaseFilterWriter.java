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
    public void write(String str, int off, int len) throws IOException {

        //Split string and perform update
        String sub = str.substring(off, off + len);
        this.out.write(sub.toUpperCase());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        this.write(String.valueOf(cbuf), off, len);
    }

    @Override
    public void write(int c) throws IOException {

        this.out.write(Character.toUpperCase(c));
    }
}
