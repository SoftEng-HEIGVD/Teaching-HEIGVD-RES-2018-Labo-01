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

      /* if the parameters would throw an ArrayOutOfBoundsException,
       * then simply write the whole String */
      if(off < 0 || len < 0 || (off + len) < 0 || (off + len ) > str.length()){
          off = 0;
          len = str.length();
      }
      
      for (int i = 0; i < len; i++){
          write(str.charAt(i + off));
      }
    
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    
    write(new String(cbuf), off, len);

  }

  @Override
  public void write(int c) throws IOException {
    super.write(Character.toUpperCase(c));
  }

}
