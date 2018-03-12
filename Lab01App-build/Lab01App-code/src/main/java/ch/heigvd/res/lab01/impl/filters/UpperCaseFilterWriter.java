package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    //Representation of str in char array
    char[] tmp = str.toCharArray();

    //We iterate throw the array, transform the char in uppercase (if needed) and write
    for(int i = off; i < off + len; ++i){
      if(Character.isLowerCase(tmp[i]))
        tmp[i] = Character.toUpperCase(tmp[i]);
      out.write(tmp[i]);
    }

  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //We iterate throw the array, transform the char in uppercase (if needed) and write
    for(int i = off; i < off + len; ++i){
      if(Character.isLowerCase(cbuf[i]))
        cbuf[i] = Character.toUpperCase(cbuf[i]);
      out.write(cbuf[i]);
    }

  }

  @Override
  public void write(int c) throws IOException {
    //Transform the char in uppercase (if needed) and write
    if(Character.isLowerCase(c))
      c = Character.toUpperCase(c);
    out.write(c);
  }

}
