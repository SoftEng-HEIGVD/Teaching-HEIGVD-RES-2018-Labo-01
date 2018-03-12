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
  private int number;                 //The line number
  private boolean lastIsBackslashR;   //If the last char read was a \r

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    number = 1;
    lastIsBackslashR = false;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    for(int i = off; i < off + len; ++i){

      //If we are at the beginning of the string
      //and it's the first one to be write,
      //then we write the line number
      if(i == off && number == 1){
        out.write(Integer.toString(number) + '\t' + str.charAt(i));   //First numbering, then write the char
        number++;
      }

      //If there is a \n OR if there is \r (and not \r\n),
      //then we write the line number
      else if(str.charAt(i) == '\n' || (str.charAt(i) == '\r' && (i == len - 1 || (i < len - 1 && str.charAt(i + 1) != '\n')))){
        out.write(str.charAt(i) + Integer.toString(number) + '\t');   //First write the char, then numbering
        number++;
      }

      //Else we write the char
      else{
        out.write(str.charAt(i));
      }
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; ++i){

      //If we are at the beginning of the string
      //and it's the first one to be write,
      //then we write the line number
      if(i == off && number == 1){
        out.write(Integer.toString(number) + '\t' + cbuf[i]);        //First numbering, then write the char
        number++;
      }

      //If there is a \n OR if there is \r (and not \r\n),
      //then we write the line number
      else if(cbuf[i] == '\n'|| (cbuf[i] == '\r' && (i == len - 1 || (i < len - 1 && cbuf[i + 1] != '\n')))){
        out.write(cbuf[i] + Integer.toString(number) + '\t');       //First write the char, then numbering
        number++;
      }

      //Else we write the char
      else{
        out.write(cbuf[i]);
      }
    }
  }

  @Override
  public void write(int c) throws IOException {

    //If it is the first char, we write the line number then the char
    if(number == 1){
      out.write(Integer.toString(number));
      out.write('\t');
      out.write(c);
      number++;
    }
    //If there is \n, we write the char then the line number
    else if(c == '\n'){
      out.write(c);
      out.write(Integer.toString(number));
      out.write('\t');
      lastIsBackslashR = false;             //It was \r\n and not \r
      number++;
    }
    //If there is \r, we write it
    else if(c == '\r') {
      lastIsBackslashR = true;              //Allow to check for the next char if it is \r\n or just \r
      out.write(c);
    }
    //If it was \r (and not \r\n), we write the line number then the char
    else if(lastIsBackslashR) {
      out.write(Integer.toString(number));
      out.write('\t');
      out.write(c);
      number++;
      lastIsBackslashR = false;
    }
    //Else we write the char
    else{
        out.write(c);

    }
  }

}
