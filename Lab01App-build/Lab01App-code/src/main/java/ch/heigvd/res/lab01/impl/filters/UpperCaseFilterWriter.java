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
    * simply call the write(String) methode with a substring Does nothing if off
    * + len is greater than the String length
    *
    * @param str
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
      write(stringToWrite);
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
    * call the write(String) method with a String that contain the unicaode
    * value of the parameter
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
      write(stringToWrite);
   }

   /**
    * apply an uppercase filter to a string and write it to the current writer
    * output
    *
    * @param str input string
    * @throws IOException
    */
   @Override
   public void write(String str) throws IOException {
      String stringToWrite = str.toUpperCase();
      this.out.write(stringToWrite);
   }
}
