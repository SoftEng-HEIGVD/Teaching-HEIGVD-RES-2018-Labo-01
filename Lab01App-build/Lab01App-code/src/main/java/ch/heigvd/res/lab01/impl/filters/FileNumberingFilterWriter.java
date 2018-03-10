package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.StringReader;
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
    private int nbLines;
    private boolean firstLine;
    private boolean backslashRDetected;
    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    public FileNumberingFilterWriter(Writer out) {
        super(out);
        nbLines = 1;
        firstLine = true;
        backslashRDetected = false;
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        char[] cbuf = str.toCharArray();
        this.write(cbuf,off,len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for(int i = off; i < off + len; i++){
            this.write(cbuf[i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        if(nbLines == 1 && firstLine){
            firstLine = false;
            this.out.write(nbLines + "\t");
        }
        if(c == '\n' && !backslashRDetected){
            nbLines++;
            this.out.write("\n" + nbLines + "\t");
        } else if(c == '\r'){
            if(backslashRDetected){
                nbLines++;
                this.out.write("\r" + nbLines + "\t");
            }
            backslashRDetected = true;
        } else if(c == '\n' && backslashRDetected){
            nbLines++;
            this.out.write("\r\n" + nbLines + "\t");
            backslashRDetected = false;
        }else if(backslashRDetected){
            nbLines++;
            this.out.write("\r" + nbLines + "\t" + (char) c);
            backslashRDetected = false;
        }else{
            this.out.write((char) c);
        }
    }

}
