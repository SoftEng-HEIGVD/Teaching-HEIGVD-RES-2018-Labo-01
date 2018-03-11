package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

import ch.heigvd.res.lab01.impl.Utils;
import javassist.bytecode.analysis.Util;

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
  private int lineNumber = 1;
  private boolean wasR = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
	  write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
	  for(int i = 0; i < len; i++)
		  write(cbuf[i + off]);
  }

  @Override
  public void write(int c) throws IOException {
	  //if first line print newline number
	  //if c is not \n but was \r print newline number
	  if(lineNumber == 1 || (c != '\n' && wasR)) out.write(lineNumber++ + "\t");
	  //print c anyway
	  out.write(c);
	  //if c is \n print newline number
	  if(c == '\n') out.write(lineNumber++ + "\t");
	  //remember if c was \r
	  wasR = c == '\r';
  }

}
