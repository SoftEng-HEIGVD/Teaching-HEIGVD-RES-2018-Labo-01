package ch.heigvd.res.lab01.impl.transformers;

import java.io.Writer;
import ch.heigvd.res.lab01.impl.filters.FileNumberingFilterWriter;
import ch.heigvd.res.lab01.impl.filters.UpperCaseFilterWriter;

/**
 * This class returns a writer decorated with two filters: an instance of
 * the UpperCaseFilterWriter and an instance of the FileNumberingFilterWriter.
 * When an instance of this class is passed to a file system explorer, it will
 * generate an output file with 1) uppercase letters and 2) line numbers at the
 * beginning of each line.
 *
 * @author Olivier Liechti
 */
public class CompleteFileTransformer extends FileTransformer {

    /*
     *@brief : Decorate a writer with a FileNumberingFilterWriter and an UpperCaseFilterWriter
     */
    @Override
    public Writer decorateWithFilters(Writer writer) {
        writer = new FileNumberingFilterWriter(new UpperCaseFilterWriter(writer));
        return writer;
    }

}
