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

   private char lastChar = ' ';
   private int lastLineNumber = 0;

   public FileNumberingFilterWriter(Writer out) {
      super(out);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {
      write(str.toCharArray(), off, len);
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
      int lastCharIndex = off + len;

      for(int i = off; i < lastCharIndex; i++)
         write(cbuf[i]);
   }

   @Override
   public void write(int c) throws IOException {
      // Constant for the End Of Line (EOF) char on different OS
      final char EOF_MAC  = '\r';
      final char EOF_UNIX = '\n';

      if(lastLineNumber == 0)
         out.write(addLineNumber());

      if(lastChar == EOF_MAC && c != EOF_UNIX)
         out.write(addLineNumber());

      out.write(c);

      if(c == EOF_UNIX)
         out.write(addLineNumber());

      lastChar = (char)c;
   }

   private String addLineNumber(){
      return Integer.toString(++lastLineNumber) + '\t';
   }

}
