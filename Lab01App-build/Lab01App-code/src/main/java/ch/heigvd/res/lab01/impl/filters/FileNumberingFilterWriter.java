package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

import java.io.BufferedOutputStream;
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
    private boolean nextIsNewLine;
    private int nextLineNumber;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
        nextLineNumber = 0;
        nextIsNewLine = true;
    }


    @Override
    public void write(String str, int off, int len) throws IOException {

        String sub = str.substring(off, off + len);
        String beginning = str.substring(0,off);
        String end = str.substring(off + len, str.length());

        String decorated = beginning + decorate(sub, "") + end;
        len += decorated.length() - str.length();

        super.write(decorated, off, len);

    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String str = String.valueOf(cbuf);

        String sub = decorate(str.substring(off, off + len), "");
        String beginning = str.substring(0,off);
        String end = str.substring(off + len, str.length());



        super.write((beginning + sub + end).toCharArray(), off, len);
    }

    @Override
    public void write(int c) throws IOException {
        // no sens in adding numbers to a single char
        super.write(c);
    }


    public String decorate(String i, String o){

        // is it done yet ?
        if(i.equals("")){
            return o;
        }

        // split it
        String[] split = Utils.getNextLine(i);

        // si pas de return, pas de nouvelle ligne
        if(split[0].equals("")){
            return o + ((nextIsNewLine) ? (++nextLineNumber + "\t") : "" ) + split[1];
        }
        // si return, il y a need de continuer
        else {
            o = o + ((nextIsNewLine) ? (++nextLineNumber + "\t") : "" ) + split[0];
            nextIsNewLine = true;
             return decorate(split[1], o);
        }
    }
}
