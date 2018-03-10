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
    private boolean lastWasALineSpliter = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String[] lines;

        str = str.substring(off, str.length());

        while(!str.equals("") && len > 0){
            super.write(((Integer)lineNumber).toString() + "\t", 0, ((Integer) lineNumber).toString().length() + 1);
            ++lineNumber;
            lines = Utils.getNextLine(str);
            int length = len - lines[0].length() >= 0 ? len : lines[0].length();
            if (!lines[0].equals("")) {
                super.write(lines[0], 0, length);
            } else {
                super.write(lines[1], 0, length);
            }
            len -= length;
            str = lines[1];
        }
        if(len > 0){
            super.write(((Integer)lineNumber).toString() + "\t", 0, ((Integer) lineNumber).toString().length() + 1);
        }


    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for(int i = 0; i < cbuf.length - off; ++i){
            write(cbuf[i + off]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        testBeginning();
        if(c == '\n' || c == '\r'){
            super.write(c);
            if(!lastWasALineSpliter) {
                super.write(((Integer)lineNumber).toString() + "\t", 0, ((Integer) lineNumber).toString().length() + 1);
                ++lineNumber;
            }
            lastWasALineSpliter = true;
        } else{
            super.write(c);
            lastWasALineSpliter = false;
        }

    }

    private void testBeginning() throws IOException{
        if(isBeginning){
            super.write("1\t", 0, 2);
            isBeginning = false;
        }
    }

}
