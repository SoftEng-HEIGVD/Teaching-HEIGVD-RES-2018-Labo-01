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
 */
public class FileNumberingFilterWriter extends FilterWriter {

  // TODO use the nextLine() function instead
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private String lineSeparator;
  private Integer lineNumber;
  boolean writeLineNumber;
  int lastCharWritten;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    lineSeparator = new String();
    lineNumber =  new Integer(1);
    writeLineNumber = true;
    lastCharWritten = -1;
  }

  /**
   * Finds the first occurrence of the valid line separatpr
   * @param str The string in which we look for a separator
   * @return The separator
   *         If any separator is found in the string, returns an empty string
   */
  private String lineSeparator(String str) {
    if (str.indexOf("\r\n") != -1) {
      return "\r\n";
    }

    if (str.indexOf("\n") != -1) {
      return "\n";
    }

    if (str.indexOf("\r") != -1) {
      return "\r";
    }

    //no separator found in str
    return "";
  }


  @Override
  public void write(String str, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");

    StringBuilder sb =  new StringBuilder(str);

    // Detect a separator in the string if it is not set yet
    lineSeparator = lineSeparator(str);

    // If the current string is the first one to write,
    // we prefix it with the line number and s tab character
    if(writeLineNumber) {
      sb.insert(off,lineNumber + "\t");
      writeLineNumber = false;
    }

    int curSeparatorIndex;
    int prevSeparatorIndex = -1;
    // Number of characters from the initial string that have been formatted
    // and put to the resulting StringBuilder (sb)
    int nbCharsTreated = 0;

    // Look for the next lineSeparator
    while ((lineSeparator.isEmpty() != true)
            && ((curSeparatorIndex = sb.indexOf(lineSeparator, prevSeparatorIndex+1)) != -1)
            && (nbCharsTreated < len)) {

      lineNumber++;
      sb.insert(curSeparatorIndex + lineSeparator.length(), lineNumber + "\t");
      nbCharsTreated += curSeparatorIndex - prevSeparatorIndex - 1;
      prevSeparatorIndex = curSeparatorIndex;

    }

    String result = sb.toString();
    int newLength = result.length() - str.length() + len;
    // Store the last char written
    lastCharWritten = result.charAt(result.length()-1);
    super.write(result, off, newLength);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    super.write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");

    // If the current chat is the first one to write, prefix it with the line number and a tab chat
    if(writeLineNumber) {
      super.write(lineNumber + "\t", 0,2);
      writeLineNumber = false;
    }

    if (lastCharWritten == '\r') {
      lineNumber++;
      lastCharWritten = c;
      String toWrite;
      if (c == '\n') {
        // If '\r' is preceded by '\n', we consider that "\r\n" is a line separator
        toWrite = "\r\n" + lineNumber + "\t";
      } else {
        // If '\r' is not followed by '\n', we consider it as a line separator
        toWrite = "\r" + lineNumber + "\t" + Character.toString((char)c);
      }
      super.write(toWrite, 0, toWrite.length());
    } else if(c == '\r') {
      // Since we do not know if '\r' char is a line separator or
      // a part of "\r\n" line separator, we simply store it in lastCharWritten
      lastCharWritten = '\r';
    } else if ( c == '\n') {
      // Since '\n' is not preceded by '\r', we can assume that it is a
      // line separator and pass to the next line
      lineNumber++;
      String toWrite = Character.toString((char)c) + lineNumber + "\t";
      lastCharWritten = c;
      super.write(toWrite, 0, toWrite.length());
    } else {
      lastCharWritten = c;
      super.write(c);
    }

  }

}
