package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

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
        String sub = str.substring(off, off + len).toUpperCase();
        String beginning = str.substring(0,off);
        String end = str.substring(off + len, str.length());


        super.write(beginning + sub + end, off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String str = String.valueOf(cbuf);

        String sub = str.substring(off, off + len).toUpperCase();
        String beginning = str.substring(0,off);
        String end = str.substring(off + len, str.length());

        super.write((beginning + sub + end).toCharArray(), off, len);
    }

    @Override
    public void write(int c) throws IOException {

        // this is ugly ... but it works ! and since i've been coding for the past
        // 6 hours on this, ugly that works is fine !
        super.write(String.valueOf((char)c).toUpperCase().charAt(0));
    }

}
