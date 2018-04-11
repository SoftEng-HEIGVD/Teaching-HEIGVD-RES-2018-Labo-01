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
    if(str.length() - off -len < 0 ){
      return;
    }

    String str2 = str.toUpperCase();
      out.write(str2,off,len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    if(cbuf.length - off - len  < 0 ){
      return;
    }

    String str = new String(cbuf);
    str  = str.toUpperCase();

    for(int i = 0 ; i < cbuf.length;++i){
      cbuf[i] = str.charAt(i);
    }

    out.write(cbuf,off,len);
  }

  @Override
  public void write(int c) throws IOException {

    //check if the number is alphabetical and lower casee if we make it upper case
    if(97 <=c && c <= 122){
      c -= 32;
    }

    out.write(c);
  }

}
