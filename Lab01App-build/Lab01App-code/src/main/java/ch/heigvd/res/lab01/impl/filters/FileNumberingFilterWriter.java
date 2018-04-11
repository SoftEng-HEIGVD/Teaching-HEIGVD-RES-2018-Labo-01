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
 * @author GuillaumeBlanco
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {super(out);}

  private int lineNumber = 1;
  private int charBefore = ' ';

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(),off,len); // We do all the treatment in write(int c) (so we pass by write(char[] cbuf ...))
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < (len + off); ++i){
      write(cbuf[i]); // We do all the treatment in write(int c)
    }
  }

  @Override
  public void write(int c) throws IOException {

    if (lineNumber == 1) { // first line of the file
      out.write(lineNumber++ + "\t");
    }

    if (charBefore == '\r' && c !='\n') // treat the case of \r\n
      out.write(lineNumber++ +"\t");

    out.write(c);

    if (c == '\n') { // if we have \n we need a new line
        out.write( lineNumber++ + "\t");
    }

    charBefore = c; // keep a track of previous character (to the case of \r\n)
  }

}
