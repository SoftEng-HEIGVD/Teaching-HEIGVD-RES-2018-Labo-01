package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Christophe Joyet
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
  //  throw new UnsupportedOperationException("The student has not implemented this method yet.");
    write(str.toCharArray(), off, len); // make an array of char with a String
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
   // throw new UnsupportedOperationException("The student has not implemented this method yet.");

    /* write char into cbuf */
    for (int i = 0; i < len; i++) {
      write(cbuf[off + i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
    super.write(Character.toUpperCase(c)); // transform char in UpperCase
  }

}
