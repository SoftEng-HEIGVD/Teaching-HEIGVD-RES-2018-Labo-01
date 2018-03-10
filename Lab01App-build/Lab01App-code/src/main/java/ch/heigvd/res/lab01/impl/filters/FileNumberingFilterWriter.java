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
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int counter;
    private boolean first = false;
    private boolean isInt=false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
        counter = 1;
        first = true;
        
    }

    @Override
    public void write(String str) throws IOException {
        if(str.contains("\r\n")&&(!isInt))
            isInt=true;
        for (int i = 0; i < str.length(); ++i) {

            if (first) {
                out.write(counter++ + "\t" + str.charAt(i));
                first = false;
            } else if (str.charAt(i) == '\n') {
                out.write("\n" + counter++ + "\t");
            }else if ((str.charAt(i)=='\r')&&(!isInt)){
                out.write("\r" + counter++ + "\t");
            } else {
                out.write(str.charAt(i));
            }
        }
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String interString = str.substring(off, off + len);
        write(interString);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String str = "";
        for (int i = 0; i <= cbuf.length; ++i) {
            str = str + cbuf[i];
        }
        write(str, off, len);
    }

    public void write(char[] cbuf) throws IOException {
        String str = "";
        for (int i = 0; i <= cbuf.length; ++i) {
            str = str + cbuf[i];
        }
        write(str);
    }

    @Override
    public void write(int c) throws IOException {
        isInt=true;
        String str = "" + (char) c;
        write(str);
    }

}
