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

    //number of the line
    private int lineNumber = 1;

    //indicates when this is the first time we write in the writer
    private boolean isBeginning = true;

    //indicated that the last line was ended with a '\r' character
    private boolean lastWasRSpliter = false;


    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    /**
     * Write a string in the Writer,adding  a line number and a tabulation at eh beginning of each new line
     *
     * @param str  string to write in the Writer
     * @param off, offest of the line
     * @param len, length of the line
     * @throws IOException
     */
    @Override
    public void write(String str, int off, int len) throws IOException {

        // the string is processed with the offset and the lenght
        int lastIndex = (off + len) < str.length() ? (off + len) : str.length();
        str = str.substring(off, lastIndex);

        // the processed string is splited in characters, that all call the write(int c) method
        for (int i = 0; i < str.length(); ++i) {
            write(str.charAt(i));
        }
    }

    /**
     * Write the content of a char[] in the Writer,adding  a line number and a tabulation at eh beginning of each new line
     *
     * @param cbuf char[] containtinig the characters to write in the Writer
     * @param off, offest of the line
     * @param len, length of the line
     * @throws IOException
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        int lastIndex = (off + len) < cbuf.length ? (off + len) : cbuf.length;
        // the write(int c) method is called for all the characters of the char[]
        for (int i = 0; i < lastIndex - off; ++i) {
            write(cbuf[i + off]);
        }
    }

    /**
     * write a character in the Writer
     *
     * @param c, the character to write
     * @throws IOException
     */
    @Override
    public void write(int c) throws IOException {
        //at the beginnig the first line number is written, no matters th econtents of the line
        if (isBeginning) {
            super.write(((Integer) lineNumber).toString() + "\t", 0, ((Integer) lineNumber).toString().length() + 1);
            ++lineNumber;
            isBeginning = false;
        }

        //if the \n character is encountered, a line number and a backslash is added
        if (c == '\n') {
            super.write(c);
            super.write(((Integer) lineNumber).toString() + "\t", 0, ((Integer) lineNumber).toString().length() + 1);
            ++lineNumber;
            lastWasRSpliter = false;
            //if the \r we keep a trace of it, if the next char is not a spliter, the the line number an tabulation will be added
        } else if (c == '\r') {
            super.write(c);
            lastWasRSpliter = true;
        } else {
            //if the last character was a \r, then it means this character is at the beginning of a line, a new line number
            //and a tabulation are added
            if (lastWasRSpliter) {
                super.write(((Integer) lineNumber).toString() + "\t", 0, ((Integer) lineNumber).toString().length() + 1);
                ++lineNumber;
            }
            super.write(c);
            lastWasRSpliter = false;
        }

    }

}
