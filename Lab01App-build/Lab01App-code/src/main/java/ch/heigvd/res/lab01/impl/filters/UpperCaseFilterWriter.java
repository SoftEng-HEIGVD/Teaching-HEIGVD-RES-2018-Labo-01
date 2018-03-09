package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti modify by : Olivier Kopp
 */
public class UpperCaseFilterWriter extends FilterWriter {

   public UpperCaseFilterWriter(Writer wrappedWriter) {
      super(wrappedWriter);
   }

   /**
    * write the substring from off to off + len only if off + len is lesser than
    * the string length
    *
    * @param str input string
    * @param off start of the substring
    * @param len length of the substring
    * @throws IOException
    */
   @Override
   public void write(String str, int off, int len) throws IOException {
      if (off + len > str.length()) {
         return;
      }
      String stringToWrite = str.substring(off, off + len);
      out.write(stringToWrite.toUpperCase());
   }

   /**
    * simply call the write(String, int, int) method by converting the char
    * array in String Does nothing if off + len is greater than the array length
    *
    * @param chrArray
    * @param off start of the substring
    * @param len length of the substring
    * @throws IOException
    */
   @Override
   public void write(char[] chrArray, int off, int len) throws IOException {
      if (off + len > chrArray.length) {
         return;
      }
      String stringToWrite = new String(chrArray);
      write(stringToWrite, off, len);
   }

   /**
    * write the unicode value of the parameter
    *
    * @param c
    * @throws IOException
    */
   @Override
   public void write(int c) throws IOException {
      //avoid error by ignoring most significant bit of the integer
      c &= 0x0000ffff;
      String stringToWrite = "";
      stringToWrite += (char) c;
      out.write(stringToWrite.toUpperCase());
   }

}
