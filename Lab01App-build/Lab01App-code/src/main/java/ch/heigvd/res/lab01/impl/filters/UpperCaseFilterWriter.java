package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

import static java.lang.Character.toUpperCase;

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
        String upper = str.toUpperCase();
        super.write(upper, off, len);
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        int endOfCut = off+len;
        for (int i = off; i < endOfCut; i++) {
            cbuf[i] = toUpperCase(cbuf[i]);
        }
        super.write(cbuf, off, len);
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

    @Override
    public void write(int c) throws IOException {
        int upperC = toUpperCase(c);
        super.write(upperC);
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

}
