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

  private int nbrLine = 1;
  private boolean partOfWindowsReturn = false;
  private static final char MAC_OS = '\r';
  private static final char LINUX = '\n';
  private static final String TABULATION = "\t";

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

      //we have just to call the function write withe the char[] as parameter
      this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

      //we use the function write(int c)
      for (int i = 0; i < len; ++i){
          this.write(cbuf[i+off]);
      }
  }

  @Override
  public void write(int c) throws IOException {

      //write the first number
      if(nbrLine == 1){
          out.write(nbrLine++ + TABULATION);
      }

      //we have to check if the last char was a windows return or not
      if(partOfWindowsReturn){
          if(c != LINUX){
              partOfWindowsReturn = false;
              out.write(nbrLine++ + TABULATION);
          }
          else{
              partOfWindowsReturn = false;
              out.write(c);
              out.write(nbrLine++ + TABULATION);
              return;
          }
      }

      out.write(c);

      if(c == MAC_OS){
          partOfWindowsReturn = true;
      }
      else if(c == LINUX){
          out.write(nbrLine++ + TABULATION);
      }
  }

}
