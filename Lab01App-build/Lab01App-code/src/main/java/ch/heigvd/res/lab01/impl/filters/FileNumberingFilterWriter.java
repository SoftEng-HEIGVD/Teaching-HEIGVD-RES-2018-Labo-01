package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
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

    private int numLine = 0;

    private int previous = ' ';

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

      /**
   * Write the string
   * @param str String to be written
   * @param off Offset from which to start writing characters
   * @param len Number of characters to write
   * @throws IOException 
   */
    @Override
    public void write(String str, int off, int len) throws IOException {
        
        //Portion of the string to write
        String subString = str.substring(off, off + len);
        
        //if the lenght of the subString is equals to  we stop
        if (subString.length() == 0) {
            return;
        }

        String writeString = "";
        //If it's the first line
        if (numLine == 0) {
            writeString += Integer.toString(++numLine) + "\t";
        }

        String[] strings = Utils.getNextLine(subString);

        
        //scan  each line of my string
        while (strings[0].length() != 0) {
            writeString += strings[0] + Integer.toString(++numLine) + "\t";
            strings = Utils.getNextLine(strings[1]);
        }

        writeString += strings[1];

        //Write the string
        super.write(writeString, 0, writeString.length());
    }

      /**
   * Write the char buffer
   * @param cbuf The char buffer
   * @param off Offset from which to start writing characters
   * @param len Number of characters to write
   * @throws IOException 
   */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String str = cbuf.toString();

        write(str, off, len);

    }

      /**
   * write the char c 
   * @param c Integer define a char to write
   * @throws IOException 
   */
    @Override
    public void write(int c) throws IOException {
        if (numLine == 0) {
            super.write(Integer.toString(++numLine));
            super.write('\t');
        }

        if (previous == '\r' && c != '\n') {
            super.write(Integer.toString(++numLine));
            super.write('\t');
        }
        super.write(c);

        if (c == '\n') {
            super.write(Integer.toString(++numLine));
            super.write('\t');
        }

        previous = c;

    }

}
