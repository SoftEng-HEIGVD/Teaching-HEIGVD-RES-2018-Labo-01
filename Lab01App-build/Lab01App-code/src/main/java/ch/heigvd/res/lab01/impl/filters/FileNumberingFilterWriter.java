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
  private int line = 1;
  private int previous = 0;
  private static final char TAB ='\t';
  private static final char RET_M ='\r';
  private static final char RET_U ='\n';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(),off,len);
    /*
    String res = str.substring(0,off);
    char cur;
    char next;
    if(line == 1){
      res += line++;
      res += TAB;
    }
    for (int i = off; i < off + len; ++i){
      cur = str.charAt(i);
      if(i + 1 < str.length()){
        next = str.charAt(i+1);
      }
      else {
        next = '\0';
      }
      res += cur;
      if(cur == RET_U || cur == RET_M){
        if(cur == RET_M && next == RET_U){
          res += next;
          ++i;
        }
        res += line++;
        res += TAB;
      }
    }
    res += str.substring(off+len, str.length());
    super.write(res,off,len + res.length() - str.length());
    */
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; ++i) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if(line == 1){
      newLineWriter();
    }
    if(previous == RET_M && c != RET_U){
      newLineWriter();
    }
    super.write(c);

    if(c == RET_U){
      newLineWriter();
    }
    previous = c;
  }
  public void newLineWriter() throws IOException{
    super.write(String.valueOf(line), 0, String.valueOf(line).length());
    super.write(TAB);
    line++;
  }
}
