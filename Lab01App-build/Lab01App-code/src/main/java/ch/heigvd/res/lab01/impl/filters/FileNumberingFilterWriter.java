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
 * @contributor Nair Alic
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  private Boolean flag = true;
  private int lineNumber = 1;
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
     // here we call the char[] function
     this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // here we call the int function for each charachter
     for(int i = 0; i < len; i++){
       this.write(cbuf[i + off]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if(lineNumber == 1) { // if it's the first line
       newLine();
    }
    
    if(flag && c != '\n'){
       newLine();
    }
    
    super.write(c);
    if (c == '\r') {
       flag = true;
    }
    
    if(c == '\n') {
       newLine();
    }
  }
  
  //function used to write the line number and tab before line
  public void newLine() throws IOException { 
     out.write(lineNumber++ + "\t");
     flag = false;
  }

}
