package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    String[] return_strings;
    int lines_length;


    return_strings    = new String[2];
    return_strings[0] = "";
    return_strings[1] = "";
    lines_length      = lines.length();


    // on parcours chaque char de notre ligne...
    for(int i = 0; i < lines_length; ++i){
        // si le characètre '\r' est dectecté...
        if(lines.charAt(i) == '\r'){
            // On regarde si le suivant (s'il existe) est '\n', dans
            // quel cas il s'agit d'un retour à la ligne version
            // Windows.
            if(i+1 < lines_length && lines.charAt(i+1) == '\n'){
                return_strings[1] = lines.substring(i+2, lines_length);
                return_strings[0] = lines.substring(0, i+2);
            }else{
                // Sinon, c'est un retour à ligne n'utilisant que '\r'.
                return_strings[1] = lines.substring(i+1, lines_length);
                return_strings[0] = lines.substring(0, i+1);
            }
            break;

        }
        // Si au contraire, c'est le charactère '\n' qui est detecté,
        // sans de '\r' avant, on prend 2 chaîne de charactère comme
        // si c'était un '\r' unique.
        if(lines.charAt(i) == '\n'){
            return_strings[1] = lines.substring(i+1, lines_length);
            return_strings[0] = lines.substring(0, i+1);
            break;
        }
        // Finalement, si on a parcouru tous les caractères sans detecté
        // de retour à la ligne, on stock la ligne à l'indice 1 du tableau
        // à retourner, alors que l'indice 0 contiendra une châine vide,
        // comme demandé.
        if(i == lines_length-1){
            return_strings[1] = lines;
            break;
        }
    }

    return return_strings;
  }

}
