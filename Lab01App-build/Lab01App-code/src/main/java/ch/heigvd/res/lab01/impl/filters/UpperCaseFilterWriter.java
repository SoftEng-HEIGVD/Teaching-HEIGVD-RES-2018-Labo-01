package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Labinot Rashiti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
     String upperString = str.toUpperCase(); // Convert string to a uppercase string
     super.write(upperString, off, len);
    
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     // Convert lowercase to uppercase (char by char)
     for (int i = 0; i < cbuf.length; ++i)
        cbuf[i] = Character.toUpperCase(cbuf[i]);
     
     super.write(cbuf, off, len);
 
  }

  @Override
  public void write(int c) throws IOException {
     super.write(Character.toUpperCase(c)); // Convert the lonely char to uppercase

  }

}
