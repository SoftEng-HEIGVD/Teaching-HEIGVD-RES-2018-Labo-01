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
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 * @author Christophe Joyet

 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        // throw new UnsupportedOperationException("The student has not implemented this method yet.");
        write(str.toCharArray(), off, len); // Transform String into Array of char
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");

        /* write char in cbuf */
        for (int i = 0; i < len; ++i) {
            write(cbuf[off + i]);
        }
    }

    //declaration of variables
    private int nLine = 0;
    private boolean back = false;

    @Override
    public void write(int c) throws IOException {
        // throw new UnsupportedOperationException("The student has not implemented this method yet.");
        if (nLine == 0) {
            out.write(++nLine + "\t");
        }

        if (c != '\n') {
            if (back) {
                nLine++;
                out.write(nLine + "\t");
            }
        }
        back = (c == '\r');

        out.write(c);

        if (c == '\n') {
            out.write(++nLine + "\t");
        }

    }
}
