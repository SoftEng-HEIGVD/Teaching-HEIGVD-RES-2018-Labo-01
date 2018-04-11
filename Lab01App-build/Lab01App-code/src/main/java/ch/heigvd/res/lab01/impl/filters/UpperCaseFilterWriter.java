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
    // We iterate over the string and set each char to its upper value
    StringBuilder sb = new StringBuilder();
    for (int i = 0; off + i < str.length() && i < len; ++i)
      sb.append(Character.toUpperCase(str.charAt(off + i)));
    super.write(sb.toString(), 0, len);
  }

  // We build a String for the char[] and call the previous method
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    StringBuilder strSB = new StringBuilder();
    for (char c : cbuf)
      strSB.append(c);
    String str = strSB.toString();
    write(str, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    super.write(Character.toUpperCase(c));  // Nothing to add
  }

}
