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
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  private int nbLines = 1;
  private boolean isR;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    this.write(str.toCharArray(), off, len);
/*

    boolean isInside = false;
    String[] nlStr = Utils.getNextLine(str.substring(off, (off + len)));

    if(nbLines == 1){

    }
    while(nlStr[0].compareTo("") != 0){

      super.write(Integer.toString(nbLines));
      nbLines++;
      super.write("\t", 0, 1);
      super.write(nlStr[0], 0, nlStr[0].length());


      nlStr = Utils.getNextLine(nlStr[1]);

      isInside = true;
    }

    if(nbLines == 1) {
      this.out.write(Integer.toString(nbLines));
      nbLines++;
      super.write("\t", 0, 1);
      super.write(nlStr[1].toString(), 0, nlStr[1].length());
    } else{
      super.write(nlStr[1].toString(), 0, nlStr[1].length());
    }
    /*
    if(!isInside) {
      super.write(Integer.toString(nbLines++), 0, 1);
      super.write("\t", 0, 1);
      super.write(nlStr[1].toString(), 0, nlStr[1].length());
  } else {
      super.write(nlStr[1].toString(), 0, nlStr[1].length());
    }
*/


    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //this.write(cbuf.toString(), off, len);
    for(int i = off; i < off + len; ++i){
      write(cbuf[i]);
    }
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {


     if(nbLines == 1){
       this.out.write(Integer.toString(nbLines++));
       this.out.write("\t");
     }


     if(isR){
       if(c == '\r'){
         this.out.write(Integer.toString(nbLines++));
         this.out.write("\t");
         this.out.write(c);
         isR = true;
       }
       if(c == '\n'){
         this.out.write(c);
         this.out.write(Integer.toString(nbLines++));
         this.out.write("\t");
         isR = false;
       } else {
         this.out.write(Integer.toString(nbLines++));
         this.out.write("\t");
         this.out.write(c);
         isR = false;
       }
     } else{
       if(c == '\r'){
         this.out.write(c);
         isR = true;
       } else if(c == '\n'){
         this.out.write(c);
         this.out.write(Integer.toString(nbLines++));
         this.out.write("\t");
         isR = false;
       } else{
         this.out.write(c);
         isR = false;
       }


     }









/*
    if(c == '\n' && isR){
      this.out.write(c);
      this.out.write(Integer.toString(nbLines++));
      this.out.write("\t");
    } else if(c == '\n' && c == '\r') {
      this.out.write(c);
      this.out.write(Integer.toString(nbLines++));
      this.out.write("\t");
      isR = false;
    } else {
      this.out.write(c);
      isR = false;
    }


/*
    if(c == '\n' && isR){
      this.out.write(c);
      this.out.write(Integer.toString(nbLines++));
      this.out.write("\t");
    } else if(c == '\n'){
      this.out.write(c);
      this.out.write(Integer.toString(nbLines++));
      this.out.write("\t");
      isR = false;
    } else if (isR) {
      this.out.write(Integer.toString(nbLines++));
      this.out.write("\t");
      this.out.write(c);
      isR = false;
    } else {
      this.out.write(c);
      isR = false;
    }
*/


    /*
    if(c == '\r'){
      this.out.write(c);
      isR = true;

    } else if(c == '\n'){
      if(!isR){
        this.out.write(c);
        this.out.write(Integer.toString(nbLines++));
        this.out.write("\t");
      } else {
        this.out.write(c);

      }

      isR = false;
    } else{
      this.out.write(c);
      isR = false;
    }

    /*
    if (c == '\n' && !isR){
      this.out.write(Integer.toString(nbLines++));
      this.out.write("\t");
    }

    else if(c == '\n' && !isR){
      this.out.write(Integer.toString(nbLines++));
      this.out.write("\t");
      isR = false;
    } else{
       isR = false;
    }
    */


    /*
    if( c == '\n') {
      this.out.write(Integer.toString(nbLines++));
      this.out.write("\t");
    }*/
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
