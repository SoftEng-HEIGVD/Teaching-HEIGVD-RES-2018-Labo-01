package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Filter;

/**
 * This class implements methods
 * @author Olivier Liechti
 * @author Patrick Neto
 */
public class UpperCaseFilterWriter extends FilterWriter {

  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      this.write(str.toUpperCase().toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      //for each character of the string or substring, write its uppercase
      for(int i = off; i < off + len ; i++) {
          this.write((int)cbuf[i]);
      }
  }

  @Override
  public void write(int c) throws IOException {
     out.write(Character.toUpperCase((char)c));
  }
}
