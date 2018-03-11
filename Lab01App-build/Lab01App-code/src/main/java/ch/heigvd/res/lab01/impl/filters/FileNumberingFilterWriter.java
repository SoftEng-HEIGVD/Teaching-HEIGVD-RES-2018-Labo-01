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

    private Integer numberLine = 1; //number in the beginning for every line.
    private Boolean firstLine = true; // if this is the first-line of the given text.
    private boolean breakLine = false; // if there is a new Line or not, use in Write with string.

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }


    /**
     * Override the write from Writer with String, Offset and Length.
     * This Override add a features : Adding number of line for every new lines.
     *
     * @author Yann Lederrey
     */
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

            // if there is a '\n' so new line
            if (strNew.charAt(i) == '\n') {
                breakLine = true;
            }

            // if there is a '\r'
            if (strNew.charAt(i) == '\r') {

                // if we are not at the finish of the line and the next character is a '\n'
                // if yes we ignore the '\r'
                if (i < strNew.length() - 1 - 2 && strNew.charAt(i + 1) == '\n') {
                    continue;
                } else {
                    breakLine = true;
                }
            }

            // if a new line is required
            if (breakLine) {
                numberLine++;
                strNew.insert(i + 1, numberLine + "\t");
                len = len + 1 + String.valueOf(numberLine).length();
                breakLine = false;
            }
        }
        super.write(strNew.toString(), 0, len);
    }

    /**
     * Override the write from Writer with Array, Offset and Length.
     * This Override add a features : Adding number of line for every new lines.
     *
     * @author Yann Lederrey
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < off + len; i++) {
            newStr.append(cbuf[i]);
        }
        // call the override write with String.
        write(newStr.toString(), 0, newStr.length());
    }

    /**
     * Override the write from Writer with Character.
     * This Override add a features : Adding number of line for every new lines.
     *
     * @author Yann Lederrey
     */
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
        // test if the next character is '\n', if yes add new line.
        if (c == '\n') {
            numberLine++;
            super.write(numberLine.toString());
            super.write('\t');
        }
    }

}
