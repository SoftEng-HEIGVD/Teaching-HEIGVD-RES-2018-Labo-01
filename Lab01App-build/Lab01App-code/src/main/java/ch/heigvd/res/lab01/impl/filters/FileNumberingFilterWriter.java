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
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    private int nextLineNumber;
    private boolean isR;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
        nextLineNumber = 0;

        isR = false;
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        //String beginning = str.substring(0,off);
        String sub = str.substring(off, off + len);
        //String end = str.substring(off + len, str.length());
    /*
        for(char c : beginning.toCharArray()){
            super.write(c);
        }
    */
        for(char c : sub.toCharArray()){
            write(c);
        }
    /*
        for(char c : end.toCharArray()){
            super.write(c);
        }
    */
        /*
        String decorated = beginning + decorate(sub, "") + end;
        len += decorated.length() - str.length();
        super.write(decorated, off, len);
        */

    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        /*
        String str = String.valueOf(cbuf);

        String sub = decorate(str.substring(off, off + len), "");
        String beginning = str.substring(0,off);
        String end = str.substring(off + len, str.length());
        */
    /*
        for(int i = 0; i < off; i++){
            super.write(cbuf[i]);
        }
    */
        for(int i = off; i < off + len; i++){
            write(cbuf[i]);
        }
     /*
        for(int i = off + len; i < cbuf.length; i++){
            write(cbuf[i]);
        }
    */
        //super.write((beginning + sub + end).toCharArray(), off, len);
    }

    @Override
    public void write(int c) throws IOException {

        // first line needs a number
        if(nextLineNumber == 0){
            writeNbr(++nextLineNumber);
            write('\t');
        }

        // if it is a \r we need to wait to see if next comes \n
        if(String.valueOf((char)c).equals("\r")){
            isR = true;
            super.write(c);
        }
        // if it is \n alone or if it commes after a \r
        else if(String.valueOf((char)c).equals("\n")){
            isR = false;
            super.write(c);
            writeNbr(++nextLineNumber);
            write('\t');
        }
        // if it is a random symbol != \n that commes after a \r
        else if( isR){
            isR = false;
            writeNbr(++nextLineNumber);
            write('\t');
            // have to write it AFTER the tabulation as it is new line beginning symbole
            write(c);

        }
        // any other symbole goes through untouched
        else {
            super.write(c);
        }
    }

    // writes a multi-digit number
    private void writeNbr(int nb) throws IOException{
        String snb = Integer.toString(nb);
        for(char c : snb.toCharArray()){
            super.write(c);
        }
    }

    /*      // OLD VERSION
    private String decorate(String i, String o){

        // is it done yet ?
        if(i.equals("")){
            return o;
        }

        // split it
        String[] split = Utils.getNextLine(i);

        // si pas de return, pas de nouvelle ligne
        if(split[0].equals("")){
            return o + ((isNewLine) ? (++nextLineNumber + "\t") : "" ) + split[1];
        }
        // si return, il y a need de continuer
        else {
            o = o + ((isNewLine) ? (++nextLineNumber + "\t") : "" ) + split[0];
            isNewLine = true;
             return decorate(split[1], o);
        }
    }
    */
}
