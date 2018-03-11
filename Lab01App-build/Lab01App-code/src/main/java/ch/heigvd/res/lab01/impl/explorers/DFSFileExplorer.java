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
 * @author Labinot Rashiti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
     // Firstly, we need to explore the root directory
     vistor.visit(rootDirectory);
     
     if (rootDirectory.isDirectory()) {
        
        // Get the list of files from rootDirectory
        File[] listFiles = rootDirectory.listFiles();
        Arrays.sort(listFiles);
        
        // Brows all the files/directories and explores them by recursing
        for (int i = 0; i < listFiles.length; ++i) {
           if (listFiles[i].isDirectory()) {
              explore(listFiles[i], vistor);
           } else if (listFiles[i].isFile()) {
              explore(listFiles[i], vistor);
           }
        }
       
     }
  }
}
