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
     write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     int lastCharIndex = off + len;

     for(int i = off; i < lastCharIndex; i++)
        write(cbuf[i]);
  }

  @Override
  public void write(int c) throws IOException {
     out.write(Character.toUpperCase(c));
  }

}
