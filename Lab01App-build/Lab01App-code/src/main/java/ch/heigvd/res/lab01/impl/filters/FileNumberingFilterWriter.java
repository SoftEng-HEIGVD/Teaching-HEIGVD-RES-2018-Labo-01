package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int lineNumber;
    private boolean mayBeDoubleReturn;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
        lineNumber = 1;
        // flag use to detect a potential double return (\r\n)
        mayBeDoubleReturn = false;
    }

    /*
     for the following write methods, only one is implemented and make the other two call it.
     So I change the first one (the one with the string) so that it would call the char[] one.
     Then I changed the second one (cbuff) so that it would call the last one (int c).
     Lastly, I implemented the last one to behave like expected.
    */

    // call the write method of the class passing a char[] instead of a string
    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
    }

    // call the write method of the class passing a int instead of a char[].
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // for each char in the buffer, we call the write (int c) method on it.
        for (int i = 0; i < len; ++i) {
            write(cbuf[off + i]);
        }
    }

    // This method is used by the other two. It does all the filter work.
    @Override
    public void write(int c) throws IOException {
        if (lineNumber == 1) {
            out.write(lineNumber++ + "\t");
        }

        // if the former char written was \r, we look if the current one is a \n
        if (mayBeDoubleReturn) {
            if (c != '\n') {
                // if it's not the case, write the new line number, reset the flag and continue the function
                out.write(lineNumber++ + "\t");
                mayBeDoubleReturn = false;
            } else {
                // if a double return has been found, write the \n on the line and only then write the new line number.
                // then get out of the function after resetting the flag since the char has already been written.
                out.write(c);
                out.write(lineNumber++ + "\t");
                mayBeDoubleReturn = false;
                return;
            }
        }

        out.write(c);

        // if the written char is a \r, we may be in a case of double return hence, the line number isn't written until
        // the next char is read.
        if (c == '\r') {
            mayBeDoubleReturn = true;
        } else if (c == '\n') {
            out.write(lineNumber++ + "\t");
        }
    }

}