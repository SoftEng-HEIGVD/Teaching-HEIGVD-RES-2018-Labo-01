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
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String[] lines;
        int n = 1;
        while(!str.isEmpty()){
            super.write(n + "\t", 0, ((Integer)n).toString().length() + 2);
            lines = Utils.getNextLine(str);
            super.write(lines[0], off, len);
            n++;
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String s = "";
        for(int i = 0; i < len ; i++){
            s += cbuf[i];
        }
        write(s, off, len);
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c);
    }

}
