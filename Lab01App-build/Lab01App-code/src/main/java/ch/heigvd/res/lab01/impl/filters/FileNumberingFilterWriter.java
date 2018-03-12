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
  private int line_counter;
  private boolean isEmpty;
  private boolean last_written_char_is_r;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    line_counter           = 0;
    isEmpty                = true;
    last_written_char_is_r = false;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    String[] next_lines;
    String   line_number;
    String   line_to_write;
    String   first_line;
    String   second_line;

    int iterator;

    next_lines     =  Utils.getNextLine(str.substring(off, off+len));
    first_line     =  next_lines[0];
    second_line    =  next_lines[1];
    line_to_write  =  !first_line.equals("") ? first_line : second_line;

    // Se fera uniquement lors de la première écriture du fichier.
    // On écrit le premier numéro de ligne.
    if(isEmpty){
      line_number  =  String.valueOf(++line_counter);

      for(iterator = 0; iterator < line_number.length(); iterator++)
        super.write((int)line_number.charAt(iterator));

      super.write((int)'\t');
      isEmpty = false;
    }

    iterator = 0;

    // On fait ici une vérification. Si le caractère
    // actuel est '\r', on met la variable "last_written_char_is_r"
    // à vrai qui permettera par la suite d'obliger au programme
    // d'attendre de voir si le prochain char est '\n' avant de faire
    // le saut de ligne.
    if(line_to_write.length() > 0)
        last_written_char_is_r = line_to_write.charAt(iterator) == '\r';
    else
      last_written_char_is_r = false;

    // on écrit chaque caractère de la ligne...
    for(; iterator < line_to_write.length(); iterator++)
      super.write((int)line_to_write.charAt(iterator));


    // Si la ligne avait un retour à la ligne...
    if(!first_line.equals("")){
      // ... est que le dernier charactère n'était pas un '\r'
      if(!last_written_char_is_r){
        // On écrit le nouveau numéro de ligne avec une tabulation.
        line_number  =  String.valueOf(++line_counter);

        for(iterator = 0; iterator < line_number.length(); iterator++)
          super.write((int)line_number.charAt(iterator));

        super.write((int)'\t');
      }

      // On fait un appel récursive à la méthode avec le reste du texte tans
      // que ce dernier possède des charactère de retour à la ligne.
      this.write(second_line, 0, second_line.length());
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    this.write(String.valueOf(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
    this.write(String.valueOf((char)c), 0, 1);
  }

}
