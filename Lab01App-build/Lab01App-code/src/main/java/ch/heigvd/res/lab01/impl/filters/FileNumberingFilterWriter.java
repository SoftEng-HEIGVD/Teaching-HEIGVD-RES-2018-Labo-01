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
 *
 * @modified by Lionel Nanchen
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  private int numberOfLines;
  private char lastCharacter;
  private boolean newLine;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    numberOfLines = 0;
    lastCharacter = '\0';
    newLine = true;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    for(int i = off; i < (off + len); ++i) {
      write(str.charAt(i));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    String str = new String(cbuf);
    write(str, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    if (newLine) {
      out.write(numberOfLines++ + "\t");
      newLine = false;
    }

    if (lastCharacter == '\r' && c != '\n') {
      out.write(numberOfLines++ + "\t");
    }

    out.write(c);

    if(c == '\n') {
      out.write(numberOfLines++ + "\t");
    }

    lastCharacter = (char)c;

  }

}
