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
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {
    final static char TABULATION = '\t';

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        int nbLine = 1;
        String[] tabStr = new String[2];

        tabStr
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String str = cbuf.toString();
        write(str, off, len);
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c);
        if(c == '\n'){
            super.write('1');
            super.write(TABULATION);
        }
    }

}
