package ch.heigvd.res.lab01.impl.filters;

import javassist.bytecode.ClassFileWriter;

import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Jérémie Châtillon
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    super.write(str.toUpperCase(), off, len);

  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    if(len > cbuf.length)
      return;

    for (int i = off; i < off + len; ++i)
      this.write(cbuf[i]);
  }

  @Override
  public void write(int c) throws IOException {
    super.write(Character.toUpperCase(c));
  }

}
