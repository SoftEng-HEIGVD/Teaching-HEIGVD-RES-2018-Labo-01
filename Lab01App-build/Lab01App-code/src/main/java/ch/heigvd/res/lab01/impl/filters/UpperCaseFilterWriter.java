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
     out.write(str.toUpperCase(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     StringBuilder str = new StringBuilder();
     int lastCharIndex = off + len;

     // Use a StringBuilder to make only 1 call to out.write()
     for(int i = off; i < lastCharIndex; i++)
        str.append(Character.toUpperCase(cbuf[i]));

     out.write(str.toString());
  }

  @Override
  public void write(int c) throws IOException {
     out.write(Character.toUpperCase(c));
  }

}
