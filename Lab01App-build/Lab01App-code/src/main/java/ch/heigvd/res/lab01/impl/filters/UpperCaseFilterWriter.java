package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * The filter transform all the Character in upper case Character.
 *
 * @author Olivier Liechti
 * @author GuillaumeBlanco
 *
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    super.write(str.toUpperCase(),off,len); // We don't call write(int c) here because we have already a JavaFunction who do the job
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < (off + len);++i){
      write(cbuf[i]); // we do the treatment in write(int c)
    }
  }

  @Override
  public void write(int c) throws IOException {
    super.write(Character.toUpperCase(c));
  }

}
