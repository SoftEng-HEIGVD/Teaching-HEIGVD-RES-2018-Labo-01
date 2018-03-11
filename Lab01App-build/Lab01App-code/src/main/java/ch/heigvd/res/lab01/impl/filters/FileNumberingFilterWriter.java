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
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private StringBuilder strBuilder;
  private int lineNb = 1;
  private char prevChar = 'a';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    strBuilder = new StringBuilder(); // flush
    String correctLengthString = str.substring(off, off + len); // string to write

    if (lineNb == 1) {
      strBuilder.append(Integer.toString(lineNb++) + '\t'); // init first line
    }

    for (int i = 0; i < correctLengthString.length(); i++) {
      char currChar = correctLengthString.charAt(i);

      switch (currChar) {
        case '\n': // new line
          strBuilder.append(currChar + Integer.toString(lineNb++) + '\t');
          break;
        case '\r':
          if (i + 1 != correctLengthString.length()) { // not last char
            if (correctLengthString.charAt(i + 1) == '\n') { // next char is \n -> new line Windows
              strBuilder.append("\r\n" + Integer.toString(lineNb++) + '\t');
              i++; // \n handled
            } else { //next char is \n -> new line MAC OS9
              strBuilder.append("\r" + Integer.toString(lineNb++) + '\t');
            }
          } else { // \r last char -> new line MAC OS9
            strBuilder.append("\r" + Integer.toString(lineNb++) + '\t');
          }
          break;
        default:
          strBuilder.append(currChar);
          break;
      }
    }
    String result = strBuilder.toString();
    super.write(result, 0, result.length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    strBuilder = new StringBuilder();
    for (int i = off; i < off + len; i++) {
      strBuilder.append(cbuf[i]);
    }
    write(strBuilder.toString(), 0, len);
  }

  @Override
  public void write(int c) throws IOException { //doesn't work for mac os9
    char c1 = (char) c;
    if (lineNb == 1) { // first line
      super.write(Integer.toString(lineNb++) + '\t', 0, 2);
    }

    if (c1 == '\n') {
      super.write(c1 + Integer.toString(lineNb++) + '\t', 0, 3);
    } else {
      super.write(c1);
    }
  }
}
