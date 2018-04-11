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
 * @author Yosra Harbaoui
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int lineNumber = 1;
    private boolean isR = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        this.write(str.toCharArray(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int ic = off; ic < off + len; ic++) {
            this.write((int) cbuf[ic]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        if (lineNumber == 1) {
            out.write(lineNumber++ + "\t");
        }

        if (!(c == '\n') && isR) {
            out.write(lineNumber++ + "\t");
            isR = false;
        }
        out.write((char) c);
        if (c == '\n') {
            out.write(lineNumber++ + "\t");
            isR = false;
        } else if (c == '\r') {
            isR = true;
        }
    }

}
