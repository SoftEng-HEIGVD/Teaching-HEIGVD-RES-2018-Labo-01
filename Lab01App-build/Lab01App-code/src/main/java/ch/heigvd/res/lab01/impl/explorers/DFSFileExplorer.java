package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @author updated by Joel Schar
 *
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

    if(rootDirectory == null){
      return;
    }

    // visit current folder
    vistor.visit(rootDirectory);

    // create an ordered list with the folder's content
    File[] listOfFiles = rootDirectory.listFiles();
    if(listOfFiles != null) {
      Arrays.sort(listOfFiles);

      // explore each subfolder
      for(File file: listOfFiles){
        explore(file, vistor);
      }
    }

  }

}
