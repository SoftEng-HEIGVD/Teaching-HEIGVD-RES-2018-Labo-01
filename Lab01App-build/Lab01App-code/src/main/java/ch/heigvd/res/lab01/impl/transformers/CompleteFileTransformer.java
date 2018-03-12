package ch.heigvd.res.lab01.impl.transformers;

import ch.heigvd.res.lab01.impl.filters.FileNumberingFilterWriter;
import ch.heigvd.res.lab01.impl.filters.UpperCaseFilterWriter;

import java.io.Writer;

/**
 * This class returns a writer decorated with two filters: an instance of
 * the UpperCaseFilterWriter and an instance of the FileNumberingFilterWriter.
 * When an instance of this class is passed to a file system explorer, it will
 * generate an output file with 1) uppercase letters and 2) line numbers at the
 * beginning of each line.
 *
 * @author Olivier Liechti
 * @author Dejvid Muaremi
 */
public class CompleteFileTransformer extends FileTransformer {
  
  /***
   * Implement the decoration of the writer
   * The resulting writer is used by the abstract class to write the characters read from the input files.
   * So, the input is first prefixed with line numbers, then transformed to uppercase, then sent to the output file
   * @param writer the writer connected to the output file
   * @return the decorated writer.
   */
  @Override
  public Writer decorateWithFilters(Writer writer) {
    // First decorate the writer with an UpperCaseFilterWriter, then decorate with a FileNumberingFilterWriter.
    writer = new FileNumberingFilterWriter(new UpperCaseFilterWriter(writer));
    return writer;
  }
}
