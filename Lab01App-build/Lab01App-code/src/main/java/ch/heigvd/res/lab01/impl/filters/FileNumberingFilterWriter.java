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
  private int counter;           // Counter for keeping track of line number
  private boolean gotBackslashR; // Flag if we meet a \r
  private boolean startNewLine;  // Flag if we need to start a new line

  // We initialise the attribute in the constructor since we will create a new instance for every new writing
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
    // We consider the portion of the string we will deal with
    String nextLine = str.substring(off, off+len > str.length() ? str.length() : off + len);

    // If we are at the beginning of the writing, we print the first counter value and a tab
    if (1 == counter) {
      sb.append(counter++);
      sb.append('\t');
    }

    // We iterate over the string to print line by line and add the counter value for the following line
    while (!((result = Utils.getNextLine(nextLine))[0]).isEmpty()) {
      sb.append(result[0]);
      sb.append(counter++);
      sb.append('\t');
      nextLine = result[1];
    }

    // If we have something last to print, we print it.
    if (!result[1].isEmpty()) {
      sb.append(result[1]);
      if (!result[0].isEmpty()) { // If the last line contain a carriage return (\r, \n or \r\n)
        sb.append(counter++);
        sb.append('\t');
      }
    }

    super.write(sb.toString(), 0, sb.length());
  }

  // In order to avoid code duplication, we will use the previous method.
  // We build a string from the char[] with StringBuilder.
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

    // If we have to start a line we add the counter value and a tab
    // If the previous char was \r and this one is a \n, we want to print the \n and start a new line after
    if (startNewLine && !(c == '\n' && gotBackslashR)) {
      StringBuilder sb = new StringBuilder();
      sb.append(counter++);
      sb.append('\t');
      super.write(sb.toString(), 0, sb.length());
      startNewLine = false;
    }

    super.write(c);

    // For \r and \n we have to start a new line before the next char so we set the corresponding flag to true
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
