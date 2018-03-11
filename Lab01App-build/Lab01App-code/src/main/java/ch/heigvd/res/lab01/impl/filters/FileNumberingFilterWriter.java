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
  private static int counter;
  private static boolean gotBackslashR;
  private static boolean startNewLine;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    counter = 1;
    gotBackslashR = false;
    startNewLine = true;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    StringBuilder sb = new StringBuilder();
    String[] result;
    String nextLine = str.substring(off, off+len > str.length() ? str.length() : off + len);

    if (1 == counter) {
      sb.append(counter++);
      sb.append('\t');
    }
    while (!((result = Utils.getNextLine(nextLine))[0]).isEmpty()) {
      sb.append(result[0]);
      sb.append(counter++);
      sb.append('\t');
      nextLine = result[1];
    }
    if (!result[1].isEmpty()) {
      sb.append(result[1]);
      if (!result[0].isEmpty()) {
        sb.append(counter++);
        sb.append('\t');
      }
    }

    super.write(sb.toString(), 0, sb.length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    StringBuilder strSB = new StringBuilder();
    for (char c : cbuf)
      strSB.append(c);
    String str = strSB.toString();
    write(str, off, len);
  }

  @Override
  public void write(int c) throws IOException {

    if (startNewLine && !(c == '\n' && gotBackslashR)) {
      StringBuilder sb = new StringBuilder();
      sb.append(counter++);
      sb.append('\t');
      super.write(sb.toString(), 0, sb.length());
      startNewLine = false;
    }

    super.write(c);

    if ('\r' == c) {
      gotBackslashR = true;
      startNewLine = true;
    }
    else if ('\n' == c) {
      startNewLine = true;
      if (gotBackslashR)
        gotBackslashR = false;
    }

  }

}
