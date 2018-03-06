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
  boolean beginning = true;
  int lineCounter = 0;
  int previous = 'a';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for (int i = off; i < len + off && i < cbuf.length; i++) {
        write(cbuf[i]);
     }
  }

  @Override
  public void write(int c) throws IOException {
     if(beginning) {
        printLineNumber();
        beginning = false;
     }
     if(previous == '\r' && c != '\n') {
        printLineNumber();
     }
     super.write(c);
     
     if(c == '\n') {
        printLineNumber();
     }
     previous = c;
  }
  
  private void printLineNumber() throws IOException {
     lineCounter++;
     String charNumber = String.valueOf(lineCounter);
     super.write(charNumber, 0, charNumber.length());
     super.write('\t');
  }

}
