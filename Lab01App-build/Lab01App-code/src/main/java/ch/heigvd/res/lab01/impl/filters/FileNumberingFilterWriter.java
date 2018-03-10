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

    private boolean begin = true;
    private Integer lineNumber = 1;
    private boolean lineSeparator = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        for (int i = off; i < off + len; ++i) {
            this.write(str.charAt(i));
        }
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; ++i) {
            this.write(cbuf[i]);
        }
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

    @Override
    public void write(int c) throws IOException {
        String beginLine = "";
        if (begin) {
            beginLine = Integer.toString(lineNumber) + "\t" + (char)c;
            begin = false;
        } else if(c == (int)'\r'){
            beginLine += (char)c;
            lineSeparator = true;
        } else if(c == (int)'\n') {
            ++lineNumber;
            beginLine = (char)c + Integer.toString(lineNumber) + "\t";
            lineSeparator = false;
        } else if(lineSeparator){
            ++lineNumber;
            beginLine = Integer.toString(lineNumber) + "\t" + (char)c;
            lineSeparator = false;
        }else{
            beginLine += (char)c;
        }
        super.write(beginLine, 0, beginLine.length());
    }

}
