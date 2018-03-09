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
  private int lineNumber = 1;
  private boolean lastWasR = false;


  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    if(lineNumber == 1) {
      out.write("1\t");
      lineNumber++;
    }

    String[] lines = Utils.getNextLine(str);

    if(lines[0] == ""){
      out.write(str, off, len);
    } else {

      out.write(lines[0], off, lines[0].length());
      String cbuf1 = Integer.toString(lineNumber);
      out.write(cbuf1, 0, cbuf1.length());
      lineNumber++;

      char last = lines[0].charAt(lines[0].length() -1);



      char[] cbuf = new char[1];
      cbuf[0] = '\t';
      out.write(cbuf, 0, 1);

      //Appel récursif
      this.write(lines[1], 0 ,lines[1].length());
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    this.write(new String(cbuf), 0, len);

  }

  @Override
  public void write(int c) throws IOException {

    char[] cbuf = Character.toChars(c);

    this.write(cbuf, 0, cbuf.length);  }

}
