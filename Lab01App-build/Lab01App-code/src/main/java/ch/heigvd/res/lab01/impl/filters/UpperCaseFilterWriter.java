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
    // If there's an error, do nothing.
    if(off > str.length() || len > str.length() || off + len > str.length()) return;

    String result = str.substring(0, off);
    result += str.substring(off, off+len).toUpperCase();
    result += str.substring(off+len, str.length());
    super.write(result,off,len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // If there's an error, do nothing.
    if(off > cbuf.length || len > cbuf.length || off + len > cbuf.length) return;

    for(int i = off; i < off + len; ++i){
      cbuf[i]=Character.toUpperCase(cbuf[i]);
    }
    super.write(cbuf,off,len);
  }

  @Override
  public void write(int c) throws IOException {
    super.write(Character.toUpperCase(c));
  }

}
