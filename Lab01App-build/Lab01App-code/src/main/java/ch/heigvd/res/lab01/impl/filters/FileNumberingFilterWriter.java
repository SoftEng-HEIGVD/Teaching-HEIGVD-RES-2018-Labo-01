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
  private int lineNumber;
  private String output;
  private char lastChar;
  
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
    
    lineNumber = 1;
    
    // We initialise the output String with the first line number and tabulation.
    output = lineNumber++ + "\t";
  }
  
  @Override
  public void write(String str, int off, int len) throws IOException {
    // We initialise the array string which will contain the line to write in first position
    // and the remaining text in second position
    String[] fLineAndRemaining = {"", str.substring(off, off+len)};
    
    // If the output already has a line, we reset it to avoid rewritting it.
    // But only if the length is greater than 2, meaning the output contains
    // more than just the number and the tabulation.
    if (output.length() > 2) {
      output = "";
    }
    
    do {
      fLineAndRemaining = Utils.getNextLine(fLineAndRemaining[1]);
      
      // If there isn't a line with ending character {"\r", "\n", "\r\n"}
      if (fLineAndRemaining[0].isEmpty()) {
        // We add the remaining String in the output String and break out of the loop.
        output += fLineAndRemaining[1];
        break;
      } else {
        // Otherwise, we add the line, the next line number and the tabulation in the output String.
        output += fLineAndRemaining[0] + lineNumber++ + "\t";
      }
    } while (!fLineAndRemaining[1].isEmpty()); // While the remaining text isn't empty.
    
    out.write(output);
  }
  
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    write(String.valueOf(cbuf), off, len);
  }
  
  @Override
  public void write(int c) throws IOException {
    // If the last character is "\r" and the current one isn't a "\n"
    if (lastChar == '\r' && (char) c != '\n') {
      // We add the number and the tabulation before adding the character to the output.
      output += lineNumber++ + "\t" + (char) c;
    } else if ((char) c != '\n') {  // If the last character wasn't "\r" and the actual one isn't "\n"
      // We add the character to the output string
      output += (char) c;
    } else {
      // Otherwise, we add the ending line character and then the line number and the tabulation for next line.
      output += "\n" + lineNumber++ + "\t";
    }
    
    out.write(output);
    
    // We reset the output string and we save the current character as lastChar for next time.
    output = "";
    lastChar = (char) c;
  }
  
}
