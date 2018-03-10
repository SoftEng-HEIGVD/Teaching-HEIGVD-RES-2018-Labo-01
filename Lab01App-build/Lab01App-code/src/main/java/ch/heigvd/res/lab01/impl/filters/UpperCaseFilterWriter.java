package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.BufferOverflowException;

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
        // we first check if the offset and the length doesn't exceed the length of the string
        if(off + len > str.length()) throw new BufferOverflowException();
        // we process the wanted portion of string char by char
        for(int i = off ; i < off+len; ++i){
            write(str.charAt(i));
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // we first check if the offset and the length doesn't exceed the length of the string
        if(off + len > cbuf.length) throw new BufferOverflowException();
        // we process the wanted portion of string char by char
        for(int i = off ; i < off + len ; ++i){
            write(cbuf[i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        super.write(Character.toUpperCase(c));
    }

}
