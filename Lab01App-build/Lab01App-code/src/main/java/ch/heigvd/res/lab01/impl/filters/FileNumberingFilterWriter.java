package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;
import ch.heigvd.res.lab01.impl.Utils;
import javassist.bytecode.analysis.Util;

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
  private int numberLine = 1;
  private boolean backR = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = 0; i < len; i++)
      write(cbuf[i + off]);
  }

  @Override
  public void write(int c) throws IOException {
    //If this line is the first line or is a non empty line print the line number
    if(numberLine == 1 || c != '\n' && backR) out.write(numberLine++ + "\t");
    out.write(c);

    if(c == '\n') out.write(numberLine++ + "\t");

    backR = c == '\r';
  }

}
