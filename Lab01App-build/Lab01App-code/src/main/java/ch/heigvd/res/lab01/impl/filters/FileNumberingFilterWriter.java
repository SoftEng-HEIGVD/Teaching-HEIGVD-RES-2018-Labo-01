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
  private static int lineNumber = 1;
  private String output;

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    
    output = lineNumber++ + "\t";
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    String[] fLineAndRemaining = {"", str.substring(off, off+len)};
    
    if (output.length() > 2) {
      output = "";
    }
    
    do {
      fLineAndRemaining = Utils.getNextLine(fLineAndRemaining[1]);
      output += fLineAndRemaining[0] + lineNumber++ + "\t";
      
    } while (!fLineAndRemaining[1].isEmpty());
    
    out.write(output);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
    throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
