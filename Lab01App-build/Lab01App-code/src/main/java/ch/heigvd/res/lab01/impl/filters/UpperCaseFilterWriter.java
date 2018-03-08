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
      String stringToWrite = str.substring(off, off + len);
      write(stringToWrite);
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
      if (off + len > cbuf.length) {
         return;
      }
      String stringToWrite = "";
      for (int i = off; i < off + len; i++) {
         stringToWrite += cbuf[i];
      }
      write(stringToWrite);
   }

   @Override
   public void write(int c) throws IOException {
      c &= 0x0000ffff;
      String stringToWrite = "";
      stringToWrite += (char) c;
      write(stringToWrite);
   }

   public void write(char c) throws IOException {
      write((int) c);
   }

   @Override
   public void write(String str) throws IOException {
      String stringToWrite = str.toUpperCase();
      this.out.write(stringToWrite);
   }

   @Override
   public void write(char[] chrArray) throws IOException {
      String stringToWrite = "";
      for (int i = 0; i < chrArray.length; i++) {
         stringToWrite += chrArray[i];
      }
      write(stringToWrite);
   }

}
