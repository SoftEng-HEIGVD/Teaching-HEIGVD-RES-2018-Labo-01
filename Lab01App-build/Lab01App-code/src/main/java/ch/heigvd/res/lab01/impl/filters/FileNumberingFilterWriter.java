package ch.heigvd.res.lab01.impl.filters;

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
    private  int counter = 1;
    private  boolean endWithR = false;



    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        if(str.length() - off - len < 0){
            return;
        }
        for(int i = off; i < off +len ; i++){
            write((int)str.charAt(i));
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        if(cbuf.length - off - len < 0){
            return;
        }
        for(int i = off; i < off +len ; i++){
            write((int)cbuf[i]);
        }
    }

    @Override
    public void write(int c) throws IOException {

        String strCounter = String.valueOf(counter);

        //if first line add 1\t
        if(counter == 1){
            out.write(Character.forDigit(counter++,Character.MAX_RADIX));
            out.write((int)'\t');
            out.write(c);
            return;
        }

        //if enf with \r check if \n
        if(endWithR){
            out.write((int)'\r');
            if((char)c == '\n'){
                out.write(c);
            }
            for(int j = 0; j < strCounter.length(); j++){
                out.write(strCounter.charAt(j));
            }
            counter++;
            out.write((int)'\t');
            endWithR = false;
            if((char)c != '\n') {
                out.write(c);
            }
            return;
        }

        //if \n write \nX\t
        if((char)c== '\n'){
            out.write(c);
            for(int j = 0; j < strCounter.length(); j++){
                out.write(strCounter.charAt(j));
            }
            counter++;
            out.write((int)'\t');
            return;
        }

        //if \r just change the boolen i will use it in if(enfWithR) check.
        if((char) c == '\r'){
            endWithR = true;
            return;
        }

        //just write the character if it is not a spectial character
        out.write(c);


    }



}
