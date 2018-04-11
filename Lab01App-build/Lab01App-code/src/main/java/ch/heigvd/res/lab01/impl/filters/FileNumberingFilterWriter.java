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

  private int i = 1;
  private boolean first = true;
  private int last = 0;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");
    String line = str.substring(off, off + len);
    if (i == 1)
      out.write(i++ + "\t");

    while (line.length() != 0) {
      String part[] = Utils.getNextLine(line);
      if( part[0].length() == 0 ) {
        out.write(part[1]);
        break;
      }
      else {
        out.write(part[0] + i++ + "\t");
        line = part[1];
      }
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    throw new UnsupportedOperationException("The student will not implement this method, because" +
            "it's not needed lol.");
  }

  @Override
  public void write(int c) throws IOException {
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");
    if (first) {
      out.write(i++ + "\t");
      first = false;
    }
    if (last == '\r' && c != '\n' )
      out.write(i++ + "\t" + c);
    else if( c == '\n')
      out.write("\n" + i++ + "\t");
    else
      out.write(c);

    last = c;
  }

}
