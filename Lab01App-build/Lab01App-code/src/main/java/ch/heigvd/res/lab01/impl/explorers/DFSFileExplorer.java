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
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    
    visitor.visit(rootDirectory);
    File[] directoryContent = rootDirectory.listFiles();
    
    if(directoryContent == null){
      return;
    }
    // in case the content is not sorted, depending on the OS
    Arrays.sort(directoryContent);
    
    for(File file : directoryContent){
      
      if(file.isFile()){
        visitor.visit(file);
        
      } else if (file.isDirectory()){
        // recursive call if the content is a directory
        explore(file, visitor);
        
      }
    }
  }

}
