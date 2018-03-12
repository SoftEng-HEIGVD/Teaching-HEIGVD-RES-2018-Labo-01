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
    private boolean first = false;//to test if it's the first line or no
    private boolean hadBackSlashRandBackSlashN = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
        counter = 1;
        first = true;

    }

    @Override
    public void write(String str) throws IOException {
        if (str.contains("\r\n") && (!hadBackSlashRandBackSlashN)) {
            hadBackSlashRandBackSlashN = true;
        }
        for (int i = 0; i < str.length(); ++i) {
            String str1 = str.substring(i);
            if (!str1.contains("\r\n")) {
                hadBackSlashRandBackSlashN = false;
            }
            if (first) {
                out.write(counter++ + "\t" + str.charAt(i));
                first = false;
            } else if (str.charAt(i) == '\n') {
                out.write("\n" + counter++ + "\t");
            } else if ((str.charAt(i) == '\r') && (!hadBackSlashRandBackSlashN)) {
                out.write("\r" + counter++ + "\t");
            } else {
                out.write(str.charAt(i));
            }
        }
    }

    /*
    function to convert char[] to string 
     */
    public String charTabToString(char[] cbuf) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < cbuf.length; ++i) {
            str.append(cbuf[i]);
        }
        return str.toString();
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String interString = str.substring(off, off + len);
        write(interString);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        write(charTabToString(cbuf), off, len);
    }

    public void write(char[] cbuf) throws IOException {

        write(charTabToString(cbuf));
    }

    @Override
    public void write(int c) throws IOException {
        /*
        I know its not the best implementation i can do 
        but i didn't have time to do best 
        sorry i will try next time
         */
        hadBackSlashRandBackSlashN = true;
        char str = (char) c;
        if (first) {
            out.write(counter++ + "\t" + str);
            first = false;
        } else if (str == '\n') {
            out.write("\n" + counter++ + "\t");
        } else if ((str == '\r') && (!hadBackSlashRandBackSlashN)) {
            out.write("\r" + counter++ + "\t");
        } else {
            out.write(str);
        }

    }

}
