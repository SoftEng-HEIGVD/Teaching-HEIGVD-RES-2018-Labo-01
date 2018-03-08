package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * the transformation is just an uppercase
 * 
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
   /**
    * Constructor
    * 
    * @param wrappedWriter writer we decorate
    */
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  /**
   * use write(int c) on the concerned caracters of str
   * 
   * @param str string we want to write with this decorator
   * @param off start index where we want to apply the transformation
   * @param len numbers of caracters we want to apply the transformation
   * @throws IOException possible IOException as we use write method
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
     write(str.toCharArray(), off, len);
  }

  /**
   * use write(int c) on the concerned caracters of cbuf
   * 
   * @param cbuf string we want to write with this decorator
   * @param off start index where we want to apply the transformation
   * @param len numbers of caracters we want to apply the transformation
   * @throws IOException possible IOException as we use write method
   */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     for (int i = off; i < len + off && i < cbuf.length; i++) {
        write(cbuf[i]);
     }
  }

  /**
   * write the c caracter but in uppercase
   * 
   * @param c the next caracter we are writting
   * @throws IOException possible IOException as we use write method
   */
  @Override
  public void write(int c) throws IOException {
      super.write(Character.toUpperCase(c));
  }

}
