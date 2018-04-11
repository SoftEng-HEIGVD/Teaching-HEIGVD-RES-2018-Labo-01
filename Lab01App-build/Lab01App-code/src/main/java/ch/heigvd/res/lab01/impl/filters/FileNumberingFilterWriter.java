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
 * Modified by : Léo Cortès
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int lineCpt = 0;
    private boolean firstCall = true;
    private int lastWrittenChar = '\0';

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // Going through the whole substring and calling write(int) on each char
        for (int i = off; i < off + len && i < cbuf.length; ++i) {
            write(cbuf[i]);
        }
    }

    @Override
    public void write(int c) throws IOException {

        // Will store the final String to write
        StringBuilder output = new StringBuilder("");

        // First case when we must write a new line number : it is the first call
        if (firstCall) {
            output.append(++lineCpt).append("\t");
            firstCall = false;
        }

        // If the last written char is \r and ther is no \n following
        // => we can simply add the new line number, and c will be added later
        if (lastWrittenChar == '\r') {
            if (c != '\n') {
                output.append(++lineCpt).append("\t");
            }
        }

        // Adding the current char
        output.append((char) c);

        // If c == \r, then we must figure out if the next char is \n or not
        if (c == '\r') {
            lastWrittenChar = c;
            out.write(output.toString());
            return;
        }

        // Seconde case when we must write a new line number : there is a "new line" delimiter
        if (c == '\n' | c == '\r') {
            output.append(++lineCpt).append("\t");
        }

        lastWrittenChar = c;
        out.write(output.toString());
    }
}
