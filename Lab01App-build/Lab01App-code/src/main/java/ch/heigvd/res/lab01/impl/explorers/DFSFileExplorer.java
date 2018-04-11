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
 * @contributor Nair Alic
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
     //we make a recursive call to visit in order to go further 
     //in the sub-directories and found file that are in them
     visitor.visit(rootDirectory);
     File[] files = rootDirectory.listFiles();
     
     if(files != null) {
        for(File file : files) {
           if(file.isDirectory()){
              explore(file, visitor);
           }else{
              visitor.visit(file);
           }
        }
     }
    
  }

}
