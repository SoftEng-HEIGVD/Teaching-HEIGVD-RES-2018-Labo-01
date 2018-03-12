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
 * I tought it would be "util" to use getNextLine method from Utils.java,
 * but I encountered lots of mistakes, so I resigned myself..
 * Sorry for repeatedly buy a single beer instead of buy a pack..  
 *
 * @author Olivier Liechti
 * @author Adam Zouari
 * 
 * 
 */
public class FileNumberingFilterWriter extends FilterWriter {
    
    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int lineNumber;
    private boolean isFirstLine, isEndOfLine;

    
    public FileNumberingFilterWriter(Writer out) {
        super(out);
        lineNumber = 1;
        isFirstLine = true;
        isEndOfLine = false;
    }
      
    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
        
    }
    
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for(int i = off; i < off + len ; ++i)
            write(cbuf[i]);
    }
    
    @Override
    public void write(int c) throws IOException {
        
        // First char of the line or WINDOWS/MACOS9 cases
        if (isFirstLine || (isEndOfLine && c != '\n')) {
            newLine();
            isFirstLine = false;
        }
        out.write(c);
        
        // '\r' is always a sign of a new line (last or before last character)
        if(c == '\r')
        {
            isEndOfLine = true;
        }
        // UNIX new line
        else if (c == '\n')
        {
            newLine();
        }
    }
    
    /**
     * This method print the number of the line with the tabulation
     * @throws IOException
     */
    private void newLine() throws IOException {
       
        out.write(String.format("%d\t", lineNumber++));
        isEndOfLine = false;
    }
    
}
