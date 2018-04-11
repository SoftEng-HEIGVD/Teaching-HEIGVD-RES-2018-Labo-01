package ch.heigvd.res.lab01.impl.filters;

import static ch.heigvd.res.lab01.impl.Utils.*;
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

    //  current line number
    private int numberOfLine = 1;
    
    //and 2 useful boolean variables to help us take care some special cases 

    // boolean if first line number has been written
    private boolean firstLineOk = false;

    // boolean if the last character that was written was a line separator
    private boolean prevCharSeparator = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        // handle first line
        if (firstLineOk == false) {
            writeAndIncLine();
            firstLineOk = true;
        }

        // look for lines
        int endOfCut = off+len;
        String[] lines = getNextLine(str.substring(off, endOfCut));
        while (!(lines[0].equals(""))) {

            // write the first line, write number of next line, and then look for more lines
            super.write(lines[0], 0, lines[0].length());
            writeAndIncLine();
            str = lines[1];
            lines = getNextLine(str);
        }

        // write what is left of the text
        super.write(lines[1], 0, lines[1].length());
        

        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }
    
    

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // write characters one by one in array from the offset until the end of the cut
        int endOfCut = off+len;
        for (int i = off; i < endOfCut; i++) {
            write(cbuf[i]);
        }
        
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

    @Override
    public void write(int c) throws IOException {
        // takes care of case where the number of line is 1
        if (firstLineOk == false) {
            writeAndIncLine();
            firstLineOk = true;
        }
        
        // char is separator, so we write it
        if (c == '\r' || c == '\n') {
            super.write(c);
            prevCharSeparator = true;
        }
        
        // other characters
        else {
            // if character before was a line separator, insert the number of line
            if (prevCharSeparator == true) {
                prevCharSeparator = false;
                writeAndIncLine();

            }
            
           //we write the character
            super.write(c);
        }
        
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }
    
    //(auxiliary function, more practical than multiple copy-pastes of 3 lines) increments and writes the current number of line
    private void writeAndIncLine() throws IOException {
        String line = numberOfLine + "\t";
        numberOfLine++;
        super.write(line, 0, line.length());
    }

}
