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

  private int lineNbr = 1;
  private boolean firstLine = true;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    StringBuilder numberedStr = new StringBuilder();

    // substring if part of string asked
    String subStr = str.substring(off, off + len);

    // if first line we need to add an additional number at the beginning
    if(firstLine) {
      firstLine = false;
      numberedStr.append(lineNbr++).append("\t");
    }

    // getting the first line splited
    String[] splitedStr = Utils.getNextLine(subStr);

    // if string is an unique line
    if(splitedStr[1].equals("")){
      numberedStr.append(splitedStr[0]);
      numberedStr.append(lineNbr++).append("\t");
      this.out.write(numberedStr.toString(), 0, numberedStr.length());
      return;
    }

    // threat all lines but the last one
    while(!splitedStr[0].equals("")){
      numberedStr.append(splitedStr[0]);
      numberedStr.append(lineNbr++).append("\t");
      splitedStr = Utils.getNextLine(splitedStr[1]);
    }

    // appending the last line to the numbered sting
    numberedStr.append(splitedStr[1]);

    this.out.write(numberedStr.toString(), 0, numberedStr.length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // go throw array, and call
    String arrayStr = String.valueOf(cbuf);
    this.write(arrayStr, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    // if first line we need to add an additional number at the beginning
    if(firstLine) {
      firstLine = false;
      this.out.write(lineNbr++ + "\t");
    }

    this.out.write(c);

    // if end of line add new line number
    if(c == '\n'){
      this.out.write(lineNbr++ + "\t");
    }
  }

}
