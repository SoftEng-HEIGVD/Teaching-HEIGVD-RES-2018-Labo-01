package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
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
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");

    // in case directory is empty
    if(rootDirectory == null){
      return;
    }

    // threat current directory
    vistor.visit(rootDirectory);

    // create a list with the folder's content
    File[] listOfFiles = rootDirectory.listFiles();

    // sorting array to get an alphabetical order
    if(listOfFiles != null) {
      Arrays.sort(listOfFiles);

      //visit subdirectories
      for(File file: listOfFiles){
        explore(file, vistor);
      }
    }

  }

}
