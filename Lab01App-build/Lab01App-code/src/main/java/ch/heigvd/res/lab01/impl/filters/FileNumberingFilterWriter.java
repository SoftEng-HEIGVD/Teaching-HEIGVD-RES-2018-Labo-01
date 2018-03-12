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
 * @author Walid Koubaa
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private boolean isStartLine = true;
  private int linenumber = 0;
  private boolean hasAnRSeparator = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      // we simply write in the Writer the String characters
      // from the specified offset and with the specified length
    for (int i = off; i < (off + len); ++i) {
      write(str.charAt(i));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      // we simply write in the Writer each characters of the static array
      // from the specified offset and with the specified length
      for (int i = off; i < (off + len); ++i) {
          write(cbuf[i]);
      }
  }

  @Override
  public void write(int c) throws IOException {
      // This function writes a char in the Writer.
      // It's called by the two upper functions so it can writes in the Writer
      // each characters on a line with the correct format for Linux/Windows/MacOs
      // In fact line separators are quite different for each os these Operating Systems

      // If it's the beginning of the sentence, we specified the number of it
      if (isStartLine) {
          out.write(++linenumber + "\t");
          isStartLine = false;
      }

      // Firstly we have to check the line separator for MacOS
      // This check has to be done before writting the character because the '\r'
      // is the previous character. In the case where the actual character is not a \n
      // then we need to start a new line and of course write its number
      if ((hasAnRSeparator && (c != '\n'))) {
          out.write(++linenumber + "\t");
      }

      out.write(c);

      // Secondly we have to check for the Linux and Windows separator (respectively \n and \r\n)
      if (c == '\n') {
          out.write(++linenumber + "\t");
      }

      // Here, if the argument c is equal to '\r',
      // then we set our boolean to true, toherwise it remains at false
      hasAnRSeparator = (c == '\r');

  }

}
