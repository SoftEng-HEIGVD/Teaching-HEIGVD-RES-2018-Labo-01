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
 * @author Olivier Nicole (Student)
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int counter = 0;
  private boolean isBackslashR = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    /*
     * Updated by Olivier Nicole
     * Use the method toCharArray() from String to call write(char[] bcuf, ...)
     */
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    /*
     * Updated by Olivier Nicole
     * Call write(int c) with every char of the array
     */
    for (int i = off; i < len + off; ++i) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {

    /*
     * Updated by Olivier Nicole
     */

    //First line detected
    //OR the character is not a line return and '\r' detected on the previous call
    // -> add number and tabulation
    if (counter == 0 || (c != '\n' && isBackslashR)) {
      out.write(++counter + "\t");
    }

    //Check if the character is a '\r' and keep it for the next call
    isBackslashR = (c == '\r');

    out.write(c);

    //Finally add counter number and a tabulation
    if (c == '\n') {
      out.write(++counter + "\t");
    }
  }
}
