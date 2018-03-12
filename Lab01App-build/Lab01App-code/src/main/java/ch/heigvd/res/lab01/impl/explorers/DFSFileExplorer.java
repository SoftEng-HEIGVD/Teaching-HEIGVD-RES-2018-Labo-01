package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

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

    //List of files and directories from the root directory
    File[] listOfFiles = rootDirectory.listFiles();

    //First we visit the root
    vistor.visit(rootDirectory);

    //If there is some files/directories to explore
    if(listOfFiles != null){

      //We first explore the directories in depth
      for(int i = 0; i < listOfFiles.length; ++i){
        if (listOfFiles[i].isDirectory()) {
          explore(listOfFiles[i], vistor);
        }
      }

      //When there is no more directories to explore
      //(in depth), we visit the files
      for(int i = 0; i < listOfFiles.length; ++i){
        if (listOfFiles[i].isFile()) {
          vistor.visit(listOfFiles[i]);
        }
      }
    }

  }

}
