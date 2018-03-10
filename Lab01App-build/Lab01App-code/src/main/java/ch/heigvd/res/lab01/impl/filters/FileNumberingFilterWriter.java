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
  private int noLine = 1;
  private Boolean wasNumbered = false;
  private Boolean wasR_EOL = false;
  private final String TABULATION = Character.toString('\t');

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
     String[] tmp;

     StringBuilder strWellFormed = new StringBuilder();
      
     if(!str.isEmpty()){
        if(!wasNumbered){
            strWellFormed.append(addNumber());
            wasNumbered = !wasNumbered;
        }
        
        tmp = Utils.getNextLine(str.substring(off, off + len));
        
        while(!tmp[0].isEmpty()){
                       
            strWellFormed.append(tmp[0]);
            strWellFormed.append(addNumber());
            
            tmp = Utils.getNextLine(tmp[1]);
        }
        
        strWellFormed.append(tmp[1]);
        out.write(strWellFormed.toString());
     }
 

  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    this.write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {

    if(!wasNumbered){
        out.write(addNumber());
        wasNumbered = !wasNumbered;
    }
    
    switch (c) {
        case '\r':
            wasR_EOL = !wasR_EOL;
            out.write(c);
            break;
        case '\n':
            wasR_EOL = false;
            out.write(c);
            out.write(addNumber());
            break;
        default:
            if(wasR_EOL){
                wasR_EOL = !wasR_EOL;
                out.write(addNumber());
            }
            out.write(c);
            break;
    }
  }
  
  private String addNumber(){
      StringBuilder tmp = new StringBuilder(Integer.toString(noLine));
      tmp.append(TABULATION);      
      noLine++;
      return tmp.toString();
    }  
}
