package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {

    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        super.write(str.toUpperCase(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        
        //verification que off et len soit valides
        if(off >= 0 && off + len <= cbuf.length) {
            String str = new String(cbuf);
            //conversion en majuscule
            this.write(str, off, len);
        }
    }

    @Override
    public void write(int c) throws IOException {
        //conversion des caracteres ascii des lettres minuscules 
        //en lajuscules
        if(c >= 97 && c <= 122) {
            //majuscules entre 65 et 90
            c -= 32;
        }
        super.write(c);
    }

}
