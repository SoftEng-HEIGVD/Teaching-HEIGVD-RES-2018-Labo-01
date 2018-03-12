package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

import javax.rmi.CORBA.Util;
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
 * Warning: If the last char is à \r, it won't be displayed
 *
 * @author Olivier Liechti
 * @author Jérémie Châtillon
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  private int nbLines = 1;    // count the current line
  private boolean isR;        // Needed if we have a \r\n

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; ++i)
      write(cbuf[i]);
  }

  @Override
  public void write(int c) throws IOException {


     if(nbLines == 1){
       this.out.write(Integer.toString(nbLines++));
       this.out.write("\t");
     }

     if(isR){   // If there were a \r as last char, we have to verify the \n
       if(c == '\r'){
         this.out.write(Integer.toString(nbLines++));
         this.out.write("\t");
         this.out.write(c);
         isR = true;
       }
       if(c == '\n'){
         this.out.write(c);
         this.out.write(Integer.toString(nbLines++));
         this.out.write("\t");
         isR = false;
       } else {
         this.out.write(Integer.toString(nbLines++));
         this.out.write("\t");
         this.out.write(c);
         isR = false;
       }
     } else{
       if(c == '\r'){
         this.out.write(c);
         isR = true;
       } else if(c == '\n'){
         this.out.write(c);
         this.out.write(Integer.toString(nbLines++));
         this.out.write("\t");
       } else{
         this.out.write(c);
       }
     }
  }
}
