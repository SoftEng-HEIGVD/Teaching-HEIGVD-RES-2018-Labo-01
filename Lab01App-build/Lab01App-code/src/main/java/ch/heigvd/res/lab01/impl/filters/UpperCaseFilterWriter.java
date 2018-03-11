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

        int lastIndex = (off + len) < str.length()? (off + len) : str.length();
        str = str.substring(off, lastIndex);

        for(int i = 0; i < str.length(); ++i){
            write(str.charAt(i));
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        int lastIndex = (off + len) < cbuf.length? (off + len) : cbuf.length;
        for(int i = 0; i < lastIndex - off; ++i){
            write(cbuf[i + off]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        //The character is transformed in an uppercase
        super.write(Character.toUpperCase((char) c));
    }

}
