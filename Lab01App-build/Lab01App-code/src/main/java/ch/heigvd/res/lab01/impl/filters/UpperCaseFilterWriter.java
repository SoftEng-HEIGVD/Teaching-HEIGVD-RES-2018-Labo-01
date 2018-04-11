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

        //Get the substring asked from the text
        String newStr = str.substring(off, off + len);

        //Transform the substring into upper cases
        newStr = newStr.toUpperCase();

        //Write the formatted text
        super.out.write(newStr);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        //Transform the characters array into upper case
        write(String.valueOf(cbuf), off, len);
    }

    @Override
    public void write(int c) throws IOException {

        //Write the formatted character
        super.out.write(Character.toUpperCase(c));
    }

}