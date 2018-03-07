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
    int noLigne = 0;

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String consider = str.substring(off,off+len);
        String[] tabStr = Utils.getNextLine(consider);
        if(noLigne == 0){
            super.write(Integer.toString(++noLigne) + TABULATION, 0, 2);
        }

        while (tabStr[0].length() != 0){

            super.write(tabStr[0]+ Integer.toString(++noLigne )+ TABULATION , 0, tabStr[0].length()+2 + (int)Math.log10((double)noLigne));
            tabStr = Utils.getNextLine(tabStr[1]);
        }

        if(tabStr[1].length() != 0) {
            super.write(tabStr[1], 0, tabStr[1].length());
        }

        //super.write(Integer.toString(++noLigne) + TABULATION, 0, 2);

    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String str = cbuf.toString();
        write(str, off, len);
    }

    @Override
    public void write(int c) throws IOException {
        if(noLigne==0){
            ++noLigne;
            super.write(Integer.toString(noLigne));
            super.write(TABULATION);
        }
        super.write(c);
        if(c == '\n'){
            ++noLigne;
            super.write(Integer.toString(noLigne));
            super.write(TABULATION);
        }
    }

}
