package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

import ch.heigvd.res.lab01.impl.Utils;

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

    private int numberOfLines = 1;
    private boolean newLine = true;
    private char lastCharacter;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    public void write(String str, int off, int len) throws IOException {

        //Appel a la methode getNextLine de Utils
        String[] lines = Utils.getNextLine(str.substring(off, off + len));
        String nextLine = lines[0];
        String remainingLines = lines[1];

        //chaine a afficher a la fin
        String textToWrite = (newLine ? numberOfLines + "\t" : "") + nextLine;

        //verification de l'existence d'une nouvelle ligne
        newLine = nextLine.isEmpty();

        while (!nextLine.isEmpty()) {

            //recuperation de la ligne suivante
            lines = Utils.getNextLine(remainingLines);
            nextLine = lines[0];
            remainingLines = lines[1];

            //ajout de la ligne
            textToWrite += ++numberOfLines + "\t" + nextLine;

            //newLine est vrai tant que le texte restant (lines[1] n'est pas vide)
            newLine = !remainingLines.isEmpty();
        }

        //Enfin, ajout de la derniere ligne restante
        textToWrite += remainingLines;

        super.out.write(textToWrite);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

            write(String.valueOf(cbuf), off, len);

    }

    @Override
    public void write(int c) throws IOException {
        String textToWrite = "";             
        char currentChar = (char) c;    

        //si le caractere courant est \r on cherche si le suivant est \n
        if (currentChar == '\r') {
            newLine = true;
        } else if (currentChar == '\n') {
            if (lastCharacter == '\r') {
                textToWrite += String.valueOf(lastCharacter);
            }

            textToWrite += String.valueOf(currentChar) + numberOfLines++ + "\t";
            newLine = false;
            
        } else {
            if (newLine) {
                if (lastCharacter == '\r') {
                    textToWrite += String.valueOf(lastCharacter);
                }
                textToWrite += numberOfLines++ + "\t";
            }

            textToWrite += currentChar;
            newLine = false;
        }

        lastCharacter = currentChar;
        super.out.write(textToWrite);
    }
}
