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
 * @author Olivier Nicole (Student)
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

      /*
       * Updated by Olivier Nicole
       */

      vistor.visit(rootDirectory);

      //Check if the rootDirectory is a directory !
      if(rootDirectory.isDirectory()) {
          File[] files = rootDirectory.listFiles();
          Arrays.sort(files);

          //Call explore on all files and directories
          for(File file : files) {
              if(file.isDirectory() || file.isFile()) {
                  explore(file, vistor);    //call recursivly explore..
              }
          }
      }
  }
}
