package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

   private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

   private int lineNumber = 1;
   private boolean checkNewLine = false;

   public FileNumberingFilterWriter(Writer out) {
      super(out);
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
   public void write(char[] chrArray) throws IOException {
      String stringToWrite = "";
      for (int i = 0; i < chrArray.length; i++) {
         stringToWrite += chrArray[i];
      }
      write(stringToWrite);
   }

   @Override
   public void write(String str) throws IOException {
      String stringToWrite = "";
      if (lineNumber == 1) {
         stringToWrite += String.valueOf(lineNumber++) + "\t";
      }
      int i;
      for (i = 0; i < str.length(); i++) {
         if (checkNewLine) {
            if (str.charAt(i) == '\n') {
               stringToWrite += "\r" + str.charAt(i);
               stringToWrite += String.valueOf(lineNumber++) + "\t";
               checkNewLine = false;
            } else if (str.charAt(i) == '\r') {
               stringToWrite += str.charAt(i);
               stringToWrite += String.valueOf(lineNumber++) + "\t";
            } else {
               stringToWrite += "\r";
               stringToWrite += String.valueOf(lineNumber++) + "\t";
               stringToWrite += str.charAt(i);
               checkNewLine = false;
            }
         } else {
            if (str.charAt(i) == '\n') {
               stringToWrite += str.charAt(i);
               stringToWrite += String.valueOf(lineNumber++) + "\t";
            } else if (str.charAt(i) == '\r') {
               checkNewLine = true;
            } else {
               stringToWrite += str.charAt(i);
            }

         }
      }

      this.out.write(stringToWrite);
   }

}
