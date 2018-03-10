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

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  //Static variable

  private int cmpt = 1;
  private boolean checkLineSeparator = false;

  @Override
  public void write(String str, int off, int len) throws IOException {
    str = str.substring(off,off + len);
    write(str);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    if(off + len > cbuf.length){
      return;
    }
    String str = new String(cbuf);
    str = str.substring(off, off + len);
    write(str);
  }

  @Override
  public void write(int c) throws IOException {
    String str = new String();
    str += (char)c;
    write(str);
  }

  @Override
  public void write(String str) throws IOException{
    String lines = new String();

    if(cmpt == 1){
      lines += cmpt + "\t";
      cmpt ++;
    }
    for(int i = 0; i < str.length(); ++i){
      // it will be a end of a line
      if (str.charAt(i) == '\n') {
        lines += str.charAt(i) + String.valueOf(cmpt) + "\t";
        cmpt++;
        checkLineSeparator = false;
      } else if (checkLineSeparator) {
        lines += String.valueOf(cmpt) + "\t" + str.charAt(i);
        cmpt ++;
        checkLineSeparator = false;
      } else {
        // can be followed by a \n
        if (str.charAt(i) == '\r') {
          checkLineSeparator = true;
          lines += str.charAt(i);
        } else { // is a normal caracter
          lines += str.charAt(i);
        }
      }
    }

    this.out.write(lines);
  }

}
