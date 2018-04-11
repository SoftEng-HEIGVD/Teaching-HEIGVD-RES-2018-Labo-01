package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  /**
   * Writes a portion of a string converted to upper case
   * @param str String to be written
   * @param off Offset from which to start reading characters
   * @param len Number of characters to be written
   * @throws IOException
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
    super.write(str.toUpperCase(Locale.ENGLISH), off, len);
  }

  /**
   * Writes a portion of an array of characters
   * @param cbuf Buffer of characters to be written
   * @param off Offset from which to start reading characters
   * @param len Number of characters to be written
   * @throws IOException
   */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    super.write(new String(cbuf).toUpperCase(Locale.ENGLISH), off, len);
  }

  /**
   * Writes a single character
   * @param c int specifying a character to be written
   * @throws IOException
   */
  @Override
  public void write(int c) throws IOException {
    super.write(Character.toUpperCase(c));
  }

}
