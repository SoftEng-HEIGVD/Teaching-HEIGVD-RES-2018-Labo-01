package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

import javax.rmi.CORBA.Util;
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

    private int  totalLines;    // total number of lines added to the writer
    private char previousChar;  // keep the previous written char

    public FileNumberingFilterWriter(Writer out) {
        super(out);
        totalLines   = 0;
        previousChar = 0;
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        if(off + len > cbuf.length)
            throw new IllegalArgumentException("invalid size of substring");

        for(int i = off; i < off + len; i++) {
            write(cbuf[i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        // beginning of file
        if(totalLines == 0) {
            writeNewLine();
        }

        // if '\r'
        else if((char)c != '\n' && previousChar == '\r') {
            writeNewLine();
        }

        out.write((char)c);

        // if '\n' or '\r\n'
        if((char)c == '\n') {
            writeNewLine();
        }

        previousChar = (char)c;
    }

    /**
     * this private method write a new line in this specific way:
     *
     * "totalLines"\t
     *
     * @throws IOException
     */
    private void writeNewLine() throws IOException {
        StringBuilder newLine = new StringBuilder();
        out.write(newLine.append(++totalLines).append('\t').toString());
    }

}
