package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.transformers.CompleteFileTransformer;
import ch.heigvd.res.lab01.impl.transformers.FileTransformer;
import com.sun.org.apache.xpath.internal.functions.FuncSubstring;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;
import java.util.regex.Matcher;
/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 * @author Patrick Neto
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int cntLines = 1;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      for(int i = off; i < off + len; i++){
          this.write((int)cbuf[i]);
      }
  }

  @Override
  public void write(int c) throws IOException{
      char currentChar;
      if(c >= 0 && c <= 9){
          currentChar = Character.forDigit(c, 10);
      }else{
          currentChar = (char)c;
      }

      //if this is the beginning of the first line
      if(cntLines == 1){
          addLineNumber();
      }

      //Detects '\n' or '\r\n' or '\r'. If the char sequences are found, add the line number
      if(currentChar == '\n'){
          out.write(currentChar);
          addLineNumber();
      }else if(out.toString().endsWith("\r") && currentChar != '\n'){
          addLineNumber();
          out.write(currentChar);
      }else{
          out.write(currentChar);
      }

  }

  private void addLineNumber() throws IOException {
      out.write(String.valueOf(cntLines++) + '\t');
  }
}
