package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Cedric Lankeu
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String line) throws IOException 
  {
	super.write(line.toUpperCase());
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }
  
  @Override
  public void write(String str, int off, int len) throws IOException 
  {
	super.write(str.toUpperCase(), off, len);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException 
  {
        String cbuf_ = new String(cbuf);
	this.write(cbuf_, off, len);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException 
  {
	super.write(Character.toUpperCase(c));
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }
 
}
