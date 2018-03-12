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
  private  int number_of_line = 1;
  private  int last_char_str;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
     write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     
     int i;
     for(i = off; i < off + len; ++i){
     write(cbuf[i]);
      }
  }

@Override
  public void write(int c) throws IOException {
      if (number_of_line ==1){
      writeHeaderLine();
  }
      
  if ((this.last_char_str == '\r' && c != '\n')) {
      writeHeaderLine();
     }
     out.write(c);
     
     if (c == '\n') {
        writeHeaderLine();
     }
     
     this.last_char_str = c;
  }
  
    private void writeHeaderLine() throws IOException {
        out.write(number_of_line + "\t");
        number_of_line++;
    }
      
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
}


