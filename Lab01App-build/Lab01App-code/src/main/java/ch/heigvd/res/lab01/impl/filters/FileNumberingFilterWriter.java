package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
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
  private static String tab = "\t";
  //contains the current number of lines for our object
  private int line = 0;
  private char prevChar = ' ';



  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    String sep = Utils.getSeparator(str, off, len);/*System.lineSeparator(); */
    StringBuilder sb = new StringBuilder( str /*str.substring(off, off+len-1)*/ );


    int indexSep = 0;
    //nb lines wrote this far
    int nbLines = 0;

    //print 1\t at start
    if(line == 0) {
      sb.insert(indexSep + off, ++line + tab);
      nbLines++;
    }

    //We iterate if we have a return character
    if( !sep.equals("\0") ) {

      while (indexSep != -1 && indexSep < len) {
        //We search the first occurence of sep in our string, starting at
        // offset + index of the previous match for sep + length of our previously added separator.
        indexSep = sb.indexOf(sep, off + indexSep + sep.length());

        if (indexSep != -1) {

          //we insert our addition right after our separator.
          sb.insert(indexSep + sep.length(), ++line + tab);
          nbLines++;
        }
      }
    }


    super.write(sb.toString(), off, len + (nbLines*(line + tab).length() ));


  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    this.write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
    char ch = (char)c;

    /*
     * If current char == \n, add line number AFTER char
     * Then if not, we have 3 possibilities:
     * 1. We're at the first line, we add line number BEFORE char
     * 2. We're after an \r, we add line number       BEFORE char
     * 3. Neither of 1 or 2, we just print the char
     */
    if(ch == '\n' ){
      super.write(ch);
      super.write((++line + tab).toCharArray());
    }
    else if( line == 0 || prevChar == '\r' ){
      super.write((++line + tab).toCharArray());
      super.write(ch);

    }else{
      super.write(ch);
    }
    prevChar = ch;
  }

}
