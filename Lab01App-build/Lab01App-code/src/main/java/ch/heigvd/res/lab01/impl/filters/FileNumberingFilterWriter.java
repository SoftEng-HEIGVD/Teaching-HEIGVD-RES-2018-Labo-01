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
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {super(out);}

  private int lineNumber = 1;
  private int charBefore = ' ';

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(),off,len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < (len + off); ++i){
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {

    if (lineNumber == 1) {
      out.write(lineNumber++ + "\t");
    }

    if (charBefore == '\r' && c !='\n')
      out.write(lineNumber++ +"\t");

    if (c == '\n') {
        out.write("\n" + lineNumber++ + "\t");
    } else if (c == '\r') {
      out.write("\r");
    } else {
      out.write(c);
    }
    charBefore = c;
  }

}
