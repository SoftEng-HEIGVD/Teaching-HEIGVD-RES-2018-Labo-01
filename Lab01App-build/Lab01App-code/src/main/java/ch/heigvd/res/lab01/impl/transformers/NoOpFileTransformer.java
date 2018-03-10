package ch.heigvd.res.lab01.impl.transformers;

import java.io.Writer;

/**
 * This class returns a writer without any decorator. When an instance of
 * this class is passed to a file system explorer, it will simply duplicate
 * the content of the input file into the output file.
 * 
 * @author Olivier Liechti
 * @author Dejvid Muaremi
 */
public class NoOpFileTransformer extends FileTransformer {
  
  /***
   * The NoOpFileTransformer does not apply any transformation of the character stream
   * (no uppercase, no line number, etc.). So, we don't need to decorate the writer connected to
   * the output file at all.
   * @param writer the writer connected to the output file
   * @return the the character stream without modification
   */
  @Override
  public Writer decorateWithFilters(Writer writer) {
    return writer;
  }
}
