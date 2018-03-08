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
 * @author Olivier Liechti modify by : Olivier Kopp
 */
public class FileNumberingFilterWriter extends FilterWriter {

   private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

   //number to print at the beginning of each line
   private int lineNumber = 1;
   /*this flag is used if a \r in found at the end of the buffer and indicate that we need to check if a
   \n is present in the beginning of the next buffer to analyze*/
   private boolean checkNewLine = false;

   public FileNumberingFilterWriter(Writer out) {
      super(out);
   }

   @Override
   /**
    * This method simply call the write(String) method with a substring
    */
   public void write(String str, int off, int len) throws IOException {
      String stringToWrite = str.substring(off, off + len);
      write(stringToWrite);
   }

   @Override
   /**
    * This method simply call the write(String) method with a substring
    */
   public void write(char[] chrArray, int off, int len) throws IOException {
      if (off + len > chrArray.length) {
         return;
      }
      String stringToWrite = new String(chrArray);
      stringToWrite = stringToWrite.substring(off, off + len);
      write(stringToWrite);
   }

   @Override
   /**
    * this method keep only the 16 less significant bit of the argument to avoid
    * error and call the write(String) method with a string that contain the
    * unicode value of the argument
    */
   public void write(int c) throws IOException {
      c &= 0x0000ffff;
      String stringToWrite = "";
      stringToWrite += (char) c;
      write(stringToWrite);
   }

   /**
    * apply the numbering filter to an input string
    *
    * @param str input string
    * @throws IOException
    */
   @Override
   public void write(String str) throws IOException {
      String stringToWrite = "";
      //we write the first line number if its the first time that this method is call
      if (lineNumber == 1) {
         stringToWrite += String.valueOf(lineNumber++) + "\t";
      }
      for (int i = 0; i < str.length(); i++) {
         //if we need to check the first character
         if (checkNewLine) {
            //if we find a \n, we write \r\n, set the flag to false and write the line number
            if (str.charAt(i) == '\n') {
               stringToWrite += "\r" + str.charAt(i);
               stringToWrite += String.valueOf(lineNumber++) + "\t";
               checkNewLine = false;
            } //if we find another \r we write the previous \r, the line number but we keep the flag to true
            else if (str.charAt(i) == '\r') {
               stringToWrite += str.charAt(i);
               stringToWrite += String.valueOf(lineNumber++) + "\t";
            } //if we find another character, we append the \r, the line number, and the character to the final string
            //and we set the flag to false
            else {
               stringToWrite += "\r";
               stringToWrite += String.valueOf(lineNumber++) + "\t";
               stringToWrite += str.charAt(i);
               checkNewLine = false;
            }
         } //if we don't need to check the first character
         else {
            //if we find a \n, we aooend it and the line number to the final string
            if (str.charAt(i) == '\n') {
               stringToWrite += str.charAt(i);
               stringToWrite += String.valueOf(lineNumber++) + "\t";
            } //if we find a \r, we set the flag to true
            else if (str.charAt(i) == '\r') {
               checkNewLine = true;
            } //else, we append the character to the final string
            else {
               stringToWrite += str.charAt(i);
            }

         }
      }

      this.out.write(stringToWrite);
   }

}
