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
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private Integer numberLine = 1;
    private Boolean firstLine = true;
    private boolean breakLine = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        StringBuilder strNew = new StringBuilder(str.substring(off, off + len));


        // test if this is the first line of the document
        if (firstLine) {
            firstLine = false;
            strNew.insert(0, numberLine + "\t");
            len = len + 2;
        }

        // write the character
        for (int i = 0; i < strNew.length(); i++) {

            if (strNew.charAt(i) == '\n') {
                breakLine = true;
            }
            if (strNew.charAt(i) == '\r') {
                if (i < strNew.length()-1 - 2 && strNew.charAt(i + 1) == '\n') {
                    continue;
                } else {
                    breakLine = true;
                }
            }

            if (breakLine) {
                numberLine++;
                strNew.insert(i + 1, numberLine + "\t");
                len = len + 1 + String.valueOf(numberLine).length();
                breakLine = false;
            }
        }
        super.write(strNew.toString(), 0, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = 0; i < off + len; i++) {
            write(cbuf[i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        // test if this is the first line of the document
        if (firstLine) {
            firstLine = false;
            super.write(numberLine.toString());
            super.write('\t');
        }

        // write the character
        super.write(c);
        if (c == '\n') {
            numberLine++;
            super.write(numberLine.toString());
            super.write('\t');
        }
    }

}
