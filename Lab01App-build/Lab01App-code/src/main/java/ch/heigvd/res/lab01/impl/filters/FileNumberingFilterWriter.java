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

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  boolean beginning = true;
  int lineCounter = 0;
  int previous = 'a';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  /**
   * use write(int c) on the concerned caracters of str
   * 
   * @param str string we want to write with this decorator
   * @param off start index where we want to apply the transformation
   * @param len numbers of caracters we want to apply the transformation
   * @throws IOException possible IOException as we use write method
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  /**
   * use write(int c) on the concerned caracters of cbuf
   * 
   * @param cbuf array of char we want to write with this decorator
   * @param off start index where we want to apply the transformation
   * @param len numbers of caracters we want to apply the transformation
   * @throws IOException possible IOException as we use write method
   */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for (int i = off; i < len + off && i < cbuf.length; i++) {
        write(cbuf[i]);
     }
  }

  /**
   * write the c caracter but write "n\t" with n the number of lines yet writen 
   * for each new line
   * 
   * @param c the next caracter we are writting
   * @throws IOException possible IOException as we use write method
   */
  @Override
  public void write(int c) throws IOException {
     // it's the very first caracter we write, then it's a new line
     if(beginning) {
        printLineNumber();
        beginning = false;
     }
     
     // the line separator is \r only we write the new line number
     if(previous == '\r' && c != '\n') {
        printLineNumber();
     }
     
     //we write the current caracter
     super.write(c);
     
     // the line separator is \n or \r\n then we write the new line number
     if(c == '\n') {
        printLineNumber();
     }
     previous = c;
  }
  
  /**
   * write the new line number with the format specified by the Junit tests
   * @throws IOException possible IOException as we use write method
   */
  private void printLineNumber() throws IOException {
     lineCounter++;
     String charNumber = String.valueOf(lineCounter);
     super.write(charNumber, 0, charNumber.length());
     super.write('\t');
  }

}
