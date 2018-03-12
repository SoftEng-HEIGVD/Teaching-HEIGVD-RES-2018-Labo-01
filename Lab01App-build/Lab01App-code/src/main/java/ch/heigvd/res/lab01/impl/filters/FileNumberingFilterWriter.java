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
  
  private int lineNumber = 0;
  private boolean beginning = true; // true if we are at the beginning of the file
  private int prevChar; // previous character treated

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    
         /* if the parameters would throw an ArrayOutOfBoundsException,
       * then simply write the whole String */
      if(off < 0 || len < 0 || (off + len) < 0 || (off + len ) > str.length()){
          off = 0;
          len = str.length();
      }
    
    for (int i = 0; i < len; i++){
          write(str.charAt(i + off));
      } 
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    
      write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
    
    if(beginning) {
        writeLineNumber();
        beginning = false;
    }
    
    // when return line is \n or \r\n
    if (c == '\n') {
        super.write(c);
        writeLineNumber();

    // MacOS 9 case, where return line is \r
    } else if (prevChar == '\r'){

        writeLineNumber();
        super.write(c);

    } else {
        super.write(c);
    }
    
    prevChar = c;
  }
  
  // this methode writes the line number followed by a tab (\t)
  private void writeLineNumber() throws IOException{

    super.out.write(++lineNumber + "\t");
  
  }

}
