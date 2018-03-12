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
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  
  private int numLine = 1;
  private int lastChar = 0;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    String[] lines = Utils.getNextLine(str.substring(off, off + len));
    String numbering = Integer.toString(numLine) + '\t';
    if(lines[0].equals("")) {
         super.write(numbering + lines[1], 0, lines[1].length() + numbering.length());
    } else {
       super.write(numbering + lines[0], 0, lines[0].length() + numbering.length());
       numLine++;
       if(!lines[1].equals(""))
         write(lines[1], 0, lines[1].length());
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; ++i) {
        write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if(numLine == 1){
       super.write(Integer.toString(numLine++) + '\t');     
    }
    if(lastChar == '\r' && c != '\n') {
       super.write(Integer.toString(numLine++) + '\t');     
    }
    super.write(c);
    if(c == '\n') {
       super.write(Integer.toString(numLine++) + '\t');     
    }
    lastChar= c;
  }

}
