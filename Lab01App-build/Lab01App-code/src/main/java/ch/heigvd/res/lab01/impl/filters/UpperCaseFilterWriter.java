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
    for (int i = off; i < off+len; i++)
        this.write(str.charAt(i));
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off+len; i++)
        this.write(cbuf[i]);
  }

  @Override
  public void write(int c) throws IOException {

    // Avant d'appeler la méthode toUpperCase fournis par la classe
    // Character, on vérifie que le char donné en paramètre est bien
    // une lettre.
    if(Character.isLetter(c))
      super.write(Character.toUpperCase(c));
    else
      super.write(c);
  }

}
