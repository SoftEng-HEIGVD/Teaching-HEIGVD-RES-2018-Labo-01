package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

    File[] files_list;

    if(vistor == null)
      return;

    if (rootDirectory != null)
      vistor.visit(rootDirectory);
    else
      return;


    // si le fichier donné en paramètre est un dossier, alors on
    // fait un appel récursif à la méthode pour chacun des fichiers/dossiers
    // qu'il contient.
    if(rootDirectory.isDirectory()){
      files_list = rootDirectory.listFiles();
      Arrays.sort(files_list);
      for(int i = 0; i < files_list.length; i++)
        explore(files_list[i], vistor);

    }
  }

}
