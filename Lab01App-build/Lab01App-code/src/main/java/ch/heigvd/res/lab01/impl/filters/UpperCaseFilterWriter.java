package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Olivier Nicole (Student)
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

      /*
       * Updated by Olivier Nicole
       * Use toUpperCase from the class String
       */
      super.write(str.toUpperCase(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

      /*
       * Updated by Olivier Nicole
       * Create a new String with cbuf to call write(String str...)
       */
      write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
      //throw new UnsupportedOperationException("The student has not implemented this method yet.");
      // Use the Character class tu use the static method toUpperCase :
      //  - https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html

      /*
       * Updated by Olivier Nicole
       * Use the Character class tu use the static method toUpperCase :
       * - https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html
       */
      super.write(Character.toUpperCase(c));
  }
}
