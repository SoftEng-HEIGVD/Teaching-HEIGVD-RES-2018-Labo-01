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
 * francine youndzo
 */
public class DFSFileExplorer implements IFileExplorer {
    
  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
        
         vistor.visit(rootDirectory);//visits current dorectory
        if(rootDirectory.listFiles() == null)  //tests whether current doirectories is emptx 
          return;

        if(rootDirectory.isDirectory()) {
          File[] files = rootDirectory.listFiles();
          
          Arrays.sort(files); // sort all files
          
          for (File fileEntry : files) { //browse all files       
                  explore(fileEntry, vistor); //explore files entries
      
          }
      }
  }
 
}