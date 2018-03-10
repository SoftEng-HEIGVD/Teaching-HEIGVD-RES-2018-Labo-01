package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException { //TODO use shorter substring and recall with o, substr.len
    this.out.write(str.toUpperCase(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException { //TODO use shorter array, recall shorter
    // array copy for encapsulation
    char[] cbufCopy = Arrays.copyOf(cbuf, cbuf.length);

    // convert characters of array to upper casse
    for(int i = off; i < off + len; ++i)
      cbufCopy[i] = Character.toUpperCase(cbufCopy[i]);
    this.out.write(cbufCopy, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    this.out.write(Character.toUpperCase(c));
  }

}
