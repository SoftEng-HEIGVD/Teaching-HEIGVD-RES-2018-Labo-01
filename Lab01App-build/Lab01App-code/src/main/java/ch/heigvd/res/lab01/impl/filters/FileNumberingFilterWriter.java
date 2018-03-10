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
 * @author Labinot Rashiti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int nbLines = 0;
  private char lastChar;
  private boolean beginLine = true;
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      char[] arrayStr = str.toCharArray();
      write(arrayStr, off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      for (int i = off; i < off + len; ++i) {
         write(cbuf[i]);
      }
  }

  @Override
  public void write(int c) throws IOException {
     char input = (char) c;
    
     // Check if it's the first line
     if (beginLine) {
        out.write(++nbLines + "\t"); // Tag the beginning of the line
        beginLine = false;
     }
     
     // Check last character is a line separator
     if (lastChar == '\r') {
        ++nbLines;
        
        // Check if there is again a line separator
        if (input == '\n') {
           out.write("\n" + nbLines + "\t");
           lastChar = '\n';
                    
        } else if (input != '\n') { // Check if there is no line separator
           out.write(nbLines + "\t" + input);
           lastChar = input;
        }
       
       // Check if new line separator
     } else if (input == '\n') {
        ++nbLines;
        out.write("\n" + nbLines + "\t");
        lastChar = input;
        
     } else {          
        // Normal Input
        out.write(input);
        lastChar = input;
     }


      // throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
