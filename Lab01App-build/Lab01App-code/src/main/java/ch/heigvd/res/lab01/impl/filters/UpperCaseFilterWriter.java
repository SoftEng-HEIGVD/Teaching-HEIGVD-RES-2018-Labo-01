package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  /**
   * Write the string but in uppercase
   * @param str String to be written
   * @param off Offset from which to start writing characters
   * @param len Number of characters to write
   * @throws IOException 
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
      super.write(str.toUpperCase(), off, len);
  }

  /**
   * Write the char buffer but in uppercase
   * @param cbuf The char buffer
   * @param off Offset from which to start writing characters
   * @param len Number of characters to write
   * @throws IOException 
   */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      for(int i = 0; i < cbuf.length; ++i){
              cbuf[i] = Character.toUpperCase(cbuf[i]);
      }
      super.write(cbuf, off, len);
  }

  /**
   * write the char c but in uppercase
   * @param c Integer define a char to write
   * @throws IOException 
   */
  @Override
  public void write(int c) throws IOException {
      super.write(Character.toUpperCase(c));
  }

}
