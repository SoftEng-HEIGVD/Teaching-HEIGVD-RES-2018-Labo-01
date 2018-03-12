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

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    int lineNb = 1;
    int prevChar = ' ';

    /*
     *@brief : Write a string plus line numbers in a writer (use the char to char method)
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
    }

    /*
     *@brief : Write a char array plus line numbers in a writer (use the char to char method)
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        for (int i = off; i < len + off; i++) {
            write(cbuf[i]);
        }

    }

    /*
     *@brief : Write a character with a given int in a writer and add the line number
     */
    @Override
    public void write(int c) throws IOException {
        //write line number if first line or if Mac OS newline detected
        if (lineNb == 1 || (prevChar == '\r' && c!= '\n')) {
            out.write(lineNb++ + "\t");
        }

        //Look for newlines in every OS
        if (c == '\n') {
            out.write("\n" + lineNb++ + "\t");
        } else if (c == '\r') {
            out.write("\r");
        } else {
            out.write(c);
        }
        prevChar = c;
    }

}
