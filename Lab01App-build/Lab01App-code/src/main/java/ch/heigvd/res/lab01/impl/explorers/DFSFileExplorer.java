package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subDirdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

    //Do we work on something ?
    if(rootDirectory != null){

      //We do what we have to do
      vistor.visit(rootDirectory);

      //And we parkour his chilren
      File[] fileList = rootDirectory.listFiles();

      //If it had some, it should have use protection
      if(fileList != null)
        for(File subDir: fileList){
          explore(subDir, vistor);
        }
    }
  }
}


