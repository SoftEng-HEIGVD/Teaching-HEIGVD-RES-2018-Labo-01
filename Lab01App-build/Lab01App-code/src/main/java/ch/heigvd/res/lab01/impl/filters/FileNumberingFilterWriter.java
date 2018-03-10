package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.LogRecord;
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
    private static int nbrLine;
    private static boolean isNewLine;

    public FileNumberingFilterWriter(Writer out) {

        super(out);
        nbrLine = 1;
        isNewLine = true;
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        // Get the substring asked from the text
        String[] lines = Utils.getNextLine(str.substring(off, off + len));
        String lineWithSeparator = lines[0];
        String remainingLines = lines[1];

        //Formatted text
        String newStr = (isNewLine ? nbrLine + "\t" : "") + lineWithSeparator;

        //The next line has to be written without number of line
        if(lineWithSeparator.isEmpty()){
            isNewLine = false;
        }

        while(!lineWithSeparator.isEmpty() && !lineWithSeparator.equals("\r")){

            //Get the next lines
            lines = Utils.getNextLine(remainingLines);
            lineWithSeparator = lines[0];
            remainingLines = lines[1];

            //Write the line
            newStr += ++nbrLine + "\t" + lineWithSeparator;

            //False if the next line has to be written without number of line
            isNewLine = !remainingLines.isEmpty();
        }

        //Write the remaining text
        newStr += remainingLines;

        //Write the formatted text
        super.out.write(newStr);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        write(String.valueOf(cbuf), off, len);
    }

    @Override
    public void write(int c) throws IOException {
        String str = String.valueOf((char) c);
        write(str, 0, str.length());
    }
}