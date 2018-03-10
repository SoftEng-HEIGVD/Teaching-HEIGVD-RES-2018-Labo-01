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
    private int lineNumber;
    private boolean mayBeDoubleReturn;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
        lineNumber = 1;
        mayBeDoubleReturn = false;
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
       write(str.toCharArray(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for(int i = 0; i < len; ++i){
            write(cbuf[off + i]);
        }

    }

    @Override
    public void write(int c) throws IOException{
            if(lineNumber == 1){
                out.write(lineNumber++ + "\t");
            }

            // if the former char written was \r, we look if the current one is a \n
            if(mayBeDoubleReturn){
                if(c != '\n'){
                    // if it's not the case, we write the new line number, reset the flag and continue the function
                    out.write(lineNumber++ + "\t");
                    mayBeDoubleReturn = false;
                }else{
                    // if a double return has been found, we write the \n on the line and only then we write a new line number.
                    // then we get out of the function after resetting the flag since the char has already been written.
                    out.write(c);
                    out.write(lineNumber++ + "\t");
                    mayBeDoubleReturn = false;
                    return;
                }
            }

            out.write(c);

            // if the written char is a \r, we may be in a case of double return hence, the line number isn't written until
            // the next char is read.
            if(c == '\r'){
                    mayBeDoubleReturn = true;
            }else if(c == '\n'){
                out.write(lineNumber++ + "\t");
            }
    }

}
