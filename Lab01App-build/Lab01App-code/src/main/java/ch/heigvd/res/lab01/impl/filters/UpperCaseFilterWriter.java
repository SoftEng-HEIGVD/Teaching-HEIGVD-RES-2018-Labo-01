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

    String newStr;  //Formatted text

    //Get the substring asked from the text
    newStr = str.substring(off, off + len);

    //Transform the substring into upper cases
    newStr = newStr.toUpperCase();

    //Write the formatted text
    super.out.write(newStr);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

      char[] newCbuf = new char[len];  //Formatted text

      //Get the substring asked from the text
      int pos = 0;  //Index position in the formatted text

      for(int i = off; i < off + len; i++){
          //Transform the substring into upper cases
          newCbuf[pos] = Character.toUpperCase(cbuf[i]);
          pos++;
      }

      //Write the formatted text
      super.out.write(newCbuf);
  }

  @Override
  public void write(int c) throws IOException {
    throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
