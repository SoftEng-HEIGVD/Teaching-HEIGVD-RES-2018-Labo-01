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

    /*
     *@brief : Write a string in all caps in a writer
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        out.write(str.substring(off, len+off).toUpperCase());
    }

    /*
     *@brief : Write a char array in all caps in a writer
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String string = "";
        for (int i = off; i < len+off; i++) {
            string += cbuf[i];
        }
        out.write(string.toUpperCase());
    }

    /*
     *@brief : Write an int as a char in all caps in a writer
     */
    @Override
    public void write(int c) throws IOException {
        String charS = "" + (char)c;
        out.write(charS.toUpperCase());
    }

}
