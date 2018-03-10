package ch.heigvd.res.lab01.impl.filters;

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
    private static boolean numberIsWritten;
    private static boolean isNewLine;

    public FileNumberingFilterWriter(Writer out) {

        super(out);
        nbrLine = 1;
        numberIsWritten = false;
        isNewLine = true;
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        // Get the substring asked from the text
        String newStr = str.substring(off, off + len);

        //Set the number of the line if not written
        if(isNewLine == true && numberIsWritten == false){
            newStr = nbrLine +  "\t" +newStr;
            numberIsWritten = false;
            isNewLine = false;
        }

        //Find the separator
        String[] separators = {"\r\n", "\n", "\r"};     //Possible separators
        int positionSeparator = -1;                     //Position of the separator found
        String separator = "";                          //Separator found
        int indexFrom = 0;                              //Index after which the separator search is made

        for (String s : separators) {

            int position = newStr.indexOf(s, indexFrom);

            if(position != -1){
                positionSeparator = position;
                separator = s;
                break;
            }
        }

        while(positionSeparator != -1){
            nbrLine++;

            indexFrom = positionSeparator + separator.length();

            String firstPart = newStr.substring(0, indexFrom);
            String secondPart = newStr.substring(indexFrom, newStr.length());

            numberIsWritten = secondPart.isEmpty();
            newStr = firstPart + nbrLine + "\t" +secondPart;

            positionSeparator = newStr.indexOf(separator, indexFrom);
            isNewLine = true;
        }

        //Write the formatted text
        super.out.write(newStr);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

    @Override
    public void write(int c) throws IOException {
        throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

}
