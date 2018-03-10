package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/**
 *
 * @author Olivier Liechti
 * @contributor Nair Alic
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }
  
  @Override
  public void write(String str) throws IOException {
     out.write(str.toUpperCase());
  }
  
  @Override
  public void write(String str, int off, int len) throws IOException {
     //calls first function
     write(str.substring(off, len + off));
  }
  
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     for(int i = off; i < off + len; i++) {
        cbuf[i] = Character.toUpperCase(cbuf[i]);
     }
     out.write(cbuf, off, len);
  }

  @Override
  public void write(int c) throws IOException {
     out.write(Character.toUpperCase((char)c));
  }

}
