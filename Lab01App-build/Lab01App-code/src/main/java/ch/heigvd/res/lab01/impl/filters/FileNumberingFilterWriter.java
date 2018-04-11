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

    private int counter             = 1;
    private boolean returned        = false;
    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    public FileNumberingFilterWriter(Writer out) {

        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        //Delegate to write(char[])
        write(str.toCharArray(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        //Handle string splitting and delegate alteration to write(char)
        for(int i = off; i < off + len; i++)
            write(cbuf[i]);
    }

    @Override
    public void write(int c) throws IOException {

        //First, check if it's a plain new line
        if(counter == 1)
            out.write(tab());

        //Line break WINDOWS AND OSX
        if(c == '\r') {

            out.write(c);
            returned = true;
        }

        //Line break OSX completion
        else if(returned && c != '\n') {

            out.write(tab());
            out.write(c);
            returned = false;
        }

        //Line break Linux, and windows completion
        else if(c == '\n') {

            out.write(c);
            out.write(tab());

            if(returned)
                returned = false;
        }

        else
            out.write(c);
    }

    /**
     * Simply concatenates the current line number (and updating it) with the tab char
     * @return Beginning of new line
     */
    private String tab() {

        return (counter++) + "\t";
    }
}
