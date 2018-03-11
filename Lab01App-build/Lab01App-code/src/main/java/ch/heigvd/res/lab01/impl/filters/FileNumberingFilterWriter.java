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

    private int  lineNumber = 1;
    private boolean isBeginning = true;
    private boolean lastWasRSpliter = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        int lastIndex = (off + len) < str.length()? (off + len) : str.length();
        str = str.substring(off, lastIndex);

        for(int i = 0; i < str.length(); ++i){
            write(str.charAt(i));
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        int lastIndex = (off + len) < cbuf.length? (off + len) : cbuf.length;
        for(int i = 0; i < lastIndex - off; ++i){
            write(cbuf[i + off]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        if(isBeginning){
            super.write(((Integer) lineNumber).toString() + "\t", 0, ((Integer) lineNumber).toString().length() + 1);
            ++lineNumber;
            isBeginning = false;
        }
        if(c == '\n'){
            super.write(c);
            super.write(((Integer)lineNumber).toString() + "\t", 0, ((Integer) lineNumber).toString().length() + 1);
            ++lineNumber;
            lastWasRSpliter = false;
        } else if(c == '\r'){
            super.write(c);
            lastWasRSpliter = true;
        }else{
            if(lastWasRSpliter){
                super.write(((Integer)lineNumber).toString() + "\t", 0, ((Integer) lineNumber).toString().length() + 1);
                ++lineNumber;
            }
            super.write(c);
            lastWasRSpliter = false;
        }

    }

}
