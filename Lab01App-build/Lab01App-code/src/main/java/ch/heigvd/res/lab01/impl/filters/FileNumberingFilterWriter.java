package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

import static ch.heigvd.res.lab01.impl.Utils.getNextLine;

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
  private static int lineNumber = 1;
  private static boolean firstLineWritten;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    lineNumber = 1;
    firstLineWritten = false;

  }

/*
  public String getLineSeparator(String str){
      String separator;
      if(str.charAt(str.length()-2)== '\r'){
          separator = "\r\n";
      }else{
          separator = String.valueOf(str.charAt(str.length()-1));
      }
      return separator;
  }
*/

  @Override
  public void write(String str, int off, int len) throws IOException {
      String sub = "";

      //String separator = getLineSeparator(lines[0]);
    if((off+len) <= str.length()){
        String[] line = getNextLine(str.substring(off, off+len));
        if(!firstLineWritten)
        {
            sub += String.valueOf(lineNumber);
            sub += '\t';
            firstLineWritten = true;
        }


        while(!line[0].isEmpty()){
            sub += line[0];
            //sub += '\r';
            sub += String.valueOf(++lineNumber);
            sub += '\t';
            line = getNextLine(line[1]);
        }
        sub += line[1];
        out.write(sub);

    }
  }



  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {

      {
          out.write(String.valueOf(lineNumber));
          out.write('\t');
      }
      out.write((char)c);
  }

}
