package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

import javax.rmi.CORBA.Util;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int nbLine = 1;
    private boolean maybeAnewLine = false; // if we detect a '\r' => maybeAnewLine = true


    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        StringBuilder buffer = new StringBuilder("");
        String line = str.substring(off, off + len);
        // For the first line of the writer, we automatically add the first line number
        if (nbLine == 1) {
            buffer.append(nbLine++);
            buffer.append('\t');
        }

        // We use the getNextLine() function of Utils to split the line at the correct line separator (see Utils)
        String[] splittedString;
        while (!((splittedString = Utils.getNextLine(line))[0]).isEmpty()) {
            // while there is a line separator detected (in the string line)
            buffer.append(splittedString[0]);
            buffer.append(nbLine++);
            buffer.append('\t');

            line = splittedString[1];
        }

        // if there is no line separator at the end of the string or no line separator at all (there is always content in the second part of the array)
        if (!splittedString[1].isEmpty()){
            buffer.append(splittedString[1]);
        }

        // Finally we write all the buffer to the writer
        super.write(buffer.toString(), 0, buffer.length());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; ++i){
            write(cbuf[i]);
        }
    }

    @Override
    public void write(int c) throws IOException {

        // New Line on MACOS9
        if ((maybeAnewLine && c != '\n') || nbLine == 1) {
            addNewLine();
        }

        super.write(c);

        // New Line on Unix and Windows
        if (c == '\r') {
            maybeAnewLine = true;
        }
        else if (c == '\n') {
            addNewLine();
        }


    }


    /**
     * This class add the next line number and a tab character (\t)
     * @throws IOException
     * @author Frueh Loïc
     */
    private void addNewLine() throws IOException{
        String nbLineToString = String.valueOf(nbLine++) + '\t';
        out.write(nbLineToString, 0, nbLineToString.length());
        maybeAnewLine = false;
    }
}
