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

  private int noLigne = 0;
  
  private char ancien = 'a';
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
     String subString = str.substring(off,off+len);
     if(subString.length() == 0) {
         return;
     }
     
     String s = "";
     if(noLigne == 0)
         s += Integer.toString(++noLigne) + "\t";
     
     String[] strings = Utils.getNextLine(subString);
     
     while(strings[0].length() != 0){
         s += strings[0] + Integer.toString(++noLigne) + "\t";
         strings = Utils.getNextLine(strings[1]);
     }
     
     s += strings[1];
     
     super.write(s, 0, s.length());    
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      String str = cbuf.toString();
      
      write(str, off, len);
      
  }

  @Override
  public void write(int c) throws IOException {
      if(noLigne == 0){
          super.write(Integer.toString(++noLigne));
          super.write('\t');
      }
      
      if(ancien == '\r' && c != '\n'){
          super.write(Integer.toString(++noLigne));
          super.write('\t');
      }
      super.write(c);
      
      if(c == '\n'){
          super.write(Integer.toString(++noLigne));
          super.write('\t');
      }
      
      ancien = (char)c;
      
  }

}
