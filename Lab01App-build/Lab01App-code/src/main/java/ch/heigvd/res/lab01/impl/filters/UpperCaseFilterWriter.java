package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.Character;

/**
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {

    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    /**
     * Override the write from Writer with String, Offset and Length.
     * This Override add a features : convert the text to Uppercase
     *
     * @author Yann Lederrey
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        super.write(str.toUpperCase(), off, len);
    }

    /**
     * Override the write from Writer with Array, Offset and Length.
     * This Override add a features : convert the text to Uppercase
     *
     * @author Yann Lederrey
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        //change every Character in the array to Upper Case.
        for (int i = off; i < len + off; i++) {
            cbuf[i] = Character.toUpperCase(cbuf[i]);
        }
        super.write(cbuf, off, len);
    }

    /**
     * Override the write from Writer with Character.
     * This Override add a features : convert the text to Uppercase
     *
     * @author Yann Lederrey
     */
    @Override
    public void write(int c) throws IOException {
        char carac = (char) c;
        carac = Character.toUpperCase(carac);
        super.write(carac);
    }

}
