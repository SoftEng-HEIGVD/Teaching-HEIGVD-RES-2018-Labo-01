package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Reader;
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

    private int counter = 1;
    private char last;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        this.write(str.toCharArray(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i <= off + len - 1; i++) {
            this.write(cbuf[i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        /*
        if (car == '\n' && last == '\r') {//windows is \r\n
            super.write(car);
            startLine();
        } else if (car == '\r') {//mac is \r
            super.write(car);
            startLine();
        } else if (car == '\n') { //linux is \n
            super.write(car);
            startLine();
            
        } else { //other char
            super.write(car);
        }

        last = car;*/
        char car = (char) c;
        if (counter == 1) {
            startLine();
        }
        if (car != '\n' && last == '\r') {
            startLine();
        }
        super.write(car);
        if (car == '\n') {
            startLine();
        }
        last = car;
    }

    private void startLine() throws IOException {
        for (char car : (counter + "\t").toCharArray()) {
            super.write((int) car);
        }
        counter++;
    }

}
