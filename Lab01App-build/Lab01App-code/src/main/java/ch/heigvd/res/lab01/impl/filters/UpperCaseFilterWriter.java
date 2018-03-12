package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti Modified by : Léo Cortès
 */
public class UpperCaseFilterWriter extends FilterWriter {

    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        // Transfroming the substring in uppercase
        String majStr = str.substring(off, off + len).toUpperCase();
        out.write(majStr);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // Transforming a sub-tab string uppercase
        char[] majTab = new char[len];
        for (int i = off; i < off + len; ++i) {
            majTab[i - off] += Character.toUpperCase(cbuf[i]);
        }
        out.write(majTab);
    }

    @Override
    public void write(int c) throws IOException {
        // Transforming a single char uppercase
        String majC = "" + Character.toUpperCase((char) c);
        out.write(majC);
    }

}
