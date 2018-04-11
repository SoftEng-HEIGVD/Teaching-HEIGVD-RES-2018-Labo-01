package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Walid Koubaa
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
      super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      // for each of the characters in our buffer we set it in UpperCase
      super.write(str.toUpperCase(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      for (int i = 0; i < cbuf.length; ++i) {       // for each of the characters in our buffer
          cbuf[i] = Character.toUpperCase(cbuf[i]); // writes a character and sets it in UpperCase
      }
      super.write(cbuf, off, len);                  // then we write the buffer on the Writer
  }

  @Override
  public void write(int c) throws IOException {
      super.write(Character.toUpperCase(c));    // writes a character and sets it in Uppercase
  }

}
