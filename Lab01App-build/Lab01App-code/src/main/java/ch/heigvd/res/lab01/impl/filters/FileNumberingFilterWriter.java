package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;
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
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    private int numLine = 0;

    private int previous = ' ';

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    /**
     * Write the string
     *
     * @param str String to be written
     * @param off Offset from which to start writing characters
     * @param len Number of characters to write
     * @throws IOException
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
    }

    /**
     * Write the char buffer
     *
     * @param cbuf The char buffer
     * @param off Offset from which to start writing characters
     * @param len Number of characters to write
     * @throws IOException
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < cbuf.length && i < len + off; ++i) {
            write(cbuf[i]);
        }

    }

    /**
     * write the char c
     *
     * @param c Integer define a char to write
     * @throws IOException
     */
    @Override
    public void write(int c) throws IOException {
        String toWrite = "";

        //If it's the first char we write then we write the line and the tab
        if (numLine == 0) {
            toWrite = Integer.toString(++numLine) + "\t";
            super.write(toWrite, 0, toWrite.length());
        }

        //if the char is a \r only we write the line + the tab
        if (previous == '\r' && c != '\n') {
            toWrite = Integer.toString(++numLine) + "\t";
            super.write(toWrite, 0, toWrite.length());
        }
        
        //write the current char
        super.write(c);

        //if the char is \n or \r\n then we write the line + the tab
        if (c == '\n') {
            toWrite = Integer.toString(++numLine) + "\t";
            super.write(toWrite, 0, toWrite.length());
        }

        previous = c;

    }

}
