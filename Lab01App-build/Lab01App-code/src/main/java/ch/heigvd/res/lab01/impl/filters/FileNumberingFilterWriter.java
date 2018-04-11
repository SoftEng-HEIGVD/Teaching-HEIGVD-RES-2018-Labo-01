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

    // Indicate that we are in the beginning of the file
    private boolean begin = true;
    // Allow us to store the actual number of line
    private Integer lineNumber = 1;
    // Indicate if we are at the end of the line for the differents line separators
    private boolean endOfLine = false;
    // Indicate the string to write
    private String toWrite;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        // write char by char using the algorithm of the write(int c) function
        for (int i = off; i < off + len; ++i) {
            this.write(str.charAt(i));
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // write char by char using the algorithm of the write(int c) function
        for (int i = off; i < off + len; ++i) {
            this.write(cbuf[i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        // If we are at the beginning of the file, we print
        // the number of the line followed by a tabulation
        if (begin) {
            toWrite = Integer.toString(lineNumber) + "\t" + (char)c;
            begin = false;
            // if there is a '\r', we will know with the next character if 
            // that's the end of the line or not
        } else if(c == (int)'\r'){ 
            toWrite = "" + (char)c;
            endOfLine = true;
        } else if(c == (int)'\n') { // We are sure that's the end of a line
            ++lineNumber;
            toWrite = (char)c + Integer.toString(lineNumber) + "\t";
            endOfLine = false;
        } else if(endOfLine){ // We are sure that's the end of the line
            ++lineNumber;
            toWrite = Integer.toString(lineNumber) + "\t" + (char)c;
            endOfLine = false;
        }else{ // for all other characters, we simply add them to the string
            toWrite = "" + (char)c;
        }
        // We finally write the appropriate string
        super.write(toWrite, 0, toWrite.length());
    }

}
