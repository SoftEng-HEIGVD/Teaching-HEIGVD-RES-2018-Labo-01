package ch.heigvd.res.lab01.impl.transformers;

import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This abstract class implements the IFileVisitor interface and has the
 * responsibility to open an input text file, to read its content, to apply a
 * number of transformations (via filters) and to write the result in an output
 * text file.
 *
 * The subclasses have to implement the decorateWithFilters method, which
 * instantiates a list of filters and decorates the output writer with them.
 *
 * @author Olivier Liechti
 */
public abstract class FileTransformer implements IFileVisitor {

    private static final Logger LOG = Logger.getLogger(FileTransformer.class.getName());
    private final List<FilterWriter> filters = new ArrayList<>();

    /**
     * The subclasses implement this method to define what transformation(s) are
     * applied when writing characters to the output writer. The visit(File
     * file) method creates an output file and creates a corresponding writer.
     * It then calls decorateWithFilters and passes the writer as argument. The
     * method wraps 0, 1 or more filter writers around the original writer and
     * returns the result.
     *
     * @param writer the writer connected to the output file
     * @return the writer decorated by 0, 1 or more filter writers
     */
    public abstract Writer decorateWithFilters(Writer writer);

    @Override
    public void visit(File file) {
        if (!file.isFile()) {
            return;
        }
        try {
            // Open in read-mode the file given in parameter
            Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            // Create a new file in write-mode to store the result
            Writer writer = new OutputStreamWriter(new FileOutputStream(file.getPath() + ".out"), "UTF-8"); // the bug fix by teacher
            // add the decoration filter to the writer stream.
            writer = decorateWithFilters(writer);

            // read the input file char by char and write it with filters in the output.
            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }

            // closing the read and write streams
            reader.close();
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

}
