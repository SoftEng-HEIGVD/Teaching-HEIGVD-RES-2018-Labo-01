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
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 * @author Cedric Lankeu
 * 
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    
    // contents the previous character .
    private int previousCharacter;
    // to check if the first character has  been treated
    private boolean isFirstCharTreated = true;
    // save the lines number.
    private int lineNumber = 0;
    
    // this fonction, give us the lines numerotation
    private void setLineNumerotation() throws IOException {
        String line = String.format("%d\t", ++lineNumber);
        super.out.write(line);
    }

    public FileNumberingFilterWriter(Writer out) throws IOException {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int i = off; i < off + len; ++i) {
            write(str.charAt(i));
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; ++i) {
            write(cbuf[i]);
        }
    }

    @Override
    public void write(int c) throws IOException 
    {
        // first char to be proceed, if it's the case, isFirstCharTreated takes false
        if (isFirstCharTreated) 
        {
            setLineNumerotation();
            isFirstCharTreated = false;
        }
        if(c == '\n')
        {
            super.write(c);
            setLineNumerotation();
        }
        else
        { 
            if (previousCharacter == '\r') {
                setLineNumerotation();
            }
          super.write(c);
        }
        previousCharacter = c;
    }

    

}
