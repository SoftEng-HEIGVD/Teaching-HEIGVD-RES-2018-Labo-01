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
  private static boolean isBackslashR;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    lineNumber = 1;
    firstLineWritten = false;
    isBackslashR = false;

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
    String str = "";
    for(char c : cbuf){
        str += String.valueOf(c);
    }
    write(str, off, len);
  }

  @Override
  public void write(int c) throws IOException {
      // if the first line wasn't numbered
      if(!firstLineWritten)
      {
          // numbering the line
          out.write(String.valueOf(lineNumber));
          out.write('\t');
          firstLineWritten = true;
      }

      // if current character is the '\r' separator
      if(c == '\r'){
          // we signal it
          isBackslashR = true;
          // we write the character
          out.write(c);

      }else if(c == '\n'){ // if current character is the '\n' separator
          out.write(c); //we write it
          // as it will always be either a separator or the last part of the '\r\n' separator,
          // we print the number and tabulation
          out.write(String.valueOf(++lineNumber));
          out.write('\t');
          // new line, we'll potentially be searching for a new '\r'
          isBackslashR = false;
      }else{ // if it's a commom character
          if(!isBackslashR){ // if there was no '\r' before we print it
              out.write(c);
          }else{ //if there was a '\r' before, we need to print number and tabulation, and then the character
              out.write(String.valueOf(++lineNumber));
              out.write('\t');
              out.write(c);
              // new line, we'll potentially be searching for a new '\r'
              isBackslashR = false;
          }
      }
  }

}
