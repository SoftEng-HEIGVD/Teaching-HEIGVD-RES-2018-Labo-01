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

  @Override
  public void write(String str, int off, int len) throws IOException {
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");
    out.write(str.substring(off, off+len).toUpperCase());

  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for (int i = 0; i < len; i++)
      write(cbuf[off + i]);
  }

  @Override
  public void write(int c) throws IOException {
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");

    if (c >= 'a' && c <= 'z')
      out.write(c - 'a' + 'A');
    else
      out.write(c);
  }

}
