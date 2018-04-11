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
 * @author Dejvid Muaremi
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int line = 1;
  private int previous = 0;
  private static final char TAB ='\t';
  private static final char RET_M ='\r';
  private static final char RET_U ='\n';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  /***
   * Write a string with the correct filter.
   * @param str the input string to write.
   * @param off the offset for the first character to write.
   * @param len the number of character to write.
   * @throws IOException when it can't write.
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
    // We don't meed to code this part. A string can be an array of char.
    write(str.toCharArray(),off,len);
  }

  /***
   * Write an array of char with the correct filter.
   * @param cbuf the input array to write.
   * @param off the offset for the first character to write.
   * @param len the number of character to write.
   * @throws IOException when it can't write.
   */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // We don't need to code this part. An array of char is just some char together,
    for(int i = off; i < off + len; ++i) {
      write(cbuf[i]);
    }
  }

  /***
   * Write a single character.
   * Remember the last written character for the case when there's a Windows carriage return.
   * @param c the character to write and remember.
   * @throws IOException when it can't write.
   */
  @Override
  public void write(int c) throws IOException {
    if(line == 1){
      newLineWriter();
    }
    // manage the case when it's a Mac carriage return.
    if(previous == RET_M && c != RET_U){
      newLineWriter();
    }

    super.write(c);

    // manage the case when it's a Windows or Unix like carriage return.
    if(c == RET_U){
      newLineWriter();
    }

    previous = c;
  }

  /***
   * Write a new line with its number and a blank as defined.
   * Increment the line number.
   * @throws IOException when it can't write.
   */
  private void newLineWriter() throws IOException{
    // Manage the case when the line number contains more than 1 digits.
    super.write(String.valueOf(line), 0, String.valueOf(line).length());
    super.write(TAB);
    ++line;
  }
}
