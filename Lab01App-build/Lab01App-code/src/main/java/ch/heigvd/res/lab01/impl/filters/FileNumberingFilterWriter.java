package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

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

   private int lastLineNumber = 0;

   public FileNumberingFilterWriter(Writer out) {
      super(out);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {
      String[] nextLine = Utils.getNextLine(str.substring(off, off + len));

      if(lastLineNumber == 0)
         out.write(addLineNumber());

      if(nextLine[0].equals("")) {
         out.write(str, off, len);
      } else {
         out.write(nextLine[0] + addLineNumber());

         this.write(nextLine[1], 0, nextLine[1].length());
      }
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
      StringBuilder textToNumeroted = new StringBuilder();
      int lastCharIndex = off + len;

      for(int i = off; i < lastCharIndex; i++)
         textToNumeroted.append(cbuf[i]);

      this.write(textToNumeroted.toString(), 0, len);
   }

   @Override
   public void write(int c) throws IOException {
      this.write(Integer.toString(c), 0, 1);
   }

   private String addLineNumber(){
      return Integer.toString(++lastLineNumber) + '\t';
   }

}
