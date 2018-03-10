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
        //the parent method is called with  an upper cased string instead of the original string
        super.write(str.toUpperCase(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String s = "";
        for (char c : cbuf) {
            s += c;
        }
        super.write(s.toUpperCase(), off, len);
    }

    @Override
    public void write(int c) throws IOException {
        //The character is transformed in an uppercase
        super.write(Character.toUpperCase((char) c));
    }

}
