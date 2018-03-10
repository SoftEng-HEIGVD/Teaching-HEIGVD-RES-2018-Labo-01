package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.BufferOverflowException;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {
    final static char TABULATION = '\t';
    private int noLigne = 0; // count the lines displayed
    private int previous = 0; // we store the previous displayed char

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        // we first check if the offset and the length doesn't exceed the length of the string
        if(off + len > str.length()) throw new BufferOverflowException();

        // we process the string char by char
        for(int i = off ; i < off + len ; ++i){
            int c = str.charAt(i);
            write(c);
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // we first check if the offset and the length doesn't exceed the length of the string
        if(off + len > cbuf.length) throw new BufferOverflowException();

        // we process the "string" char by char
        for(char c : cbuf){
            write(c);
        }
    }

    @Override
    public void write(int c) throws IOException {

        // if we haven't displayed anything yet, we number the first line
        if(noLigne == 0){
            numberNewLine();
        }

        // case if the \r was written alone
        if(previous == '\r' && c != '\n'){
            previous = 0;
            numberNewLine();
        }

        // display the actual char and store it as "previous char"
        super.write(c);
        previous = c;


        if(c == '\n'){
            numberNewLine();
        }
    }

    private void numberNewLine() throws IOException{
        ++noLigne;
        super.write(Integer.toString(noLigne));
        super.write(TABULATION);
    }

}
