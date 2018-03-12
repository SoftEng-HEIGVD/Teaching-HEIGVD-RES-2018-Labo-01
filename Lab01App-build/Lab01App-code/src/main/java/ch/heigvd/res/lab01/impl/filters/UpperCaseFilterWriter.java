package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a character, it sends it to the decorated writer.
 * It then change the character to it's uppercase, before resuming the write process.
 *
 * @author Olivier Liechti
 * @author Dejvid Muaremi
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
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
   * Write the uppercase of a single character.
   * @param c the character to write.
   * @throws IOException when it can't write.
   */
  @Override
  public void write(int c) throws IOException {
    super.write(Character.toUpperCase(c));
  }
  
}
