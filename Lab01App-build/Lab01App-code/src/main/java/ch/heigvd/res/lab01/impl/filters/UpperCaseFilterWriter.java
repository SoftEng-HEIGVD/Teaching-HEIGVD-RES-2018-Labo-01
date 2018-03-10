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
        String newString = "";
        for (int i = off; i < len+off; i++) {
            newString += str.charAt(i);
        }
        out.write(newString.toUpperCase());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String string = "";
        for (int i = off; i < len+off; i++) {
            string += cbuf[i];
        }
        out.write(string.toUpperCase());
    }

    @Override
    public void write(int c) throws IOException {
        String charS = "" + (char)c;
        out.write(charS.toUpperCase());
    }

}
